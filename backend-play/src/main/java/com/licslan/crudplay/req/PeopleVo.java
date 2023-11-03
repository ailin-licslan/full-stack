package com.licslan.crudplay.req;

import lombok.Data;

@Data
public class PeopleVo {
    private Long id;
    private String name;
    private int age;
    private String address;
    private String createTime;
    private String tags;
}
