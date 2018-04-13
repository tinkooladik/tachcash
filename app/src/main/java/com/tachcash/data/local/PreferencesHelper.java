package com.tachcash.data.local;

import android.content.Context;
import android.content.SharedPreferences;
import com.google.gson.Gson;
import com.tachcash.data.local.model.TemplateEntity;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Alexandra on 11/3/2017.
 */

public class PreferencesHelper {

  private static final String PREF_FILE_NAME = "com.tachcard.hotcoin";
  private static final String PREF_SECRET = "PREF_SECRET";
  private static final String PREF_TOKEN = "PREF_TOKEN";
  private static final String TEMPLATES = "TEMPLATES";

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

  public void saveTemplate(TemplateEntity templateEntity) {
    ArrayList<TemplateEntity> templateEntities = getTemplates();
    templateEntities.add(templateEntity);
    mPreferences.edit().putString(TEMPLATES, (new Gson()).toJson(templateEntities)).apply();
  }

  public ArrayList<TemplateEntity> getTemplates() {
    ArrayList<TemplateEntity> templateEntities = new ArrayList<>();
    TemplateEntity[] fromPref =
        (new Gson()).fromJson(mPreferences.getString(TEMPLATES, ""), TemplateEntity[].class);
    if (fromPref != null) {
      templateEntities.addAll(Arrays.asList(fromPref));
    }
    return templateEntities;
  }

  public void clear() {
    mPreferences.edit().clear().apply();
  }
}
