package com.vinilemess.dataanalyzer.model;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class ItemTest {

    @Test
    void mustGetTotalPriceSuccessfully() {
        Item item = new Item(1, 20, BigDecimal.valueOf(15));
        assertEquals(BigDecimal.valueOf(300), item.getTotalPrice());
    }
}