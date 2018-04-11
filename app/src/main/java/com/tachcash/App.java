package com.tachcash;

import android.app.Application;
import android.support.v7.app.AppCompatDelegate;
import com.tachcash.di.components.AppComponent;
import com.tachcash.di.components.DaggerAppComponent;
import com.tachcash.di.modules.AppModule;
import timber.log.Timber;

/**
 * Created by Alexandra on 11.04.2018.
 */
public class App extends Application {

  private static AppComponent sAppComponent;

  public static AppComponent getAppComponent() {
    return sAppComponent;
  }

  @Override public void onCreate() {
    super.onCreate();

    if (BuildConfig.DEBUG) {
      Timber.plant(new Timber.DebugTree());
    } else {
      //Fabric.with(this, new Crashlytics());
    }

    AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);

    sAppComponent = DaggerAppComponent.builder().appModule(new AppModule(this)).build();
  }
}
