package com.tachcash.di.components;

import com.tachcash.base.BaseActivity;
import com.tachcash.base.BaseFragment;
import com.tachcash.data.DataManager;
import com.tachcash.di.modules.AppModule;
import com.tachcash.di.scopes.AppScope;
import com.tachcash.feature.adapters.ServiceChildAdapter;
import com.tachcash.feature.presenters.CatalogChildPresenter;
import com.tachcash.feature.presenters.CatalogPresenter;
import com.tachcash.feature.presenters.MainPresenter;
import com.tachcash.feature.presenters.MapPresenter;
import com.tachcash.feature.presenters.PaymentPresenter;
import com.tachcash.feature.presenters.TemplatesPresenter;
import dagger.Component;

/**
 * Created by Alexandra on 11/3/2017.
 */

@AppScope @Component(modules = AppModule.class) public interface AppComponent {

  /* data manager */
  void inject(DataManager dataManager);

  /* base */
  void inject(BaseActivity activity);

  void inject(BaseFragment fragment);

  /* activities */
  void inject(MainPresenter presenter);

  /* fragments */
  void inject(CatalogPresenter presenter);

  void inject(PaymentPresenter presenter);

  void inject(MapPresenter presenter);

  void inject(CatalogChildPresenter presenter);

  void inject(TemplatesPresenter presenter);

  /* adapters */
  void inject(ServiceChildAdapter adapter);

  /* service */
}
