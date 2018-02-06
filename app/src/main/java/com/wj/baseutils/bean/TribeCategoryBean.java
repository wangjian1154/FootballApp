package com.wj.baseutils.bean;

import java.util.List;
import java.util.Map;

/**
 * Created by wj on 2018/2/5.
 */

public class TribeCategoryBean {

    public int ret;
    public DataBean data;
    public long serverTime;

    public static class DataBean {

        //        public CategoryGroupsMapBean categoryGroupsMap;
        public List<CategoriesBean> categories;
        public Map<String, List<CategoryGroupsMapBean.ObjBean>> categoryGroupsMap;

        public static class CategoryGroupsMapBean {

            public static class ObjBean {

                public int id;
                public String name;
                public String icon;
                public String cover;
                public int groupType;
                public int teamId;
            }
        }

        public static class CategoriesBean {

            public int id;
            public String name;
        }
    }
}
