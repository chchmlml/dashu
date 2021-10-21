package io.haicheng.stockmonitorcollection.services.impl;

import cn.hutool.http.HttpRequest;
import io.haicheng.stockmonitorcollection.services.Crawl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * <p>Title: XueqiuCrawl</p>
 * <p>Description: </p>
 * ----------------------------
 *
 * @author haicheng
 * @date 2021/9/10 9:35 上午
 */
@Service
@Slf4j
public class XueqiuCrawl implements Crawl<Object> {

    private static final String QUOTE_URL = "https://stock.xueqiu.com/v5/stock/quote.json?symbol=SH601688";

    public void getTest() {

        String body = HttpRequest.get(QUOTE_URL).execute().body();
        log.info("[XueqiuCrawl]->getTest {}", body);
    }

    @Override
    public Optional<Object> crawl() {
        return Optional.empty();
    }

    @Override
    public void refresh() {

    }
}
