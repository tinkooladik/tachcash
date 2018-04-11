package com.tachcash.feature.views;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

/**
 * Created by Alexandra on 15.11.2017.
 */

@StateStrategyType(AddToEndSingleStrategy.class) public interface MainView extends MvpView {

}
