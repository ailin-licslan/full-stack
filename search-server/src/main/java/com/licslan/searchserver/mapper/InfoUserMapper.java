package com.licslan.searchserver.mapper;

import com.licslan.searchserver.entity.InfoUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface InfoUserMapper {

    // 查询所有数据
    List<InfoUser> queryAll();
    // 通过 id 查询数据
    InfoUser queryById(@Param("uid") Integer id);
    // 插入数据
    Integer insert(InfoUser user);
    // 修改数据
    void update(InfoUser user);
    // 通过 id 删除数据
    void delete(Integer id);


}
