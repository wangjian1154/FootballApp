package com.wj.baseutils.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by wj on 2018/2/5.
 */

public class TribeCategory {

    public int ret;
    public DataBean data;
    public long serverTime;

    public static class DataBean {

        public CategoryGroupsMapBean categoryGroupsMap;
        public List<CategoriesBean> categories;

        public static class CategoryGroupsMapBean {
            @SerializedName("1")
            public List<_$1Bean> _$1;
            @SerializedName("2")
            public List<_$2Bean> _$2;
            @SerializedName("3")
            public List<_$3Bean> _$3;
            @SerializedName("4")
            public List<_$4Bean> _$4;
            @SerializedName("5")
            public List<_$5Bean> _$5;
            @SerializedName("6")
            public List<_$6Bean> _$6;
            @SerializedName("7")
            public List<_$7Bean> _$7;
            @SerializedName("8")
            public List<_$8Bean> _$8;
            @SerializedName("9")
            public List<_$9Bean> _$9;

            public static class _$1Bean {

                public int id;
                public String name;
                public String icon;
                public String cover;
                public int groupType;
                public int teamId;
            }

            public static class _$2Bean {

                public int id;
                public String name;
                public String icon;
                public String cover;
                public int groupType;
                public int teamId;
            }

            public static class _$3Bean {
                public int id;
                public String name;
                public String icon;
                public String cover;
                public int groupType;
                public int teamId;
            }

            public static class _$4Bean {
                public int id;
                public String name;
                public String icon;
                public String cover;
                public int groupType;
                public int teamId;
            }

            public static class _$5Bean {
                public int id;
                public String name;
                public String icon;
                public String cover;
                public int groupType;
                public int teamId;
            }

            public static class _$6Bean {

                public int id;
                public String name;
                public String icon;
                public String cover;
                public int groupType;
                public int teamId;
            }

            public static class _$7Bean {
                public int id;
                public String name;
                public String icon;
                public String cover;
                public int groupType;
                public int teamId;
            }

            public static class _$8Bean {
                public int id;
                public String name;
                public String icon;
                public String cover;
                public int groupType;
                public int teamId;
            }

            public static class _$9Bean {

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
