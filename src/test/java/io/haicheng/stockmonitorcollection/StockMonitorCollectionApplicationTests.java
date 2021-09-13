package io.haicheng.stockmonitorcollection;

import io.haicheng.stockmonitorcollection.services.impl.XueqiuCrawl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class StockMonitorCollectionApplicationTests {

    @Test
    void contextLoads() {
    }

    @Autowired
    XueqiuCrawl xueqiuCrawl;

    @Test
    public void testXueqiuCrawl() {

        xueqiuCrawl.getTest();
    }
}
