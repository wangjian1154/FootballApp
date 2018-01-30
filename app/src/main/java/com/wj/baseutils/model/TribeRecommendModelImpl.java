package com.wj.baseutils.model;

import com.wj.base.base.BaseListener;
import com.wj.baseutils.bean.HotDiscussionBean;
import com.wj.baseutils.contract.TribeRecommendContract;
import com.wj.baseutils.net.ApiRetrofit;
import com.wj.baseutils.net.ApiService;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by wj on 2018/1/30.
 * 部落推荐热议
 */

public class TribeRecommendModelImpl implements TribeRecommendContract.TribeRecommendModel {

    @Override
    public void loadDiscussuion(final BaseListener<HotDiscussionBean> listener) {
        ApiService apiService = ApiRetrofit.getInstance().getApiService();
        Observable<HotDiscussionBean> hotDicussion = apiService.getHotDicussion();
        Observer<HotDiscussionBean> observer = new Observer<HotDiscussionBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(HotDiscussionBean discussionBean) {
                listener.onSuccess(discussionBean);
            }

            @Override
            public void onError(Throwable e) {
                listener.onError(e.getMessage());
            }

            @Override
            public void onComplete() {

            }
        };
        hotDicussion.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }
}
