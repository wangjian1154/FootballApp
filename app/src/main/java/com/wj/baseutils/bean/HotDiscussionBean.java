package com.wj.baseutils.bean;

import java.util.List;

/**
 * Created by wj on 2018/1/30.
 */

public class HotDiscussionBean {

    public int ret;
    public DataBean data;
    public long serverTime;

    public static class DataBean {
        public List<DiscussionBean> 正在热议;

        public static class DiscussionBean {
            public int imagesCount;
            public int imageType;
            public int id;
            public String title;
            public int likeCount;
            public int visibleStatus;
            public int groupId;
            public String content;
            public int toHome;
            public String toHomeCover;
            public int toTeam;
            public long toHomeAt;
            public int essence;
            public String atUserIds;
            public long lastEditAt;
            public int heatValue;
            public String title2;
            public int lastCommentId;
            public long lastCommentAt;
            public long recommendWeight;
            public UserBean user;
            public String avatar;
            public SnsGroupBean snsGroup;
            public String groupName;
            public int flag;
            public int authorId;
            public int commentCount;
            public long time;
            public String shortContent;
            public String author;
            public List<String> imageUrls;

            public static class UserBean {
                public int id;
                public String omniauthType;
                public String deviceType;
                public String nickname;
                public String avatar;
                public long createdAt;
                public int gender;
                public int status;
                public int goldCoins;
                public int showCoins;
                public int userValue;
                public String mobileNumber;
                public int level;
                public String identifiedInfo;
            }

            public static class SnsGroupBean {

                public int id;
                public String name;
                public String icon;
                public String cover;
                public int groupType;
                public int teamId;
            }
        }
    }
}
