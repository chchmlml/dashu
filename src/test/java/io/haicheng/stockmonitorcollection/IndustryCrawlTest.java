package io.haicheng.stockmonitorcollection;

import cn.hutool.core.lang.Assert;
import io.haicheng.stockmonitorcollection.domain.entity.Industry;
import io.haicheng.stockmonitorcollection.services.impl.IndustryCrawl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

/**
 * <p>Title: IndustryCrawlTest</p>
 * <p>Description: </p>
 * ----------------------------
 *
 * @author haicheng
 * @date 2021/9/13 5:52 下午
 */

@RunWith(MockitoJUnitRunner.class)
public class IndustryCrawlTest {

    @InjectMocks
    IndustryCrawl industryCrawl;

    @Test
    public void testCrawl() {
        Optional<Industry> data = industryCrawl.crawl();
        Assert.isTrue(data.isPresent());
        Assert.notEmpty(data.get().getItems());
    }
}
