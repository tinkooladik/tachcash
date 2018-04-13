package com.tachcash.feature.presenters;

import com.arellomobile.mvp.InjectViewState;
import com.tachcash.App;
import com.tachcash.base.BasePresenter;
import com.tachcash.feature.views.TemplatesView;
import com.tachcash.utils.RxBusHelper;
import com.tachcash.utils.ThreadSchedulers;
import io.reactivex.disposables.Disposable;
import timber.log.Timber;

/**
 * Created by Alexandra on 11/8/2017.
 */

@InjectViewState public class TemplatesPresenter extends BasePresenter<TemplatesView> {

  @Override protected void inject() {
    App.getAppComponent().inject(this);
  }

  @Override protected void onFirstViewAttach() {
    super.onFirstViewAttach();
    getTemplates();

    //rxbus
    subscribeUpdateTemplates();
  }

  public void getTemplates() {
    getViewState().setUpTemplates(mDataManager.getTemplates());
  }

  private void subscribeUpdateTemplates() {
    Disposable disposable = mRxBus.filteredFlowable(RxBusHelper.UpdateTemplates.class)
        .compose(ThreadSchedulers.flowableSchedulers())
        .subscribe(UpdateTemplates -> getTemplates(), Timber::e);
    addToUndisposable(disposable);
  }
}
