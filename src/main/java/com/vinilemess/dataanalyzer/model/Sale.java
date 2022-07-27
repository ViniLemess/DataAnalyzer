package com.vinilemess.dataanalyzer.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@Getter
public class Sale {
    private long id;
    private List<Item> items;
    private String salesmanName;

    public BigDecimal getTotalItemsPrice() {
        BigDecimal totalPrice = BigDecimal.ZERO;
        for (Item item : items) {
            totalPrice = totalPrice.add(item.getTotalPrice());
        }
        return totalPrice;
    }
}
