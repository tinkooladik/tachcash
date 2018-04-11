package com.tachcash.di.modules;

import android.content.Context;
import com.tachcash.data.DataManager;
import com.tachcash.data.local.PreferencesHelper;
import com.tachcash.data.remote.apis.InfoApi;
import com.tachcash.data.remote.apis.InfoService;
import com.tachcash.di.scopes.AppScope;
import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * Created by Alexandra on 11/3/2017.
 */

@Module(includes = { RetrofitModule.class }) public class DataModule {

  @Provides @AppScope InfoApi provideInfoApi(Retrofit retrofit) {
    return retrofit.create(InfoApi.class);
  }

  @Provides @AppScope InfoService provideInfoService(InfoApi api) {
    return new InfoService(api);
  }

  @Provides @AppScope DataManager provideDataManager() {
    return new DataManager();
  }

  @Provides @AppScope PreferencesHelper providePreferencesHelper(Context context) {
    return new PreferencesHelper(context);
  }
}
