package com.licslan.searchserver.controller;


import com.licslan.searchserver.entity.Goods;
import com.licslan.searchserver.entity.PageBean;
import com.licslan.searchserver.req.SaveInfoBody;
import com.licslan.searchserver.req.SearchQueryBody;
import com.licslan.searchserver.response.ResView;
import com.licslan.searchserver.service.GoodService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ConcurrentHashMap;

@RestController
@CrossOrigin
public class WebTest {

    private static final ConcurrentHashMap<String, Object> map = new ConcurrentHashMap<>(16);


    private final GoodService service;

    public WebTest(GoodService service) {
        this.service = service;
    }


    @GetMapping("/getPageList")
    public ResView getList(@Validated SearchQueryBody body) {

        System.out.println("当前查询第几页数据 =====>  " + body.getPage());

        PageBean<Goods> data = service.getList(body.getKeyword(), body.getPage(), body.getSize());


        if (data.getPages() == 0) return ResView.failed(0, "NO DATA WAS FOUND RELATE TO KEYWORDS");


        return ResView.success(1, "搜索到的结果 :").add("pagination", data);

    }

    @GetMapping("/getLinInfo")
    public ResView getLinInfo(@RequestParam Long id) {

        if (id != 1) {
            return ResView.failed(0, "failed");
        }

        //Map<String, Object> map = new HashMap<>();

        if (!map.isEmpty()) {
            return ResView.success(1, "搜索到的结果 :").add("info", map);
        }

        map.put("age", 18);
        map.put("name", "LIN");
        map.put("sex", "男");
        map.put("hobby", "英语,  KEEP LEARNING ,  HOW MANY YEARS YOU HAVE BEEN STUDYING ENGLISH?");


        return ResView.success(1, "搜索到的结果 :").add("info", map);

    }


    @PostMapping("/saveInfo")
    public ResView saveInfo(SaveInfoBody body) {

        System.out.println("当前查询爱好 =====>  " + body.getHobby());

        //Map<String, Object> map = new HashMap<>();

        map.put("age", body.getAge());
        map.put("name", body.getName());
        map.put("sex", body.getSex());
        map.put("hobby", body.getHobby());


        return ResView.success(1, "搜索到的结果 :").add("info", map);

    }


}
