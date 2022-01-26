package io.haicheng.dashu.controller;

import com.google.common.collect.Lists;
import io.haicheng.dashu.domain.entity.Dashu;
import io.haicheng.dashu.services.DashuService;
import io.haicheng.dashu.util.JsonUtil;
import lombok.Builder;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

/**
 * @author haicheng
 */
@RestController
@RequestMapping("/menu")
public class MenuController {

    @Builder
    @Data
    public static class Element {

        private int id;
        private String text;
        private String iconCls;
        private String state;
        private Attributes attributes;
        private List<Element> children;
    }

    @Builder
    @Data
    public static class Attributes {

        private String url;
    }

    @Autowired
    DashuService dashuService;

    /**
     * 查询当前角色的导航菜单
     */
    @RequestMapping("/loadMenu")
    public String loadMenu() {

        List<Dashu> data = (List<Dashu>) dashuService.findAll();

        List<Element> elements = Lists.newArrayList();

        data.forEach(d -> {
            elements.add(Element.builder()
                    .id(1000 + d.getId())
                    .text(d.getName())
                    .iconCls("menu-11")
                    .state("open")
                    .attributes(Attributes.builder().url("/dashu/index.html?dashu_id=" + d.getId()).build())
                    .build());
        });

        Element dashu = Element.builder()
                .id(10)
                .text("大数汇总")
                .iconCls("menu-1")
                .state("closed")
                .children(elements)
                .build();
        // ==================== level 0 ========================
        Element system = Element.builder()
                .id(1)
                .text("系统菜单")
                .iconCls("menu-plugin")
                .state("closed")
                .children(Arrays.asList(dashu))
                .build();

        return JsonUtil.obj2String(Arrays.asList(system));
    }
}
