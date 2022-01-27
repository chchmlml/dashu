package io.haicheng.dashu.services;

import io.haicheng.dashu.domain.entity.Stock;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

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
public class StockServiceTest {
    @Autowired
    private StockService stockService;


    @Test
    public void findByDashuId(){
        List<Stock> data = (List<Stock>) stockService.findAddByDashuId(1314);
        log.info("findAll {}", data);
        Assert.notEmpty(data, "查不出");
        List<Stock> data2 = (List<Stock>) stockService.findAddByDashuId(1);
        Assert.isTrue(CollectionUtils.isEmpty(data2), "查出来了");
    }

    @Test
    public void findAdd(){
        List<Stock> data = (List<Stock>) stockService.findAll();
        log.info("findAll {}", data);
        Assert.notEmpty(data, "查不出");
    }

    @Test
    public void findOne(){
        Optional<Stock> data = stockService.findById(1);
        log.info("findOne {}", data.get());
        Assert.isTrue(data.isPresent(), "查不出");
    }

    @Test
    public void testAdd(){

        Stock stock = new Stock();
        stock.setName("测试");
        stock.setCode("123");
        stock.setIndustry("建筑材料");
        stock.setDashuId(1314);

        stock.setPb(1.00D);
        stock.setPbScore(100.00D);
        stock.setPeTtm(1.0D);
        stock.setPeTtmScore(100.00D);
        stock.setChgScore(100.0D);
        stock.setBusinessReputation("PASS");
        stock.setInvisibleAssets("0.1"); //百分比
        stock.setStockMortgage("PASS");
        stock.setFinancial("PASS");
        stock.setRoe(3.1D);
        stock.setCurrentPrice(10.0D);
        stock.setMinPrice(9.0D);
        stock.setBugPrice(19.0D);
        stock.setDividend(9.0D);
        stock.setHold(10.0D);
        stock.setCreatedAt(System.currentTimeMillis());
        stock.setUpdatedAt(System.currentTimeMillis());

        Stock result = stockService.save(stock);
        Assert.notNull(result, "插入失败");
    }

}