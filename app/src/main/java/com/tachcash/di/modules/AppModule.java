package com.tachcash.di.modules;

import android.app.Application;
import android.content.Context;
import com.tachcash.base.Navigator;
import com.tachcash.di.scopes.AppScope;
import com.tachcash.utils.RxBus;
import dagger.Module;
import dagger.Provides;

/**
 * Created by Alexandra on 11/3/2017.
 */

@Module(includes = { DataModule.class }) public class AppModule {

  private final Application mApplication;

  public AppModule(Application application) {
    mApplication = application;
  }

  @Provides @AppScope Context provideAppContext() {
    return mApplication;
  }

  @Provides @AppScope RxBus provideRxBus() {
    return new RxBus();
  }

  @Provides @AppScope Navigator provideNavigator() {
    return new Navigator();
  }
}
