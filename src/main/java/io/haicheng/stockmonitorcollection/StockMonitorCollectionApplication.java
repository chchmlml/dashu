package io.haicheng.stockmonitorcollection;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class StockMonitorCollectionApplication {

    public static void main(String[] args) {
        SpringApplication.run(StockMonitorCollectionApplication.class, args);
    }

}
