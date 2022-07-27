package com.vinilemess.dataanalyzer.service;

import com.vinilemess.dataanalyzer.exception.UnableToReadFileException;
import com.vinilemess.dataanalyzer.model.Customer;
import com.vinilemess.dataanalyzer.model.Sale;
import com.vinilemess.dataanalyzer.model.Salesman;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@Slf4j
public class DataExtractionService {

    private final Pattern salesmanPattern;
    private final Pattern customerPattern;
    private final Pattern salePattern;
    private final ConverterService converterService;
    private final String inputDataPath;

    @Autowired
    public DataExtractionService(ConverterService converterService) {
        this.converterService = converterService;
        this.salesmanPattern = Pattern.compile("001ç(\\d+)ç([a-zA-ZÀ-ú ]*|[a-z-A-ZÀ-ú]+)ç(\\d+[.]\\d+|\\d+)");
        this.customerPattern = Pattern.compile("002ç(\\d+)ç([a-zA-ZÀ-ú ]*|[a-z-A-ZÀ-ú]+)ç([a-zA-ZÀ-ú ]*|[a-z-A-ZÀ-ú]+)");
        this.salePattern = Pattern.compile("003ç\\d+ç\\[(\\d+-\\d+-(\\d+[.]\\d+|\\d+)(,?))+\\]ç([a-zA-ZÀ-ú ]*|[a-z-A-ZÀ-ú]+)");
        this.inputDataPath = System.getenv("DATA_IN");
    }

    public List<Salesman> getAllSalesman(String fileName) {
        List<Salesman> salesmanList = new ArrayList<>();
        Matcher matcher = salesmanPattern.matcher(getFileAsString(fileName));
        while (matcher.find()) {
            salesmanList.add(converterService.stringToSalesman(matcher.group()));
        }
        return salesmanList;
    }

    public List<Customer> getAllCustomer(String fileName) {
        List<Customer> customers = new ArrayList<>();
        Matcher matcher = customerPattern.matcher(getFileAsString(fileName));
        while (matcher.find()) {
            customers.add(converterService.stringToCustomer(matcher.group()));
        }
        return customers;
    }

    public List<Sale> getAllSale(String fileName) {
        List<Sale> sales = new ArrayList<>();
        Matcher matcher = salePattern.matcher(getFileAsString(fileName));
        while (matcher.find()) {
            sales.add(converterService.stringToSale(matcher.group()));
        }
        return sales;
    }

    private String getFileAsString(String fileName) {
        try {
            return Files.readString(Paths.get(inputDataPath+"/"+fileName));
        } catch (IOException exception) {
            log.error(exception.getMessage());
            throw new UnableToReadFileException(exception.getMessage());
        }
    }
}
