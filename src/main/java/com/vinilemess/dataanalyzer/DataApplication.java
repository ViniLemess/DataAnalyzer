package com.vinilemess.dataanalyzer;

import com.vinilemess.dataanalyzer.service.DirectoryWatcherService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
@ComponentScan(basePackages = "com")
public class DataApplication {
    private static final ApplicationContext context = new AnnotationConfigApplicationContext(DataApplication.class);
    private static final DirectoryWatcherService watchDirService = context.getBean(DirectoryWatcherService.class);

    public static void main(String[] args) {
        try {
            watchDirService.startWatching();
        } catch (InterruptedException exception) {
            log.error(exception.getMessage());
        }
    }
}
