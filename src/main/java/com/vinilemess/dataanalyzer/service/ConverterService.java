package com.vinilemess.dataanalyzer.service;

import com.vinilemess.dataanalyzer.model.Customer;
import com.vinilemess.dataanalyzer.model.Item;
import com.vinilemess.dataanalyzer.model.Sale;
import com.vinilemess.dataanalyzer.model.Salesman;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class ConverterService {

    private static final String COLUMN_SEPARATOR = "ç";

    public Sale stringToSale(String str) {
        String[] fields = str.split(COLUMN_SEPARATOR);
        int saleId = Integer.parseInt(fields[1]);
        List<Item> items = stringToItemList(fields[2]);
        String salesmanName = fields[3];
        return new Sale(saleId, items, salesmanName);
    }

    public List<Item> stringToItemList(String str) {
        str = str.replaceAll("\\[|\\]", "");
        List<Item> itemList = new ArrayList<>();
        String[] items = str.split(",");
        for (String item : items) {
            itemList.add(stringToItem(item));
        }
        return itemList;
    }
    public Item stringToItem(String str) {
        String[] fields = str.split("-");
        int itemId = Integer.parseInt(fields[0]);
        int quantity = Integer.parseInt(fields[1]);
        BigDecimal price = BigDecimal.valueOf(Double.parseDouble(fields[2]));
        return new Item(itemId, quantity, price);
    }

    public Customer stringToCustomer(String str) {
        String[] fields = str.split(COLUMN_SEPARATOR);
        return new Customer(fields[1], fields[2], fields[3]);
    }

    public Salesman stringToSalesman(String str) {
        String[] fields = str.split(COLUMN_SEPARATOR);
        String cpf = fields[1];
        String name = fields[2];
        BigDecimal salary = BigDecimal.valueOf(Double.parseDouble(fields[3]));
        return new Salesman(cpf, name, salary);
    }
}
