//package io.haicheng.stockmonitorcollection;
//
//
//import io.prometheus.client.Counter;
//import io.prometheus.client.Gauge;
//import io.prometheus.client.exporter.PushGateway;
//import org.springframework.context.annotation.AnnotationConfigApplicationContext;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.ComponentScan;
//
//import java.io.IOException;
//import java.util.Random;
//
//
///**
// * @author 菩提树下的杨过(http : / / yjmyzz.cnblogs.com)
// * 利用client写入prometheus示例
// */
//@ComponentScan
//public class SampleApplication {
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
//
//    public static void main(String[] args) throws IOException, InterruptedException {
//        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SampleApplication.class);
//
//        //从spring上下文中取出这些实例
//        Counter counter = context.getBean(Counter.class);
//        Gauge gauge = context.getBean(Gauge.class);
//        PushGateway gateway = context.getBean(PushGateway.class);
//        Random rnd = new Random();
//
//        //粉丝数先预设50
//        gauge.inc(50);
//        while (true) {
//            //随机生成1个blogId
//            int blogId = rnd.nextInt(100000);
//            //该blogId的访问量+1
//            counter.labels(blogId + "").inc();
//            //模拟粉丝数的变化
//            if (blogId % 2 == 0) {
//                gauge.inc();
//            } else {
//                gauge.dec();
//            }
//            //利用网关采集数据
//            gateway.push(counter, "job-counter-test");
//            gateway.push(gauge, "job-gauge-test");
//
//            //辅助输出日志
//            System.out.println("blogId:" + blogId);
//            Thread.sleep(5000);
//        }
//    }
//}