package com.tachcash.feature.presenters;

import com.arellomobile.mvp.InjectViewState;
import com.tachcash.App;
import com.tachcash.base.BasePresenter;
import com.tachcash.feature.views.MainView;
import com.tachcash.utils.RxBusHelper;
import com.tachcash.utils.ThreadSchedulers;
import io.reactivex.disposables.Disposable;
import timber.log.Timber;

/**
 * Created by Alexandra on 15.11.2017.
 */

@SuppressWarnings("ConstantConditions") @InjectViewState public class MainPresenter
    extends BasePresenter<MainView> {

  @Override protected void inject() {
    App.getAppComponent().inject(this);
  }

  @Override protected void onFirstViewAttach() {
    super.onFirstViewAttach();
    getBadgeCount();

    //rxbus
    subscribeUpdateBadgeCount();
  }

  private void getBadgeCount() {
    getViewState().updateBadge(mDataManager.getTemplatesCount());
  }

  private void subscribeUpdateBadgeCount() {
    Disposable disposable = mRxBus.filteredFlowable(RxBusHelper.UpdateBadgeCount.class)
        .compose(ThreadSchedulers.flowableSchedulers())
        .subscribe(UpdateTemplates -> getBadgeCount(), Timber::e);
    addToUndisposable(disposable);
  }
}
