package com.tachcash.feature.presenters;

import com.arellomobile.mvp.InjectViewState;
import com.tachcash.App;
import com.tachcash.base.BasePresenter;
import com.tachcash.feature.views.MapView;

/**
 * Created by Alexandra on 11/8/2017.
 */

@InjectViewState public class MapPresenter extends BasePresenter<MapView> {

  @Override protected void inject() {
    App.getAppComponent().inject(this);
  }
}
