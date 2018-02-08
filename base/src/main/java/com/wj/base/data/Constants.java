package com.wj.base.data;

/**
 * Created by wj on 2018/1/11.
 */

public class Constants {

    public interface Key {
        String BUNDLE = "BUNDLE";
        String KEY = "key";
    }

    public interface TYPE {
        String TOP_NEWS = "tt";//头条
        String TOP_TRANSFER = "zh";//转会
        String TOP_VIDEO = "sp";//视频
    }

    public interface TribeCategory {
        String TITLE_RECOMMEND = "推荐";
        String TITLE_NEW = "最新";
        String TITLE_TRIBE = "部落";
    }

    public interface Key_EventBus_Msg {
        int NET_CONNECT_SUCCESS = 0;
    }

}
