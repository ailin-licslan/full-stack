package com.licslan.searchserver.req;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
public class SaveInfoBody {


    @Min(value = 1,message = "年龄不能小于1")
    @Max(value = 150, message = "年龄不能大于150")
    private int age;

    @NotNull(message = "关键字不能为空！")
    private String name;

    @NotNull(message = "关键字不能为空！")
    private String hobby;
    @NotNull(message = "关键字不能为空！")
    private String sex;
}
