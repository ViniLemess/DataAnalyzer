package com.vinilemess.dataanalyzer.service;

import com.vinilemess.dataanalyzer.model.Customer;
import com.vinilemess.dataanalyzer.model.Item;
import com.vinilemess.dataanalyzer.model.Sale;
import com.vinilemess.dataanalyzer.model.Salesman;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ConverterServiceTest {

    private ConverterService service;

    @BeforeEach
    void init() {
        service = new ConverterService();
    }

    @Test
    void mustConvertFromStringToSaleSuccessfully() {
        Sale sale = service.stringToSale("003ç09ç[1-34-10,2-33-1.50,3-40-0.10,4-33-5.55]çRonaldo");
        assertInstanceOf(Sale.class, sale);
        assertEquals(9, sale.getId());
        assertEquals(4, sale.getItems().size());
        assertEquals("Ronaldo", sale.getSalesmanName());
    }

    @Test
    void mustConvertFromStringToItemListSuccessfully() {
        List<Item> items = service.stringToItemList("[1-34-10,2-33-1.50,3-40-0.10,4-33-5.55]");
        assertEquals(4, items.size());
    }

    @Test
    void mustConvertFromStringToItemSuccessfully() {
        Item item = service.stringToItem("1-34-10.55");
        assertInstanceOf(Item.class, item);
        assertEquals(1, item.getId());
        assertEquals(34, item.getQuantity());
        assertEquals(BigDecimal.valueOf(10.55), item.getPrice());
    }

    @Test
    void mustConvertFromStringToCustomerSuccessfully() {
        Customer customer = service.stringToCustomer("002ç2345675433444345çEduardoPereiraçRural");
        assertInstanceOf(Customer.class, customer);
        assertEquals("2345675433444345", customer.getCnpj());
        assertEquals("EduardoPereira", customer.getName());
        assertEquals("Rural", customer.getBusinessArea());
    }

    @Test
    void mustConvertFromStringToSalesmanSuccessfully() {
        Salesman salesman = service.stringToSalesman("001ç3247788865434çClaudioç40000.99");
        assertInstanceOf(Salesman.class, salesman);
        assertEquals("3247788865434", salesman.getCpf());
        assertEquals("Claudio", salesman.getName());
        assertEquals(BigDecimal.valueOf(40000.99), salesman.getSalary());
    }
}