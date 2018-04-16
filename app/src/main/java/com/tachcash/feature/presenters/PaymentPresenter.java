package com.tachcash.feature.presenters;

import com.arellomobile.mvp.InjectViewState;
import com.tachcash.App;
import com.tachcash.base.BasePresenter;
import com.tachcash.data.local.model.TemplateEntity;
import com.tachcash.feature.views.PaymentView;
import com.tachcash.utils.RxBusHelper;

/**
 * Created by Alexandra on 11/8/2017.
 */

@InjectViewState public class PaymentPresenter extends BasePresenter<PaymentView> {

  @Override protected void inject() {
    App.getAppComponent().inject(this);
  }

  public void saveTemplate(TemplateEntity templateEntity) {
    mDataManager.saveTemplate(templateEntity);
    mRxBus.post(new RxBusHelper.UpdateBadgeCount());
  }

  public void deleteTemplate(TemplateEntity templateEntity) {
    mDataManager.deleteTemplate(templateEntity);
    mRxBus.post(new RxBusHelper.UpdateTemplates());
    mRxBus.post(new RxBusHelper.UpdateBadgeCount());
  }
}
