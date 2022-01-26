package io.haicheng.dashu.controller;

import io.haicheng.dashu.domain.entity.Stock;
import io.haicheng.dashu.services.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>Title: HomeController</p>
 * <p>Description: </p>
 * ----------------------------
 *
 * @author haicheng
 * @date 2021/9/9 6:07 下午
 */
@RestController
public class HomeController {

    @RequestMapping(value = {"/metrics/test"}, method = RequestMethod.GET)
    public String minute(HttpServletRequest request) {

        return "everything is ok";
    }

    @Autowired
    private StockService stockService;

    @RequestMapping("/findAll")
    @ResponseBody
    public Iterable<Stock> findAll() {
        return stockService.findAll();
    }
}
