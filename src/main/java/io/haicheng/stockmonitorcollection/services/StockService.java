package io.haicheng.stockmonitorcollection.services;

import io.haicheng.stockmonitorcollection.domain.entity.Stock;
import org.springframework.data.repository.CrudRepository;

/**
 * <p>Title: Stock</p>
 * <p>Description: </p>
 * ----------------------------
 *
 * @author haicheng
 * @date 2022/1/13 18:12
 */
public interface StockService extends CrudRepository<Stock, String> {
}
