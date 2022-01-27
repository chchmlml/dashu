package io.haicheng.dashu.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String code;
    private String industry;
    private Integer dashuId;
    private Double pb;
    private Double pbScore;
    private Double peTtm;
    private Double peTtmScore;
    private Double chgScore;
    private String businessReputation;
    private String invisibleAssets;
    private String stockMortgage;
    private String financial;
    private Double roe;
    private Double currentPrice;
    private Double minPrice;
    private Double bugPrice;
    private Double dividend;
    private Double hold;
    private Long createdAt;
    private Long updatedAt;
}
