package com.licslan.searchserver.controller;


import com.licslan.searchserver.entity.Goods;
import com.licslan.searchserver.entity.PageBean;
import com.licslan.searchserver.req.SearchQueryBody;
import com.licslan.searchserver.service.GoodService;
import com.licslan.searchserver.response.ResView;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class WebTest {


    private final GoodService service;

    public WebTest(GoodService service) {
        this.service = service;
    }


    @GetMapping("/getPageList")
    public ResView getList(@Validated SearchQueryBody body) {

        System.out.println("当前查询第几页数据 =====>  "+ body.getPage());

        PageBean<Goods> data = service.getList(body.getKeyword(), body.getPage(), body.getSize());


        if (data.getPages() == 0) return ResView.failed(0, "NO DATA WAS FOUND RELATE TO KEYWORDS");


        return ResView.success(1, "搜索到的结果 :").add("pagination", data);

    }


}
