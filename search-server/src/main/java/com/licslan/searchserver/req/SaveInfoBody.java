package com.licslan.searchserver.req;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
public class SaveInfoBody {



    private int age;

    private String name;

    private String hobby;

    private String sex;
}
