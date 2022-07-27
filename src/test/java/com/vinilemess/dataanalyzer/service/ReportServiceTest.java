package com.vinilemess.dataanalyzer.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

class ReportServiceTest {

    private ReportService service;
    private final String testData = """
            001ç1234567891234çDiegoç50000 001ç3245678865434çRenatoç40000.99s\s
            001ç3247788865434çClaudioç40000.99s
            002ç2345675433444345çEduardoPereiraçRural
            002ç2345687233444345çAlfonso DaviesçFutebolistico
            003ç10ç[1-10-100,2-30-2.50,3-40-3.10]çDiego
            003ç08ç[1-34-10,2-33-1.50,3-40-0.10]çRenato
            003ç13ç[1-99-15,2-33-3.50,3-400-0.10]çClaudio""";
    private final String inputPath =  System.getenv("DATA_IN");
    private final String outputPath =  System.getenv("DATA_OUT");
    private final String testFile = "test_file.dat";

    @BeforeEach
    void init() {
        service = new ReportService(new DataExtractionService(new ConverterService()));
        try {
            Files.write(Paths.get(inputPath+"/"+testFile), Collections.singleton(testData));
        } catch (Exception exception) {
            fail();
        }
    }

    @Test
    void mustGetAmountCustomersSuccessfully() {
        assertEquals(2, service.getAmountCustomers(testFile));
    }

    @Test
    void mustGetAmountSalesmanSuccessfully() {
        assertEquals(3, service.getAmountSalesman(testFile));
    }

    @Test
    void mustGetMostExpensiveSaleIdSuccessfully() {
        assertEquals(13, service.getMostExpensiveSaleId(testFile));
    }

    @Test
    void mustGetWorstSalesmanSuccessfully() {
        assertEquals("Renato", service.getWorstSalesman(testFile));
    }

    @Test
    void mustGenerateReportFileSuccessfully() {
        service.generateReportFile(testFile);
        assertTrue(Files.exists(Paths.get(outputPath+"/test_file.done.dat")));
    }

    @AfterEach
    void finish() {
        try {
            Files.deleteIfExists(Paths.get(inputPath+"/"+testFile));
            Files.deleteIfExists(Paths.get(outputPath+"/test_file.done.dat"));
        } catch (Exception exception) {
            fail();
        }
    }
}