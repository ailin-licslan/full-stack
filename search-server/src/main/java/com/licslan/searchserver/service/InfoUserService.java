package com.licslan.searchserver.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.licslan.searchserver.entity.InfoUser;
import com.licslan.searchserver.entity.PageBean;
import com.licslan.searchserver.mapper.InfoUserMapper;
import com.licslan.searchserver.req.SaveInfoBody;
import org.springframework.stereotype.Service;

@Service
public class InfoUserService {


    private final InfoUserMapper dao;

    public InfoUserService(InfoUserMapper dao) {
        this.dao = dao;
    }

    public PageBean<InfoUser> getList(int page, int size){
        Page<InfoUser> goodsPage = PageHelper.startPage(page, size);
        dao.queryAll();
        PageBean<InfoUser> goodsPageBean = new PageBean<>();
        goodsPageBean.setPage(page);
        goodsPageBean.setSize(size);
        goodsPageBean.setKeyword("");
        goodsPageBean.setPages(goodsPage.getPages());
        goodsPageBean.setTotal(goodsPage.getTotal());
        goodsPageBean.setPageData(goodsPage.getResult());
        return goodsPageBean;
    }


    public void save(SaveInfoBody body){
        InfoUser user = new InfoUser();
        user.setAge(body.getAge());
        user.setSex(body.getSex());
        user.setName(body.getName());
        user.setHobby(body.getHobby());
        dao.insert(user);
    }

    public void update(SaveInfoBody body){
        InfoUser user = new InfoUser();
        user.setId(body.getId());
        user.setAge(body.getAge());
        user.setSex(body.getSex());
        user.setName(body.getName());
        user.setHobby(body.getHobby());
        dao.update(user);
    }

    public void del(Integer id){
        dao.delete(id);
    }

    public InfoUser getIdMin() {
        return dao.getIdMin();
    }
}
