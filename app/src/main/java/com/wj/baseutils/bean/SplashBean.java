package com.wj.baseutils.bean;

/**
 * Created by wj on 2018/3/2.
 */

public class SplashBean {


    /**
     * ret : 0
     * data : {"adUrl":null,"advert":null,"url":"http://cdn.qiudd.net/87_1486713323_1486713325914.jpg","version":65}
     * serverTime : 1519972653303
     */

    public int ret;
    public DataBean data;
    public long serverTime;

    public static class DataBean {
        /**
         * adUrl : null
         * advert : null
         * url : http://cdn.qiudd.net/87_1486713323_1486713325914.jpg
         * version : 65
         */

        public Object adUrl;
        public Object advert;
        public String url;
        public int version;
    }
}
