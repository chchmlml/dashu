package io.haicheng.dashu.controller;

import io.haicheng.dashu.services.DashuService;
import io.haicheng.dashu.services.RelationService;
import io.haicheng.dashu.services.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @description 基金Controller控制器
 */
@RestController
@RequestMapping("/dashu")
public class DashuController {

    @Autowired
    private DashuService dashuService;

    @Autowired
    private RelationService relationService;

    @Autowired
    private StockService stockService;

    /**
     * 分页查询供应商
     *
     * @param page 当前页数
     * @param rows 每页显示的记录数
     * @param name 供应商名
     * @param date 日期
     */
    @RequestMapping("/list")
    public Map<String, Object> list(Integer page,
            Integer rows,
            @RequestParam(name = "name", defaultValue = "") String name,
            @RequestParam(name = "date", defaultValue = "") String date) {

        Map<String, Object> map = new HashMap<>();
        page = (null == page || page == 0) ? 1 : page;
        rows = (null == rows || rows == 0) ? 20 : rows;
        int offSet = (page - 1) * rows;
        map.put("total", 10);
        map.put("rows", stockService.findAll());
        return map;
    }

//    /**
//     * 查询下拉框客户信息
//     * @param q 客户名称
//     * @return
//     */
//    @RequestMapping("/getComboboxListDate")
//    public List<Map> getComboboxList(String q) {
//        return service.getComboboxListDate(q);
//    }
}
