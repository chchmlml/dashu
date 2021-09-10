//package io.haicheng.stockmonitorcollection;
//
//import io.micrometer.core.instrument.Counter;
//import io.micrometer.core.instrument.MeterRegistry;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import javax.annotation.PostConstruct;
//
///**
// * <p>Title: CounterDemo</p>
// * <p>Description: </p>
// * ----------------------------
// *
// * @author haicheng
// * @date 2021/9/10 2:39 下午
// */
//@RestController
//@Slf4j
//public class CounterDemo {
//
//    @Autowired
//    MeterRegistry registry;
//    /*
//     *  使用Counter.build()创建Counter类型的监控指标，并且通过name()方法定义监控指标的名称network_traffic_input
//     * ，通过labelNames()定义该指标包含的标签。最后通过register()将该指标注册到Collector的defaultRegistry中
//     */
//    Counter counterDemo;
//    @PostConstruct
//    private void init(){
//        counterDemo = registry.counter("counterChanger2", "method", "IndexController.core");
//    }
//
//
//    //指标埋点，定时器会造成普罗米修斯与本地的数据时间戳不同步，尽量不要使用这种方式，实例中的定时器是为了数据演示
//    @Scheduled(cron = "0/5 * * * * ?")
//    @RequestMapping("/changeCounter")
//    public void changeCounter() {
//        log.info("[CounterDemo]->changeCounter");
//        counterDemo.labels("网元", "在线接入", "OCS", "消息计费", "seconds").inc();//指标值增加
//    }
//}