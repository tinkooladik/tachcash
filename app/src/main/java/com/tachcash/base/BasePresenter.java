package com.tachcash.base;

import android.content.Context;
import android.support.annotation.NonNull;
import com.arellomobile.mvp.MvpPresenter;
import com.arellomobile.mvp.MvpView;
import com.tachcash.data.DataManager;
import com.tachcash.utils.RxBus;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import javax.inject.Inject;

/**
 * Created by Alexandra on 11/3/2017.
 */

public abstract class BasePresenter<V extends MvpView> extends MvpPresenter<V> {

  @Inject protected RxBus mRxBus;
  @Inject protected DataManager mDataManager;
  @Inject protected Context mContext;

  private CompositeDisposable mCompositeDisposable = new CompositeDisposable();

  public BasePresenter() {
    inject();
  }

  protected void addToUndisposable(@NonNull Disposable disposable) {
    mCompositeDisposable.add(disposable);
  }

  @Override public void onDestroy() {
    super.onDestroy();
    mCompositeDisposable.clear();
  }

  protected abstract void inject();
}
