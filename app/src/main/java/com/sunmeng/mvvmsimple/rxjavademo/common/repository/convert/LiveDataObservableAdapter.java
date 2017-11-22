package com.sunmeng.mvvmsimple.rxjavademo.common.repository.convert;

import android.arch.lifecycle.LiveData;

import com.sunmeng.mvvmsimple.rxjavademo.common.bean.Lcee;

import io.reactivex.Observable;

/**
 * Created by sunmeng on 2017/11/22.
 * Email:sunmeng995@gmail.com
 * Description:
 */

public class LiveDataObservableAdapter {
    public static <T> LiveData<Lcee<T>> fromObservableLcee(final Observable<T> observable) {
        return new ObservableLceeLiveData<>(observable);
    }
}
