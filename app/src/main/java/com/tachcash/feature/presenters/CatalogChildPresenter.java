package com.tachcash.feature.presenters;

import com.arellomobile.mvp.InjectViewState;
import com.tachcash.App;
import com.tachcash.base.BasePresenter;
import com.tachcash.feature.views.CatalogChildView;

/**
 * Created by Alexandra on 11/8/2017.
 */

@InjectViewState public class CatalogChildPresenter extends BasePresenter<CatalogChildView> {

  @Override protected void inject() {
    App.getAppComponent().inject(this);
  }
}
