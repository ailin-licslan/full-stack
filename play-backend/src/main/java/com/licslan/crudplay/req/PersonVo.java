package com.licslan.crudplay.req;

import lombok.Data;

@Data
public class PersonVo {
    private Long id;
    private String name;
    private int age;
    private boolean sex;
}
