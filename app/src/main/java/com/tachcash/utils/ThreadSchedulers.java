package com.tachcash.utils;

import io.reactivex.FlowableTransformer;
import io.reactivex.MaybeTransformer;
import io.reactivex.ObservableTransformer;
import io.reactivex.SingleTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * Created by Alexandra on 24.06.2017.
 */

public class ThreadSchedulers {

  public static <T> ObservableTransformer<T, T> observableSchedulers() {
    return observable -> observable.subscribeOn(io.reactivex.schedulers.Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread());
  }

  public static <T> FlowableTransformer<T, T> flowableSchedulers() {
    return observable -> observable.subscribeOn(io.reactivex.schedulers.Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread());
  }

  public static <T> SingleTransformer<T, T> singleSchedulers() {
    return observable -> observable.subscribeOn(io.reactivex.schedulers.Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread());
  }

  public static <T> MaybeTransformer<T, T> maybeSchedulers() {
    return observable -> observable.subscribeOn(io.reactivex.schedulers.Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread());
  }
}
