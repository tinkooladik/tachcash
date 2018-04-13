package com.tachcash.feature.presenters;

import com.arellomobile.mvp.InjectViewState;
import com.tachcash.App;
import com.tachcash.R;
import com.tachcash.base.BasePresenter;
import com.tachcash.feature.views.CatalogChildView;
import com.tachcash.utils.ErrorsUtils;
import com.tachcash.utils.ThreadSchedulers;
import io.reactivex.disposables.Disposable;
import timber.log.Timber;

/**
 * Created by Alexandra on 11/8/2017.
 */

@InjectViewState public class CatalogChildPresenter extends BasePresenter<CatalogChildView> {

  @Override protected void inject() {
    App.getAppComponent().inject(this);
  }

  @Override protected void onFirstViewAttach() {
    super.onFirstViewAttach();
  }

  public void getAllServicesChild(int parenId) {
    getViewState().setProgressVisible(true);
    Disposable disposable = mDataManager.getServicesChildren(parenId)
        .compose(ThreadSchedulers.singleSchedulers())
        .subscribe(servicesChildren -> {
          getViewState().setProgressVisible(false);
          getViewState().setUpServices(servicesChildren);
        }, throwable -> {
          getViewState().setProgressVisible(false);
          Timber.e(throwable);
          if (!ErrorsUtils.isNetworkError(throwable)) {
            getViewState().showDialogError(ErrorsUtils.getError(throwable));
          } else {
            getViewState().showDialogError(mContext.getString(R.string.server_not_connection));
          }
        });
    addToUndisposable(disposable);
  }
}
