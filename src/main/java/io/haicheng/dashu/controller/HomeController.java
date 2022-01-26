package io.haicheng.dashu.controller;

import io.haicheng.dashu.util.JsonUtil;
import lombok.Builder;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;

/**
 * <p>Title: HomeController</p>
 * <p>Description: </p>
 * ----------------------------
 *
 * @author haicheng
 * @date 2021/9/9 6:07 下午
 */
@Controller
public class HomeController {

    @Autowired
    HttpServletRequest request;

    /**
     * 进入登录页面
     *
     * @return 重定向至登录页面
     */
    @GetMapping("/")
    public String toIndex() {
        return "main.html";
    }
}
