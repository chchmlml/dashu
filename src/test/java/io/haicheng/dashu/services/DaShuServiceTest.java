package io.haicheng.dashu.services;

import io.haicheng.dashu.domain.entity.Dashu;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

/**
 * <p>Title: StockServiceTest</p>
 * <p>Description: </p>
 * ----------------------------
 *
 * @author haicheng
 * @date 2022/1/26 10:25
 */
@Slf4j
@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DaShuServiceTest {
    @Autowired
    private DashuService dashuService;


    @Test
    public void findAdd(){
        List<Dashu> data = (List<Dashu>) dashuService.findAll();
        log.info("findAll {}", data);
        Assert.notEmpty(data, "查不出");
    }

    @Test
    public void findOne(){
        Optional<Dashu> data = dashuService.findById(1);
        log.info("findOne {}", data.get());
        Assert.isTrue(data.isPresent(), "查不出");
    }

    @Test
    public void testAdd(){

        Dashu d = new Dashu();
        d.setName("测试1");
        d.setTotal(10000.0D);
        d.setCreatedAt(System.currentTimeMillis());
        d.setUpdatedAt(System.currentTimeMillis());

        Dashu result = dashuService.save(d);
        Assert.notNull(result, "插入失败");
    }

}