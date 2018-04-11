package com.tachcash.di.modules;

import android.content.Context;
import android.os.Build;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.tachcash.BuildConfig;
import com.tachcash.di.scopes.AppScope;
import com.tachcash.utils.Constants;
import com.tachcash.utils.NetworkUtil;
import com.tachcash.utils.TLSSocketFactory;
import dagger.Module;
import dagger.Provides;
import io.reactivex.schedulers.Schedulers;
import java.io.File;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import javax.inject.Named;
import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import timber.log.Timber;

import static com.tachcash.utils.Constants.Headers.LANG;
import static com.tachcash.utils.Constants.Headers.USER_AGENT;

/**
 * Created by Alexandra on 11/3/2017.
 */

@Module public class RetrofitModule {

  @Provides @AppScope Retrofit provideRetrofit(Converter.Factory converterFactory,
      OkHttpClient okClient) {
    return new Retrofit.Builder().baseUrl(Constants.BASE_URL)
        .client(okClient)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
        .addConverterFactory(converterFactory)
        .build();
  }

  @Provides @AppScope Converter.Factory provideConverterFactory(Gson gson) {
    return GsonConverterFactory.create(gson);
  }

  @Provides @AppScope Gson provideGson() {
    return new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
        .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
        .serializeNulls()
        .setLenient()
        .create();
  }

  @Provides @AppScope OkHttpClient provideOkClient(HttpLoggingInterceptor httpLoggingInterceptor,
      Interceptor interceptor, Cache cache, @Named("CacheInterceptor") Interceptor cacheInterceptor,
      @Named("OfflineCacheInterceptor") Interceptor offlineCacheInterceptor) {
    OkHttpClient client = new OkHttpClient();
    try {
      client = new OkHttpClient.Builder().readTimeout(10, TimeUnit.SECONDS)
          .addInterceptor(interceptor)
          .addInterceptor(httpLoggingInterceptor)
          .addInterceptor(offlineCacheInterceptor)
          .addNetworkInterceptor(cacheInterceptor)
          .cache(cache)
          .sslSocketFactory(new TLSSocketFactory(), new TLSSocketFactory.X509TrustManagerCustom())
          .build();
    } catch (KeyManagementException | NoSuchAlgorithmException e) {
      Timber.e(e);
    }
    return client;
  }

  @Provides @AppScope HttpLoggingInterceptor provideHttpLoggingInterceptor() {
    HttpLoggingInterceptor interceptor =
        new HttpLoggingInterceptor(message -> Timber.tag("response").d(message));
    interceptor.setLevel(
        BuildConfig.DEBUG ? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE);
    return interceptor;
  }

  @Provides @AppScope Interceptor provideHeaderInterceptor() {
    String userAgent =
        String.format("%s; Android %s (tachcash %s)", Build.MANUFACTURER + " " + Build.MODEL,
            Build.VERSION.RELEASE, BuildConfig.VERSION_NAME);
    return chain -> {
      Request original = chain.request();
      Request request = original.newBuilder()
          .header(LANG, Locale.getDefault().getLanguage())
          .header(USER_AGENT, userAgent)
          .build();
      return chain.proceed(request);
    };
  }

  /**
   * For Cache
   */
  @Provides @AppScope Cache provideCache(Context context) {
    Cache cache = null;
    try {
      cache = new Cache(new File(context.getCacheDir(), "http-cache"), 10 * 1024 * 1024); // 10 MB
    } catch (Exception e) {
      Timber.e(e, "Could not create Cache!");
    }
    return cache;
  }

  @Provides @AppScope @Named("CacheInterceptor") Interceptor provideCacheInterceptor() {
    return chain -> {
      Request request = chain.request();
      Response response = chain.proceed(chain.request());
      String requestUrl = request.url().toString();
      if (request.method().equals("GET")) {
        CacheControl cacheControl;
        if (requestUrl.contains("/api/v1/wallets") || requestUrl.contains("/api/v1/transaction/")) {
          cacheControl = new CacheControl.Builder().maxAge(0, TimeUnit.SECONDS).build();
        } else {
          cacheControl = new CacheControl.Builder().noCache().build();
        }
        response = response.newBuilder()
            .removeHeader("Pragma")
            .header("Cache-Control", cacheControl.toString())
            .build();
      }
      return response;
    };
  }

  @Provides @AppScope @Named("OfflineCacheInterceptor") Interceptor provideOfflineCacheInterceptor(
      Context context) {
    return chain -> {
      Request request = chain.request();
      if (!NetworkUtil.isNetworkConnected(context)) {
        CacheControl cacheControl = new CacheControl.Builder().maxStale(7, TimeUnit.DAYS).build();
        request = request.newBuilder().cacheControl(cacheControl).build();
      }
      return chain.proceed(request);
    };
  }
}
