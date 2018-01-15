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

    //http://api.qiuduoduo.cn/ttposts?b=&fillContent=true&lazy=true&ps=20
    @GET(ApiConstants.API_HOME_TOP_DATA)//头条
    Observable<HomeDataBean> getHomeData(@Query("b") String b, @Query("fillContent") boolean fillContent,
                                         @Query("lazy") boolean lazy, @Query("ps") int ps);

}
