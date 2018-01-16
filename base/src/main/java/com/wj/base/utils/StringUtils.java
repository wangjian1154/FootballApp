package com.wj.base.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by wj on 2018/1/9.
 * 字符串工具类
 */

public class StringUtils {

    //判断字符串是否为空
    public static boolean isEmpty(String input) {
        if (input == null || "".equals(input))
            return true;
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (c != ' ' && c != '\t' && c != '\r' && c != '\n') {
                return false;
            }
        }
        return true;
    }

    //判断字符串是否为邮箱
    public static boolean isEmail(String email) {
        String str = "^([a-zA-Z0-9]*[-_]?[a-zA-Z0-9]+)*@([a-zA-Z0-9]*[-_]?[a-zA-Z0-9]+)+[\\.][A-Za-z]{2,3}([\\" +
                ".][A-Za-z]{2})?$";
        Pattern p = Pattern.compile(str);
        Matcher m = p.matcher(email);
        return m.matches();
    }

    //字符串如果为空返回"",否则返回实际值
    public static String setStr(String text) {
        return isEmpty(text) ? "" : text;
    }

}
