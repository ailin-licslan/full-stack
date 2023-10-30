package com.licslan.crudplay.utils;

public class UtilsTest {


    public static boolean isNum(String str) {
        for (int i = str.length(); --i >= 0; ) {
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }
}
