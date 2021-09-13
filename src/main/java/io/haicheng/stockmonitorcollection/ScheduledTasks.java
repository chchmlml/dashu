package io.haicheng.stockmonitorcollection;

import io.haicheng.stockmonitorcollection.domain.entity.Industry;
import io.haicheng.stockmonitorcollection.services.Crawl;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
public class ScheduledTasks {

    private static final Logger LOGGER = LoggerFactory.getLogger(ScheduledTasks.class);

    private static final SimpleDateFormat DATEFORMAT = new SimpleDateFormat("HH:mm:ss");

    private static final ExecutorService POOL = Executors.newFixedThreadPool(1);

    @Autowired
    MeterRegistry registry;

    private Counter counterCore;

    @PostConstruct
    private void init() {
        counterCore = registry.counter("app_requests_method_count", "method", "IndexController.core");
    }

    @Autowired
    Crawl<Industry> industryCrawl;

    @Scheduled(fixedRate = 5000)
    public void sinaIndustryTask() {
        LOGGER.info("The time is now {}", DATEFORMAT.format(new Date()));


        POOL.execute(() -> {
            try {

                Optional<Industry> data = industryCrawl.crawl();
                if (data.isPresent()) {
                    LOGGER.info("[ScheduledTasks]->sinaIndustryTask i {}", data.get());
                }

                counterCore.increment();
            } catch (Exception e) {
                LOGGER.error("[ScheduledTasks]->sinaIndustryTask error: {}", e.getMessage());
            }
        });

    }
}