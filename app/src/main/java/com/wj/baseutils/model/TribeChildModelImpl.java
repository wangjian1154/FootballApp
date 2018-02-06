package com.wj.baseutils.model;

import com.wj.base.base.BaseListener;
import com.wj.baseutils.bean.TribeCategoryBean;
import com.wj.baseutils.contract.TribeChildContract;
import com.wj.baseutils.net.ApiRetrofit;
import com.wj.baseutils.net.ApiService;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by wj on 2018/1/26.
 */

public class TribeChildModelImpl implements TribeChildContract.TribeChildModel {
    @Override
    public void loadCategory(final BaseListener<TribeCategoryBean> listener) {
        ApiService apiService = ApiRetrofit.getInstance().getApiService();
        Observable<TribeCategoryBean> tribeCategory = apiService.getTribeCategory();

        Observer<TribeCategoryBean> observer = new Observer<TribeCategoryBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(TribeCategoryBean tribeCategory) {
                listener.onSuccess(tribeCategory);
            }

            @Override
            public void onError(Throwable e) {
                listener.onError(e.getMessage());
            }

            @Override
            public void onComplete() {

            }
        };
        tribeCategory.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }
}
