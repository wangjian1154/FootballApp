package com.wj.baseutils.model;

import com.wj.base.base.BaseListener;
import com.wj.baseutils.bean.HomeDataBean;
import com.wj.baseutils.contract.HomeSupportContract;
import com.wj.baseutils.net.ApiRetrofit;
import com.wj.baseutils.net.ApiService;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by wj on 2018/1/15.
 */

public class HomeSupportModelImpl implements HomeSupportContract.HomeSupportModel {

    @Override
    public void loadHomeData(final BaseListener<HomeDataBean> listener) {
        ApiService apiService = ApiRetrofit.getInstance().getApiService();
        Observable<HomeDataBean> homeData = apiService.getHomeData("",true,true,20);
        Observer<HomeDataBean> observer = new Observer<HomeDataBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(HomeDataBean homeDataBean) {
                listener.onSuccess(homeDataBean);
            }

            @Override
            public void onError(Throwable e) {
                listener.onError(e.getMessage());
            }

            @Override
            public void onComplete() {

            }
        };
        homeData.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }
}
