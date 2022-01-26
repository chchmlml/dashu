package io.haicheng.dashu.domain.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * <p>Title: Industry</p>
 * <p>Description: </p>
 * ----------------------------
 *
 * @author haicheng
 * @date 2021/9/13 5:58 下午
 */
@Builder
@Setter
@Getter
public class Industry {
    List<Item> items;

    @Builder
    @Getter
    public static class Item implements Comparable<Item> {
        private String code;
        private String name;
        private String companyCount;
        private String price;
        //涨跌额
        private String chg;
        //涨跌幅
        private String rate;
        //总成交量
        private String volume;
        //总成交额
        private String total;

        @Override
        public int compareTo(Item o) {
            return this.getCode().equals(o.getCode()) ? 0 : -1;
        }
    }
}
