package com.wj.baseutils.bean;

/**
 * Created by wj on 2018/3/6.
 */

public class LinkmanBean {

    public String name;
    public String mobile;

    private String pinyin;
    private String firstLetter;

    public String getPinyin() {
        return pinyin;
    }

    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }

    public String getFirstLetter() {
        return firstLetter;
    }

    public void setFirstLetter(String firstLetter) {
        this.firstLetter = firstLetter;
    }
}
