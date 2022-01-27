package io.haicheng.dashu.controller;

import io.haicheng.dashu.domain.entity.Dashu;
import io.haicheng.dashu.domain.entity.Stock;
import io.haicheng.dashu.domain.vo.ServiceVO;
import io.haicheng.dashu.services.DashuService;
import io.haicheng.dashu.services.StockService;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.google.common.collect.Maps;

/**
 * @description 基金Controller控制器
 */
@RestController
@RequestMapping("/dashu")
public class DashuController {

    @Autowired
    private DashuService dashuService;

    @Autowired
    private StockService stockService;

    /**
     * 分页查询供应商
     *
     * @param page 当前页数
     * @param rows 每页显示的记录数
     */
    @RequestMapping("/list")
    public Map<String, Object> list(Integer page,
            Integer rows,
            @RequestParam(name = "dashu_id", defaultValue = "") Integer dashuId) {

        Map<String, Object> map = new HashMap<>();
        // page = (null == page || page == 0) ? 1 : page;
        // rows = (null == rows || rows == 0) ? 20 : rows;
        // int offSet = (page - 1) * rows;
        map.put("total", 100);
        map.put("rows", stockService.findAddByDashuId(dashuId));
        return map;
    }

    @RequestMapping("/save")
    public ServiceVO<Stock> save(Stock stock) {

        if (null == stock.getId()) {
            stock.setCurrentPrice(0.0D);
            stock.setCreatedAt(System.currentTimeMillis());
            stock.setUpdatedAt(System.currentTimeMillis());
        } else {

            Optional<Stock> current = stockService.findById(stock.getId());
            if (!current.isPresent()) {
                return new ServiceVO(ServiceVO.FAIL_CODE, ServiceVO.FAIL_MESS);
            }
            stock.setCurrentPrice(current.get().getCurrentPrice());
            stock.setCreatedAt(current.get().getCreatedAt());
            stock.setUpdatedAt(System.currentTimeMillis());
        }

        Stock result = stockService.save(stock);

        return new ServiceVO(result);
    }

    @RequestMapping("/delete")
    public ServiceVO delete(String ids) {

        String[] idArray = StringUtils.split(ids, ",");
        for (String id : idArray) {
            stockService.deleteById(Integer.parseInt(id));
        }

        return new ServiceVO();
    }

    /**
     * 查询下拉框客户信息
     * 
     * @param q 客户名称
     * @return
     */
    @RequestMapping("/getComboboxListDashu")
    public List<Map> getComboboxList(String q) {

        List<Dashu> dashu = (List<Dashu>) dashuService.findAll();

        return dashu.stream().map(d -> {
            Map<String, Object> m = Maps.newHashMap();
            m.put("id", d.getId());
            m.put("name", d.getName());
            return m;
        }).collect(Collectors.toList());
    }
}
