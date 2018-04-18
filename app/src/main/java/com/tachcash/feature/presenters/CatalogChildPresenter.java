package com.tachcash.feature.presenters;

import com.arellomobile.mvp.InjectViewState;
import com.tachcash.App;
import com.tachcash.R;
import com.tachcash.base.BasePresenter;
import com.tachcash.data.remote.models.ServiceChildren;
import com.tachcash.feature.views.CatalogChildView;
import com.tachcash.utils.ErrorsUtils;
import com.tachcash.utils.ThreadSchedulers;
import io.reactivex.Flowable;
import io.reactivex.Single;
import io.reactivex.disposables.Disposable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import timber.log.Timber;

/**
 * Created by Alexandra on 11/8/2017.
 */

@InjectViewState public class CatalogChildPresenter extends BasePresenter<CatalogChildView> {

  private List<ServiceChildren> mListServicesChildren = new ArrayList<>();

  @Override protected void inject() {
    App.getAppComponent().inject(this);
  }

  @Override protected void onFirstViewAttach() {
    super.onFirstViewAttach();
  }

  public void getAllServicesChild(int parenId) {
    getViewState().setProgressVisible(true);
    Integer[] need = { 126, 164, 165, 175, 176, 1200, 1202, 1203, 1204, 1207, 1209 };
    List<Integer> mySet = Arrays.asList(need);
    Disposable disposable = mDataManager.getServicesChildren(parenId).flatMap(list -> {
      if (parenId == 2) {
        ArrayList<ServiceChildren> newList = new ArrayList<>();
        for (ServiceChildren service : list) {
          if (mySet.contains(service.getId())) {
            if (service.getId() == 126) {
              service.setName("Vodafone");
            } else if (service.getId() == 1200) {
              service.setName("ТриМоб");
            }
            newList.add(service);
          }
        }
        return Single.just(newList);
      }
      return Single.just(list);
    }).compose(ThreadSchedulers.singleSchedulers()).subscribe(servicesChildren -> {
      getViewState().setProgressVisible(false);
      getViewState().setUpServices(servicesChildren);
      mListServicesChildren = servicesChildren;
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

  public void filterServicesChildren(String s) {
    Disposable disposable = Flowable.fromIterable(mListServicesChildren)
        .filter(serviceChildren -> serviceChildren.getName()
            .toLowerCase()
            .contains(s.trim().toLowerCase()))
        .toList()
        .subscribe(servicesChildren -> getViewState().setUpServices(servicesChildren), Timber::e);
    addToUndisposable(disposable);
  }
}
