package com.tachcash.di.components;

import com.tachcash.base.BaseActivity;
import com.tachcash.base.BaseFragment;
import com.tachcash.data.DataManager;
import com.tachcash.di.modules.AppModule;
import com.tachcash.di.scopes.AppScope;
import com.tachcash.feature.presenters.MainPresenter;
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

  /* adapters */

  /* service */
}
