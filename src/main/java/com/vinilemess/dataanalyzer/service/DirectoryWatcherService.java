package com.vinilemess.dataanalyzer.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.*;
@Service
@Slf4j
public class DirectoryWatcherService {

    private final ReportService reportService;
    private final WatchService watchService;
    private final Path inputDirPath;
    private final WatchKey watchKey;

    @Autowired
    public DirectoryWatcherService(ReportService reportService) throws IOException {
        this.reportService = reportService;
        this.watchService = FileSystems.getDefault().newWatchService();
        this.inputDirPath = Paths.get(System.getenv("DATA_IN"));
        this.watchKey = inputDirPath.register(watchService,
                StandardWatchEventKinds.ENTRY_CREATE,
                StandardWatchEventKinds.ENTRY_MODIFY);
    }

    public void startWatching() throws InterruptedException{
        WatchKey key;
        log.info("Started Monitoring...");
        while ((key = watchService.take()) != null) {
            for (WatchEvent<?> event : key.pollEvents()) {
                String newFile = String.valueOf(event.context());
                generateReport(newFile);
            }
            key.reset();
        }
    }

    private void generateReport(String newFile) {
        if (isFileExtensionValid(newFile)) {
            try {
                reportService.generateReportFile(newFile);
                log.info("Generating new metrics for file : " + newFile);
            } catch (Exception exception) {
                log.error(exception.getMessage());
            }
        }
    }

    private boolean isFileExtensionValid(String file) {
        return file.matches("\\w+\\.dat");
    }
}
