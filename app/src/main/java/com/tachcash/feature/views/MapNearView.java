package com.tachcash.feature.views;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

/**
 * Created by Alexandra on 11.04.2018.
 */
@StateStrategyType(AddToEndSingleStrategy.class) public interface MapNearView extends MvpView {

  void setUpMap();

  void showWarning();
}
