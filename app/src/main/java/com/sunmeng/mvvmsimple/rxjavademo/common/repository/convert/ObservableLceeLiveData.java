package com.sunmeng.mvvmsimple.rxjavademo.common.repository.convert;

import android.arch.lifecycle.LiveData;

import com.sunmeng.mvvmsimple.rxjavademo.common.bean.Lcee;

import java.lang.ref.WeakReference;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

/**
 * Created by sunmeng on 2017/11/22.
 * Email:sunmeng995@gmail.com
 * Description:
 */

public class ObservableLceeLiveData <T> extends LiveData<Lcee<T>> {

    private WeakReference<Disposable> mDisposableRef;
    private final Observable<T> mObservable;
    private final Object mLock = new Object();

    ObservableLceeLiveData(@NonNull final Observable<T> observable) {
        mObservable = observable;
    }

    @Override
    protected void onActive() {
        super.onActive();
        mObservable.subscribe(new Observer<T>() {
            @Override
            public void onSubscribe(Disposable d) {
                synchronized (mLock) {
                    mDisposableRef = new WeakReference<>(d);
                }
                postValue(Lcee.loading());//通知UI刷新
            }

            @Override
            public void onNext(T t) {
                if (null == t)
                    postValue(Lcee.empty());
                else
                    postValue(Lcee.content(t));
            }

            @Override
            public void onError(Throwable e) {
                synchronized (mLock) {
                    mDisposableRef = null;
                }
                postValue(Lcee.error(e));
            }

            @Override
            public void onComplete() {
                synchronized (mLock) {
                    mDisposableRef = null;
                }
            }
        });
    }

    @Override
    protected void onInactive() {
        super.onInactive();
        synchronized (mLock) {
            WeakReference<Disposable> subscriptionRef = mDisposableRef;
            if (subscriptionRef != null) {
                Disposable subscription = subscriptionRef.get();
                if (subscription != null) {
                    subscription.dispose();
                }
                mDisposableRef = null;
            }
        }
    }
}
