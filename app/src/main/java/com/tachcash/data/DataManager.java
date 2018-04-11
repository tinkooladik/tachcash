package com.tachcash.data;

import android.content.Context;
import com.tachcash.App;
import com.tachcash.data.local.PreferencesHelper;
import javax.inject.Inject;

/**
 * Created by Alexandra on 11/3/2017.
 */

public class DataManager {

  @Inject Context mContext;
  @Inject PreferencesHelper mPref;

  public DataManager() {
    App.getAppComponent().inject(this);
  }

  public void clearData() {
    mPref.clear();
  }
}
