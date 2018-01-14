package com.wj.baseutils.model;

import com.wj.base.base.BaseListener;
import com.wj.baseutils.bean.HomeTagBean;
import com.wj.baseutils.contract.HomeContract;
import com.wj.baseutils.net.ApiRetrofit;
import com.wj.baseutils.net.ApiService;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by wj on 2018/1/14.
 */

public class HomeModelImpl implements HomeContract.HomeModel {

    @Override
    public void loadTag(final BaseListener<HomeTagBean> listener) {
        ApiService apiService = ApiRetrofit.getInstance().getApiService();
        Observable<HomeTagBean> tagData = apiService.getTagData();
        Observer<HomeTagBean> observer = new Observer<HomeTagBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(HomeTagBean homeTagBean) {
                listener.onSuccess(homeTagBean);
            }

            @Override
            public void onError(Throwable e) {
                listener.onError(e.getMessage());
            }

            @Override
            public void onComplete() {

            }
        };
        tagData.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }
}
