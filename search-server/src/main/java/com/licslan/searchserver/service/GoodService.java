package com.licslan.searchserver.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.licslan.searchserver.entity.Goods;
import com.licslan.searchserver.entity.PageBean;
import com.licslan.searchserver.mapper.GoodsMapper;
import org.springframework.stereotype.Service;

@Service
public class GoodService {


    private final GoodsMapper dao;

    public GoodService(GoodsMapper dao) {
        this.dao = dao;
    }

    public PageBean<Goods> getList(String keyword, int page, int size){

        Page<Goods> goodsPage = PageHelper.startPage(page, size);
        dao.selectByKeyword(keyword);



        PageBean<Goods> goodsPageBean = new PageBean<>();

        goodsPageBean.setPage(page);
        goodsPageBean.setSize(size);
        goodsPageBean.setKeyword(keyword);


        goodsPageBean.setPages(goodsPage.getPages());
        goodsPageBean.setTotal(goodsPage.getTotal());
        goodsPageBean.setPageData(goodsPage.getResult());

        return goodsPageBean;
    }

}
