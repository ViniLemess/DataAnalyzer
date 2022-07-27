package com.vinilemess.dataanalyzer.service;

import com.vinilemess.dataanalyzer.exception.UnableToReadFileException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DataExtractionServiceTest {

    private DataExtractionService service;
    private String testFile = "test_file.dat";

    @BeforeEach
    void init() {
        service = new DataExtractionService(new ConverterService());
    }

    @Test
    void mustGetAllSalesmanSuccessfully() {
        assertEquals(3, service.getAllSalesman(testFile).size());
    }

    @Test
    void mustValidateGetAllSalesmanWhenFileIsUnavailable() {
        assertThrows(UnableToReadFileException.class, () -> service.getAllSalesman(""));
    }

    @Test
    void mustGetAllCustomerSuccessfully() {
        assertEquals(2, service.getAllCustomer(testFile).size());
    }

    @Test
    void mustValidateGetAllCustomerWhenFileIsUnavailable() {
        assertThrows(UnableToReadFileException.class, () -> service.getAllCustomer(""));
    }

    @Test
    void mustGetAllSaleSuccessfully() {
        assertEquals(3, service.getAllSale(testFile).size());
    }

    @Test
    void mustValidateGetAllSaleWhenFileIsUnavailable() {
        assertThrows(UnableToReadFileException.class, () -> service.getAllSale(""));
    }
}