package com.licslan.searchserver.req;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
public class SearchQueryBody {

    @NotNull(message = "关键字不能为空！")
    private String keyword;
    @Min(value = 1,message = "页码不能小于1")
    @Max(value = 1000, message = "页码不能大于1000")
    private int page;
    @Min(value = 1,message = "数据不能小于1")
    @Max(value = 50, message = "数据不能大于50")
    private int size;

}
