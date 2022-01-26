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
    private Double currentPrice;
    private Double minPrice;
    private Double bugPrice;
    private Double dividend;
    private Double hold;
    private Long createdAt;
    private Long updatedAt;
}
