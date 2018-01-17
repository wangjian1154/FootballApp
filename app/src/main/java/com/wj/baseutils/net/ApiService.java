package com.wj.baseutils.net;

import com.wj.baseutils.bean.HomeDataBean;
import com.wj.baseutils.bean.HomeTagBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

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


}
