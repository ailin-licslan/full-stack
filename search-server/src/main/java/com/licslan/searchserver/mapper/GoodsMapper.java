package com.licslan.searchserver.mapper;

import com.licslan.searchserver.entity.Goods;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface GoodsMapper {

    List<Goods> selectByKeyword(String keyword);
}
