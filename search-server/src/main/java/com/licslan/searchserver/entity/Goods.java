package com.licslan.searchserver.entity;


import lombok.Data;

import java.math.BigDecimal;

@Data
public class Goods {

    private Integer id;
    private String url;
    private BigDecimal price;
    private String title;
    private String shop;
    private String type;

}
