package com.tachcash.data.local;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Alexandra on 11/3/2017.
 */

public class PreferencesHelper {

  private static final String PREF_FILE_NAME = "com.tachcard.hotcoin";

  private final SharedPreferences mPreferences;

  public PreferencesHelper(Context context) {
    mPreferences = context.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE);
  }

  public void clear() {
    mPreferences.edit().clear().apply();
  }
}
