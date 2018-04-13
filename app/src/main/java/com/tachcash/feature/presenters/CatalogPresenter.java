package com.tachcash.feature.presenters;

import com.arellomobile.mvp.InjectViewState;
import com.tachcash.App;
import com.tachcash.R;
import com.tachcash.base.BasePresenter;
import com.tachcash.feature.views.CatalogView;
import com.tachcash.utils.ErrorsUtils;
import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import timber.log.Timber;

import static com.tachcash.utils.Constants.SLUG_FAVORITES;

/**
 * Created by Alexandra on 11/8/2017.
 */

@InjectViewState public class CatalogPresenter extends BasePresenter<CatalogView> {

  @Override protected void inject() {
    App.getAppComponent().inject(this);
  }

  @Override protected void onFirstViewAttach() {
    super.onFirstViewAttach();
    getAllServicesParent();
  }

  private void getAllServicesParent() {
    getViewState().setProgressVisible(true);
    Disposable disposable = mDataManager.systemRegister()
        .flatMap(serviceParentList -> mDataManager.getServicesParent())
        .flatMap(serviceParentList -> Flowable.fromIterable(serviceParentList)
            .filter(serviceParent -> !serviceParent.getSlug().equals(SLUG_FAVORITES))
            .filter(serviceParent -> serviceParent.getServicesCount() > 0)
            .toList())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(serviceParents -> {
          getViewState().setProgressVisible(false);
          getViewState().setUpServices(serviceParents);
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
