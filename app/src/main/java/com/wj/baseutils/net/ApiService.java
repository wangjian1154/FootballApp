package com.wj.baseutils.net;

import com.wj.baseutils.bean.CircleBean;
import com.wj.baseutils.bean.HomeDataBean;
import com.wj.baseutils.bean.HomeTagBean;
import com.wj.baseutils.bean.HotDiscussionBean;
import com.wj.baseutils.bean.SplashBean;
import com.wj.baseutils.bean.TribeCategoryBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by wj on 2018/1/7.
 */

public interface ApiService {

    @GET(ApiConstants.API_HOME_TAG)
    Observable<HomeTagBean> getTagData();

    //头条 http://api.qiuduoduo.cn/ttposts?b=&fillContent=true&lazy=true&ps=20
    @GET(ApiConstants.API_HOME_TOP_DATA)
    Observable<HomeDataBean> getTopNewsData(@Query("b") String b
            , @Query("fillContent") boolean fillContent
            , @Query("lazy") boolean lazy
            , @Query("ps") int ps);

    //转会 http://api.qiuduoduo.cn/transfer/posts?b=&t=0
    @GET(ApiConstants.API_HOME_TRANSFER)
    Observable<HomeDataBean> getTransferList(@Query("b") String b, @Query("t") String type);

    //剩余几种 http://api.qiuduoduo.cn/posts?t=zc&b=
    @GET(ApiConstants.API_HOME_OTHER)
    Observable<HomeDataBean> getHomeList(@Query("t") String type, @Query("b") String b);

    //部落推荐-热议
    @GET(ApiConstants.API_TRIBE_RECOMMEND_HOT_DISCUSSION)
    Observable<HotDiscussionBean> getHotDicussion();

    //部落推荐——朋友圈列表
    @GET(ApiConstants.API_TRIBE_RECOMMEND_CIRCLE)
    Observable<CircleBean> getCircleList(@Query("lastId") String lastId,
                                         @Query("pageSize") String pageSize);
    @GET(ApiConstants.API_TRIBE_CATEGORY)
    Observable<TribeCategoryBean> getTribeCategory();

    @GET(ApiConstants.API_SPLASH)
    Observable<SplashBean> getSplashImg();
}
