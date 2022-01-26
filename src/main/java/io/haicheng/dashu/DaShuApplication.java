package io.haicheng.dashu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class DaShuApplication {

    public static void main(String[] args) {
        SpringApplication.run(DaShuApplication.class, args);
    }

}
