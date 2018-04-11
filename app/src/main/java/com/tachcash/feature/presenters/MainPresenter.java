package com.tachcash.feature.presenters;

import com.arellomobile.mvp.InjectViewState;
import com.tachcash.App;
import com.tachcash.base.BasePresenter;
import com.tachcash.feature.views.MainView;

/**
 * Created by Alexandra on 15.11.2017.
 */

@SuppressWarnings("ConstantConditions") @InjectViewState public class MainPresenter
    extends BasePresenter<MainView> {

  @Override protected void inject() {
    App.getAppComponent().inject(this);
  }
}
