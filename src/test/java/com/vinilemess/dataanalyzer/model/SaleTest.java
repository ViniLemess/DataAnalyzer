package com.vinilemess.dataanalyzer.model;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SaleTest {

    @Test
    void mustGetTotalItemsPriceSuccessfully() {
        List<Item> items = Arrays.asList(new Item(1, 5, BigDecimal.valueOf(15.0)),
                new Item(2, 10, BigDecimal.valueOf(10.0)),
                new Item(3, 8, BigDecimal.valueOf(5.0)),
                new Item(4, 30, BigDecimal.valueOf(1.5)));
        Sale sale = new Sale(1, items, "Ronaldo");
        assertEquals(BigDecimal.valueOf(260.0), sale.getTotalItemsPrice());
    }
}