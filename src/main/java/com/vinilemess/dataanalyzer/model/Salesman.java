package com.vinilemess.dataanalyzer.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@AllArgsConstructor
@Getter
public class Salesman {
    private String cpf;
    private String name;
    private BigDecimal salary;
}
