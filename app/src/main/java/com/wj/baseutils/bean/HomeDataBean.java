package com.wj.baseutils.bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.util.List;

/**
 * Created by wj on 2018/1/15.
 */

public class HomeDataBean {

    public int ret;
    public DataBean data;
    public long serverTime;

    public static class DataBean {

        public String type;
        public ColumnistsMapBean columnistsMap;
        public TopicsMapBean topicsMap;
        public OutsidesMapBean outsidesMap;
        public AdvertsBean adverts;
        public List<PostsBeanX> posts;

        public static class ColumnistsMapBean {

            public int position;
            public List<ColumnistsBean> columnists;

            public static class ColumnistsBean {


                public int id;
                public String name;
                public String avatar;
                public String about;
                public int postsCount;
                public int adminId;
                public int weight;
                public int visibleStatus;
                public long createdAt;
                public long updatedAt;
                public int lastPostId;
                public LastPostBean lastPost;

                public static class LastPostBean {

                    public int id;
                    public String title;
                    public int authorId;
                    public String sourceUrl;
                    public String sourceName;
                    public int commentCount;
                    public int likeCount;
                    public int shareCount;
                    public int visibleStatus;
                    public int channelType;
                    public int imageType;
                    public int itemType;
                    public long time;
                    public long originalPublishDate;
                    public int toTop;
                    public int redirectId;
                    public String redirectStrId;
                    public int redirectType;
                    public String label;
                    public boolean enableComment;
                    public int toHome;
                    public String summary;
                    public String redirectUrl;
                    public int heatValue;
                    public int contentImagesCount;
                    public int recommend;
                    public int videoId;
                    public boolean preload;
                    public String shareImageUrl;
                    public boolean transferPost;
                    public List<String> imageUrls;
                    public List<List<Integer>> imageSizes;
                }
            }
        }

        public static class TopicsMapBean {

            public int position;
            public List<TopicsBean> topics;

            public static class TopicsBean {

                public int id;
                public String name;
                public String avatar;
                public String about;
                public int postsCount;
                public long updatedAt;
                public int lastPostId;
                public LastPostBeanX lastPost;

                public static class LastPostBeanX {


                    public int id;
                    public String title;
                    public int authorId;
                    public String sourceUrl;
                    public String sourceName;
                    public int commentCount;
                    public int likeCount;
                    public int shareCount;
                    public int visibleStatus;
                    public int channelType;
                    public int imageType;
                    public int itemType;
                    public long time;
                    public long originalPublishDate;
                    public int toTop;
                    public int redirectId;
                    public String redirectStrId;
                    public int redirectType;
                    public String label;
                    public boolean enableComment;
                    public int toHome;
                    public String summary;
                    public String redirectUrl;
                    public int heatValue;
                    public int contentImagesCount;
                    public int recommend;
                    public int videoId;
                    public boolean preload;
                    public String shareImageUrl;
                    public boolean transferPost;
                    public List<String> imageUrls;
                    public List<List<Integer>> imageSizes;
                }
            }
        }

        public static class OutsidesMapBean {

            public int position;
            public List<OutsidesBean> outsides;

            public static class OutsidesBean {

                public int id;
                public String title;
                public int authorId;
                public String content;
                public String sourceContent;
                public String sourceUrl;
                public String sourceName;
                public int commentCount;
                public int likeCount;
                public int shareCount;
                public int visibleStatus;
                public int channelType;
                public int imageType;
                public int itemType;
                public long time;
                public long originalPublishDate;
                public int toTop;
                public int redirectId;
                public String redirectStrId;
                public int redirectType;
                public String label;
                public boolean enableComment;
                public int toHome;
                public String summary;
                public String redirectUrl;
                public int heatValue;
                public int contentImagesCount;
                public int recommend;
                public AuthorBean author;
                public int videoId;
                public boolean preload;
                public String shareImageUrl;
                public boolean transferPost;
                public List<String> imageUrls;
                public List<List<Integer>> imageSizes;

                public static class AuthorBean {

                    public int id;
                    public String about;
                    public String avatar;
                    public String nickname;
                    public String sourceName;
                    public int userId;
                    public String name;
                }
            }
        }

        public static class AdvertsBean {
            public List<?> slide;
            public List<PostsBean> posts;

            public static class PostsBean {

                public int id;
                public String name;
                public ExtraBean extra;
                public String targetUrl;
                public int adType;
                public int position;

                public static class ExtraBean {


                    public ImageBean image;
                    public String title;

                    public static class ImageBean {

                        public String width;
                        public String url;
                        public String height;
                    }
                }
            }
        }

        public static class PostsBeanX implements MultiItemEntity {

            /**
             * itemType
             * 1:Item横向普通图文
             * 2:Item纵向大图文
             * 3:Banner
             */
            private int itemType;
            public static final int ITEM_NORMAL = 1;
            public static final int ITEM_BIG_IMG = 2;
            public static final int ITEM_BANNER = 3;


            public PostsBeanX(int itemType) {
                this.itemType = itemType;
            }

            @Override
            public int getItemType() {
                return itemType;
            }

            public boolean enableComment;
            public String redirectUrl;
            public String redirectStrId;
            public int redirectId;
            public int likeCount;
            public int channelType;
            public int toHome;
            public int recommend;
            public String title;
            public int toTop;
            public int contentImagesCount;
            public String sourceUrl;
            public int shareCount;
            public boolean transferPost;
            public String shareImageUrl;
            public long originalPublishDate;
            public int id;
            public int redirectType;
            public int imageType;
            public int heatValue;
            public String summary;
            public String label;
            public int authorId;
            public int commentCount;
            public String sourceName;
            public long time;
            public String content;
            public boolean preload;
            public String preloadContent;
            public PostAuthorBean postAuthor;
            public List<List<Integer>> imageSizes;
            public List<TopicsBeanX> topics;
            public List<?> columnists;
            public List<String> imageUrls;

            public static class PostAuthorBean {

                public int id;
                public String avatar;
                public String nickname;
                public String sourceName;
                public int userId;
                public String name;
            }

            public static class TopicsBeanX {

                public int id;
                public String name;
                public String avatar;
                public String about;
                public int postsCount;
                public long updatedAt;
                public int lastPostId;
            }
        }
    }
}
