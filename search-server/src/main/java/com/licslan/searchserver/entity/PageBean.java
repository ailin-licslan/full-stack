package com.licslan.searchserver.entity;

import lombok.Data;

import java.util.List;


@Data
public class PageBean<T> {

    private String keyword;
    private int page;
    private int size;
    private int pages;
    private long total;
    private List<T> pageData;

}
