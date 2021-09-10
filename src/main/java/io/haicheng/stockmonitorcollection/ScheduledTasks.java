//package io.haicheng.stockmonitorcollection;
//
//import io.prometheus.client.Counter;
//import io.prometheus.client.Gauge;
//import io.prometheus.client.exporter.PushGateway;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.stereotype.Component;
//
//import javax.annotation.PostConstruct;
//import java.io.IOException;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import java.util.Random;
//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.Executors;
//
//@Component
//public class ScheduledTasks {
//
//    private static final Logger LOGGER = LoggerFactory.getLogger(ScheduledTasks.class);
//
//    private static final SimpleDateFormat DATEFORMAT = new SimpleDateFormat("HH:mm:ss");
//
//    @Autowired
//    private PushGateway gateway;
//
//    @Autowired
//    private Counter counter;
//
//    @Autowired
//    private Gauge gauge;
//
//    private static final ExecutorService POOL = Executors.newFixedThreadPool(1);
//
//    //    @Scheduled(fixedRate = 5000)
//    @PostConstruct
//    public void reportCurrentTime() {
//        LOGGER.info("The time is now {}", DATEFORMAT.format(new Date()));
//
//        Random rnd = new Random();
//
//
//        //粉丝数先预设50
//        gauge.inc(50);
//
//        POOL.execute(() -> {
//            try {
//
//                while (true) {
//                    //随机生成1个blogId
//                    int blogId = rnd.nextInt(100000);
//                    //该blogId的访问量+1
//                    counter.labels(blogId + "").inc();
//                    //模拟粉丝数的变化
//                    if (blogId % 2 == 0) {
//                        gauge.inc();
//                    } else {
//                        gauge.dec();
//                    }
//                    //利用网关采集数据
//
//                    gateway.push(counter, "job-counter-test");
//                    gateway.push(gauge, "job-gauge-test");
//
//                    //辅助输出日志
//                    LOGGER.info("blogId:" + blogId);
//                    Thread.sleep(5000);
//                }
//            } catch (IOException | InterruptedException e) {
//                e.printStackTrace();
//            }
//        });
//
//    }
//
//    /**
//     * push网关
//     *
//     * @return
//     */
//    @Bean
//    public PushGateway getPushGateway() {
//        return new PushGateway("localhost:19090");
//    }
//
//    /**
//     * counter实例
//     *
//     * @return
//     */
//    @Bean
//    public Counter getCounter() {
//        return Counter.build()
//                .name("blog_visit") //这里模拟博客访问量
//                .labelNames("blog_id") //博客id
//                .help("counter_blog_visit") //这个名字随便起
//                .register(); //注：通常只能注册1次，1个实例中重复注册会报错
//    }
//
//    @Bean
//    public Gauge getGauge() {
//        return Gauge.build()
//                .name("blog_fans") //这里模拟粉丝数(注：这里我们没设置label)
//                .help("gauge_blog_fans")
//                .register();
//    }
//}