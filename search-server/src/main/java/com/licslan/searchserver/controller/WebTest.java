package com.licslan.searchserver.controller;


import com.licslan.searchserver.entity.Goods;
import com.licslan.searchserver.entity.InfoUser;
import com.licslan.searchserver.entity.PageBean;
import com.licslan.searchserver.req.SaveInfoBody;
import com.licslan.searchserver.req.SearchQueryBody;
import com.licslan.searchserver.response.ResView;
import com.licslan.searchserver.service.GoodService;
import com.licslan.searchserver.service.InfoUserService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@CrossOrigin
public class WebTest {


    private final InfoUserService infoUserService;

    private static final ConcurrentHashMap<String, Object> map = new ConcurrentHashMap<>(16);


    private final GoodService service;

    public WebTest(GoodService service, InfoUserService infoUserService) {
        this.service = service;
        this.infoUserService = infoUserService;
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
        PageBean<InfoUser> data = infoUserService.getList(1, 100);
        if (data.getPages() == 0) return ResView.failed(0, "NO DATA WAS FOUND RELATE TO KEYWORDS");
        return ResView.success(1, "搜索到的结果 :").add("info", data.getPageData().stream().filter(o -> o.getId().toString().equals(id.toString())));

    }


    @PostMapping("/saveInfo")
    public ResView saveInfo(@Validated SaveInfoBody body) {
        infoUserService.save(body);
        return ResView.success(1, "保存成功");

    }

    @PostMapping("/updateInfo")
    public ResView updateInfo(@Validated SaveInfoBody body) {
        infoUserService.update(body);
        return ResView.success(1, "保存成功");

    }


    @GetMapping("/getInfoList")
    public ResView getInfoList() {
        PageBean<InfoUser> data = infoUserService.getList(1, 100);
        if (data.getPages() == 0) return ResView.failed(0, "NO DATA WAS FOUND RELATE TO KEYWORDS");
        return ResView.success(1, "搜索到的结果 :").add("infoList", data);
    }


    @DeleteMapping("/del/{id}")
    public ResView del(@PathVariable Integer id) {
        infoUserService.del(id);
        return ResView.success(1, "删除成功");
    }

    @GetMapping("/getIdMin")
    public ResView getIdMin() {
        InfoUser idMin = infoUserService.getIdMin();
        return ResView.success(1, "搜索最小id").add("idMin", idMin);
    }


}
