package com.licslan.crudplay.res;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class PeopleDto {

    private Long id;
    private String name;
    private int age;
    private String address;
    private List<String> tags;
    private String createTime;
    private boolean delFlag;

}
