package com.tachcash.feature.views;

/**
 * Created by Alexandra on 11.04.2018.
 */

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.tachcash.data.remote.models.ServiceParent;
import java.util.List;

@StateStrategyType(AddToEndSingleStrategy.class) public interface CatalogView extends MvpView {

  void setProgressVisible(boolean visible);

  void setUpServices(List<ServiceParent> services);

  void showDialogError(String error);
}
