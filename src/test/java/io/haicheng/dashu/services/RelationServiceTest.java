package io.haicheng.dashu.services;

import io.haicheng.dashu.domain.entity.Relation;
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
public class RelationServiceTest {
    @Autowired
    private RelationService relationService;

    @Test
    public void testAdd(){

        Relation d = new Relation();
        d.setDashuId(1);
        d.setStockId(2);
        d.setCreatedAt(System.currentTimeMillis());
        d.setUpdatedAt(System.currentTimeMillis());

        Relation result = relationService.save(d);
        Assert.notNull(result, "插入失败");
    }

    @Test
    public void findAdd(){
        List<Relation> data = (List<Relation>) relationService.findAll();
        log.info("findAll {}", data);
        Assert.notEmpty(data, "查不出");
    }

    @Test
    public void findOne(){
        Optional<Relation> data = relationService.findById(1);
        log.info("findOne {}", data.get());
        Assert.isTrue(data.isPresent(), "查不出");
    }

}