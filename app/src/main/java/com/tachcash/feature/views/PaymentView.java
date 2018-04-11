package com.tachcash.feature.views;

/**
 * Created by Alexandra on 11.04.2018.
 */

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

@StateStrategyType(AddToEndSingleStrategy.class) public interface PaymentView extends MvpView {
}
