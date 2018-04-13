package com.tachcash.feature.views;

/**
 * Created by Alexandra on 11.04.2018.
 */

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.tachcash.data.local.model.TemplateEntity;
import java.util.List;

@StateStrategyType(AddToEndSingleStrategy.class) public interface TemplatesView extends MvpView {

  void setUpTemplates(List<TemplateEntity> templates);
}
