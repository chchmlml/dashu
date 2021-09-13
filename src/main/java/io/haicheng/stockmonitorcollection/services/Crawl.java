package io.haicheng.stockmonitorcollection.services;

import java.util.Optional;

/**
 * <p>Title: crawl</p>
 * <p>Description: </p>
 * ----------------------------
 *
 * @author haicheng
 * @date 2021/9/10 9:34 上午
 */
public interface Crawl<T> {
    Optional<T> crawl();
}
