package com.vinilemess.dataanalyzer.service;

import com.vinilemess.dataanalyzer.exception.UnableToWrightFileException;
import com.vinilemess.dataanalyzer.model.Sale;
import com.vinilemess.dataanalyzer.model.Salesman;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;

@Service
public class ReportService {

    private final DataExtractionService service;

    private final String outputDataPath;

    @Autowired
    public ReportService(DataExtractionService service) {
        this.service = service;
        outputDataPath = System.getenv("DATA_OUT");
    }

    public int getAmountCustomers(String fileName) {
        return service.getAllCustomer(fileName).size();
    }

    public int getAmountSalesman(String fileName) {
        return service.getAllSalesman(fileName).size();
    }

    public long getMostExpensiveSaleId(String fileName) {
        BigDecimal mostExpensiveSaleValue = BigDecimal.ZERO;
        long mostExpensiveSaleId = 0;
        for (Sale sale : service.getAllSale(fileName)) {
            if (sale.getTotalItemsPrice().compareTo(mostExpensiveSaleValue) > 0) {
                mostExpensiveSaleValue = sale.getTotalItemsPrice();
                mostExpensiveSaleId = sale.getId();
            }
        }
        return mostExpensiveSaleId;
    }

    public String getWorstSalesman(String fileName) {
        String worstSalesmanName = "";
        BigDecimal worstSaleAmount = null;
        for (Salesman salesman : service.getAllSalesman(fileName)) {
            BigDecimal amountSold = getAmountSoldBySalesman(salesman.getName(), fileName);
            if (worstSaleAmount == null || amountSold.compareTo(worstSaleAmount) < 0) {
                worstSaleAmount = amountSold;
                worstSalesmanName = salesman.getName();
            }
        }
        return worstSalesmanName;
    }

    public void generateReportFile(String fileName) {
        String newFilePath = outputDataPath+"/"+fileName.replace(".dat", ".done.dat");
        String report = "Amount of customers: " + getAmountCustomers(fileName) + "\n"
                + "Amount of salesman: " + getAmountSalesman(fileName) + "\n"
                + "Most expensive sale ID: " + getMostExpensiveSaleId(fileName) + "\n"
                + "Worst Salesman: " + getWorstSalesman(fileName);
        try {
            Files.write(Paths.get(newFilePath), Collections.singleton(report));
        } catch (IOException e) {
            throw new UnableToWrightFileException(e.getMessage());
        }
    }

    private BigDecimal getAmountSoldBySalesman(String salesmanName, String fileName) {
        BigDecimal amountSold = BigDecimal.ZERO;
        for (Sale sale : service.getAllSale(fileName)) {
            if (sale.getSalesmanName().equals(salesmanName)) {
                amountSold = amountSold.add(sale.getTotalItemsPrice());
            }
        }
        return amountSold;
    }
}
