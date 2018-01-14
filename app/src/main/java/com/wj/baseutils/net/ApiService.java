package com.wj.baseutils.net;

import com.wj.baseutils.bean.HomeTagBean;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by wj on 2018/1/7.
 */

public interface ApiService {

    @GET(ApiConstants.API_HOME_TAG)
    Observable<HomeTagBean> getTagData();

}
