package io.haicheng.stockmonitorcollection.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * <p>Title: Stock</p>
 * <p>Description: </p>
 * ----------------------------
 *
 * @author haicheng
 * @date 2022/1/13 18:13
 */
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Stock {
    @Id
//    声明一个策略通用生成器，name为”system-uuid”,策略strategy为”uuid”。
    @GenericGenerator(name = "system-uuid", strategy ="uuid")
//    用generator属性指定要使用的策略生成器。
    @GeneratedValue(generator = "system-uuid")
    private String id;
    private String name;
    private String code;
    private String industry;
    private Double pb;
    private Double pbScore;
    private Double peTtm;
    private Double peTtmScore;
    private Double chgScore;
    private Boolean businessReputation;
    private Double invisibleAssets;
    private Boolean stockMortgage;
    private Boolean financial;
    private Double roe;
    private Double current;
    private Double lowCurrentInYear;
    private Double dividend;
    private Integer createdAt;
    private Integer updatedAt;
}
