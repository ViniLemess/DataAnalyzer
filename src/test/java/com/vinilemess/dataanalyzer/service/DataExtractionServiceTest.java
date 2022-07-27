package com.vinilemess.dataanalyzer.service;

import com.vinilemess.dataanalyzer.exception.UnableToReadFileException;
import org.junit.jupiter.api.*;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

class DataExtractionServiceTest {

    private DataExtractionService service;
    private final String testData = """
            001ç1234567891234çDiegoç50000 001ç3245678865434çRenatoç40000.99s\s
            001ç3247788865434çClaudioç40000.99s
            002ç2345675433444345çEduardoPereiraçRural
            002ç2345687233444345çAlfonso DaviesçFutebolistico
            003ç10ç[1-10-100,2-30-2.50,3-40-3.10]çDiego
            003ç08ç[1-34-10,2-33-1.50,3-40-0.10]çRenato
            003ç13ç[1-99-15,2-33-3.50,3-400-0.10]çClaudio""";
    private final String inputPath =  System.getenv("DATA_IN");
    private final String testFile = "test_file.dat";

    @BeforeEach
    void init() {
        service = new DataExtractionService(new ConverterService());
        try {
            Files.write(Paths.get(inputPath+"/"+testFile), Collections.singleton(testData));
        } catch (Exception exception) {
            fail();
        }
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

    @AfterEach
    void finish() {
        try {
            Files.delete(Paths.get(inputPath+"/"+testFile));
        } catch (Exception exception) {
            fail();
        }
    }
}