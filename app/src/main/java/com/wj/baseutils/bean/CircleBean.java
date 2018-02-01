package com.wj.baseutils.bean;

import java.util.List;

/**
 * Created by wj on 2018/2/1.
 */

public class CircleBean {


    public int ret;
    public long serverTime;
    public List<DataBean> data;

    public static class DataBean {

        public int imagesCount;
        public int id;
        public String title;
        public int likeCount;
        public int visibleStatus;
        public int groupId;
        public String content;
        public int toHome;
        public String toHomeCover;
        public int toTeam;
        public int essence;
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
        public int imageType;
        public String atUserIds;
        public List<String> imageUrls;
        public List<HotCommentsBean> hotComments;

        public static class UserBean {
            public int id;
            public String omniauthType;
            public String deviceType;
            public String nickname;
            public String avatar;
            public String city;
            public String about;
            public long createdAt;
            public int gender;
            public int status;
            public int goldCoins;
            public int showCoins;
            public int userValue;
            public int level;
            public String identifiedInfo;
            public String intro;
        }

        public static class SnsGroupBean {

            public int id;
            public String name;
            public String icon;
            public String cover;
            public int groupType;
            public int teamId;
        }

        public static class HotCommentsBean {

            public String images;
            public int imagesCount;
            public int imageType;
            public int id;
            public String content;
            public int snsPostId;
            public int userId;
            public int likeCount;
            public int visibleStatus;
            public int parentId;
            public int floor;
            public int editorId;
            public int hot;
            public long createdAt;
            public long updatedAt;
            public int replyCount;
            public String atUserIds;
            public UserBeanX user;
            public boolean author;

            public static class UserBeanX {


                public int id;
                public String nickname;
                public String avatar;
            }
        }
    }
}
