package io.haicheng.stockmonitorcollection.services.impl;

import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import io.haicheng.stockmonitorcollection.domain.constant.ResponseUri;
import io.haicheng.stockmonitorcollection.domain.entity.Industry;
import io.haicheng.stockmonitorcollection.services.Crawl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * <p>Title: IndustryCrawl</p>
 */
@Service
@Slf4j
public class IndustryCrawl implements Crawl<Industry> {

    private static final String PREFIX = "var S_Finance_bankuai_industry = ";

    private Map<String, Industry.Item> industryMap = Maps.newConcurrentMap();

    private static final SimpleDateFormat DATEFORMAT = new SimpleDateFormat("HH:mm:ss");

    public String getResponseBody() {

        Optional<String> body = Optional.ofNullable(HttpRequest.get(ResponseUri.QUOTE_URL).execute().body());
        log.info("[XueqiuCrawl]->getTest {}", body);
        if (body.isPresent()) {
            return StrUtil.removePrefix(body.get(), PREFIX);
        }
        throw new RuntimeException("[IndustryCrawl] body is empty");
    }

    @Override
    public Optional<Industry> crawl() {

        Industry industries = Industry.builder().build();
        industries.setItems(Lists.newArrayList());

        String data = getResponseBody();
        JSONObject maps = JSONUtil.parseObj(data, true);
        if (maps instanceof Map) {
            maps.forEach((k, v) -> {
                List<String> items = StrUtil.split(v.toString(), ",");
                if (items.size() >= 8) {

//                    industries.getItems().add(Industry.Item.builder().code(items.get(0))
//                            .name(items.get(1))
//                            .companyCount(items.get(2))
//                            .price(items.get(3))
//                            .chg(items.get(4))
//                            .rate(items.get(5))
//                            .volume(items.get(6))
//                            .total(items.get(7)).build());

                    Industry.Item item = Industry.Item.builder().code(items.get(0))
                            .name(items.get(1))
                            .companyCount(items.get(2))
                            .price(items.get(3))
                            .chg(items.get(4))
                            .rate(items.get(5))
                            .volume(items.get(6))
                            .total(items.get(7)).build();
                    industryMap.put(item.getCode(), item);
                }
            });
        }


        return Optional.ofNullable(industries);
    }

    @Scheduled(fixedRate = 5000)
    @Override
    public void refresh() {
        crawl();
        log.info("The time is now {}", DATEFORMAT.format(new Date()));
    }

}
