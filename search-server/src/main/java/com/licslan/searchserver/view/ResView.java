package com.licslan.searchserver.view;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class ResView {

    private int code;

    private boolean success;
    private String message;
    private Map<String, Object> content;

    ResView(int code, boolean success, String message) {
        this.code = code;
        this.success = success;
        this.message = message;
    }

    public static ResView success(int code, String message) {

        return new ResView(code, true, message);
    }

    public static ResView failed(int code, String message) {

        return new ResView(code, false, message);
    }

    public ResView add(String key, Object value) {
        if (this.content == null) {
            this.content = new HashMap<>();
        }
        this.content.put(key, value);
        return this;
    }
}
