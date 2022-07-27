package com.vinilemess.dataanalyzer.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Customer {
    private String cnpj;
    private String name;
    private String businessArea;
}
