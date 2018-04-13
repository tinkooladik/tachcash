package com.tachcash.data.local;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Alexandra on 11/3/2017.
 */

public class PreferencesHelper {

  private static final String PREF_FILE_NAME = "com.tachcard.hotcoin";
  private static final String PREF_SECRET = "PREF_SECRET";
  private static final String PREF_TOKEN = "PREF_TOKEN";

  private final SharedPreferences mPreferences;

  public PreferencesHelper(Context context) {
    mPreferences = context.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE);
  }

  public void setToken(String token) {
    mPreferences.edit().putString(PREF_TOKEN, token).apply();
  }

  public String getToken() {
    return mPreferences.getString(PREF_TOKEN, "");
  }

  public void setSecret(String secret) {
    mPreferences.edit().putString(PREF_SECRET, secret).apply();
  }

  public String getSecret() {
    return mPreferences.getString(PREF_SECRET, "");
  }

  public void clear() {
    mPreferences.edit().clear().apply();
  }
}
