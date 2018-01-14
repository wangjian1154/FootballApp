package com.wj.baseutils.bean;

import java.util.List;

/**
 * Created by wj on 2018/1/14.
 */

public class HomeTagBean {


    /**
     * ret : 0
     * data : [{"name":"头条","key":"tt","type":1,"pos":65535,"icon":"http://cdn.qiudd.net/toutiao@2x.png"},{"name":"转会","key":"zh","type":2,"pos":65535,"icon":"http://cdn.qiudd.net/zhuanhui@2x.png"},{"name":"中超","key":"zc","type":6,"pos":65535,"icon":"http://cdn.qiudd.net/zhongchao@2x.png"},{"name":"国际","key":"gj","type":6,"pos":3,"icon":"http://cdn.qiudd.net/guoji@2x.png"},{"name":"英超","key":"yc","type":6,"pos":65535,"icon":"http://cdn.qiudd.net/yingchao@2x.png"},{"name":"好彩","key":"hc","type":6,"pos":65535,"icon":"http://cdn.qiudd.net/jingcai@2x.png"},{"name":"西甲","key":"xj","type":6,"pos":65535,"icon":"http://cdn.qiudd.net/xijia@2x.png"},{"name":"超级颜论","key":"cjyl","type":6,"pos":65535,"icon":"http://cdn.qiudd.net/chaojiyanlun@2x.png"},{"name":"意甲","key":"yj","type":6,"pos":65535,"icon":"http://cdn.qiudd.net/yijia@2x.png"},{"name":"德甲","key":"dj","type":6,"pos":65535,"icon":"http://cdn.qiudd.net/dejia@2x.png"},{"name":"视频","key":"sp","type":5,"pos":65535,"icon":"http://cdn.qiudd.net/shipin@2x.png"},{"name":"专栏","key":"zl","type":4,"pos":65535,"icon":"http://cdn.qiudd.net/daka@2x.png"},{"name":"专题","key":"zt","type":4,"pos":65535,"icon":"http://cdn.qiudd.net/youliao@2x.png"},{"name":"欧冠","key":"og","type":6,"pos":65535,"icon":"http://cdn.qiudd.net/ouguan@2x.png"},{"name":"法甲","key":"fj","type":6,"pos":65535,"icon":"http://cdn.qiudd.net/fajia@2x.png"},{"name":"五洲","key":"wz","type":6,"pos":65535,"icon":"http://cdn.qiudd.net/wuzhou@2x.png"},{"name":"青少年","key":"qs","type":6,"pos":65535}]
     * serverTime : 1515928088681
     */

    public int ret;
    public long serverTime;
    public List<DataBean> data;

    public static class DataBean {
        /**
         * name : 头条
         * key : tt
         * type : 1
         * pos : 65535
         * icon : http://cdn.qiudd.net/toutiao@2x.png
         */

        public String name;
        public String key;
        public int type;
        public int pos;
        public String icon;
    }
}
