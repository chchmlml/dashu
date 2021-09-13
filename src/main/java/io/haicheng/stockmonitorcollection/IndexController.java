package io.haicheng.stockmonitorcollection;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

/**
 * <p>Title: IndexController</p>
 * <p>Description: </p>
 * ----------------------------
 *
 * @author haicheng
 * @date 2021/9/10 6:16 下午
 */
@RestController
@RequestMapping("/v1")
public class IndexController {

    @Autowired
    MeterRegistry registry;

    private Counter counter_core;
    private Counter counter_index;

    @PostConstruct
    private void init() {
        counter_core = registry.counter("app_requests_method_count", "method", "IndexController.core");
        counter_index = registry.counter("app_requests_method_count", "method", "IndexController.index");
    }

    @RequestMapping(value = "/index")
    public Object index() {
        try {
            counter_index.increment();
        } catch (Exception e) {
            return e;
        }
        return counter_index.count() + " index of springboot2-prometheus.";
    }

    @RequestMapping(value = "/core")
    public Object coreUrl() {
        try {
            counter_core.increment();
        } catch (Exception e) {
            return e;
        }
        return counter_core.count() + " coreUrl Monitor by Prometheus.";
    }
}
