package com.tachcash.utils;

import com.jakewharton.rxrelay2.PublishRelay;
import com.jakewharton.rxrelay2.Relay;
import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.Observable;

/**
 * Created by Alexandra on 06.06.2017.
 */

public class RxBus {

  private final Relay<Object> mBusRelay = PublishRelay.create().toSerialized();

  public void post(Object event) {
    mBusRelay.accept(event);
  }

  public <T> Flowable<T> filteredFlowable(final Class<T> eventClass) {
    return mBusRelay.ofType(eventClass).toFlowable(BackpressureStrategy.LATEST);
  }

  public <T> Observable<T> filteredObservable(final Class<T> eventClass) {
    return mBusRelay.ofType(eventClass);
  }

  public boolean hasObservers() {
    return mBusRelay.hasObservers();
  }
}
