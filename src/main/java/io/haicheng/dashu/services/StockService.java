package io.haicheng.dashu.services;

import io.haicheng.dashu.domain.entity.Stock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * <p>Title: Stock</p>
 * <p>Description: </p>
 * ----------------------------
 *
 * @author haicheng
 * @date 2022/1/13 18:12
 */
public interface StockService extends CrudRepository<Stock, Integer> {

    @Query(value = "select * from stock where dashu_id = ?1", nativeQuery = true)
    List<Stock> findAddByDashuId(Integer dashuId);
}
