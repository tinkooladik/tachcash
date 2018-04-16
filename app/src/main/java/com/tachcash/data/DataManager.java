package com.tachcash.data;

import android.content.Context;
import com.tachcash.App;
import com.tachcash.data.local.PreferencesHelper;
import com.tachcash.data.local.model.TemplateEntity;
import com.tachcash.data.remote.apis.InfoService;
import com.tachcash.data.remote.models.ServiceChildren;
import com.tachcash.data.remote.models.ServiceParent;
import com.tachcash.data.remote.models.ServicesParentResponse;
import com.tachcash.data.remote.models.SystemRegisterRequest;
import com.tachcash.data.remote.models.SystemRegisterResponse;
import io.reactivex.Flowable;
import io.reactivex.Single;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;

import static com.tachcash.utils.Constants.HEADER_X_AUTH_REGN;
import static com.tachcash.utils.Constants.HEADER_X_AUTH_SIGN;
import static com.tachcash.utils.Constants.HEADER_X_AUTH_TOKEN;
import static com.tachcash.utils.Constants.SYSTEM_PHONE;
import static com.tachcash.utils.Constants.SYSTEM_PIN;
import static com.tachcash.utils.Constants.SYSTEM_REG;
import static com.tachcash.utils.Converters.getMD5sign;

/**
 * Created by Alexandra on 11/3/2017.
 */

public class DataManager {

  @Inject Context mContext;
  @Inject PreferencesHelper mPref;
  @Inject InfoService mInfoService;

  public DataManager() {
    App.getAppComponent().inject(this);
  }

  public void clearData() {
    mPref.clear();
  }

  public Single<List<ServiceParent>> getServicesParent() {
    return mInfoService.getServicesParent(generateHeadersAuth())
        .map(ServicesParentResponse::getServicesParent);
  }

  public Single<List<ServiceChildren>> getServicesChildren(int parenId) {
    return mInfoService.getServicesChildren(generateHeadersAuth(), parenId)
        .flatMap(servicesChildrenResponse -> Flowable.fromIterable(
            servicesChildrenResponse.getServicesChildren()).map(serviceChildren -> {
          serviceChildren.setParentId(parenId);
          return serviceChildren;
        }).toList())
        .toFlowable()
        .concatMap(Flowable::fromIterable)
        .toSortedList((o1, o2) -> o1.getName().compareTo(o2.getName()));
  }

  public Single<SystemRegisterResponse> systemRegister() {
    return mInfoService.systemRegister(new SystemRegisterRequest(SYSTEM_PIN, SYSTEM_PHONE))
        .doOnSuccess(systemRegisterResponse -> {
          mPref.setSecret(systemRegisterResponse.getSecret());
          mPref.setToken(systemRegisterResponse.getToken());
        });
  }

  private Map<String, String> generateHeadersAuth() {
    Map<String, String> map = new HashMap<>();
    map.put(HEADER_X_AUTH_TOKEN, mPref.getToken());
    map.put(HEADER_X_AUTH_SIGN, getMD5sign(mPref.getToken(), SYSTEM_REG, mPref.getSecret()));
    map.put(HEADER_X_AUTH_REGN, SYSTEM_REG);
    return map;
  }

  public void saveTemplate(TemplateEntity templateEntity) {
    mPref.saveTemplate(templateEntity);
  }

  public void updateTemplate(TemplateEntity oldTemplate, TemplateEntity newTemplate) {
    mPref.updateTemplate(oldTemplate, newTemplate);
  }

  public void deleteTemplate(TemplateEntity templateEntity) {
    mPref.deleteTemplate(templateEntity);
  }

  public List<TemplateEntity> getTemplates() {
    return mPref.getTemplates();
  }

  public int getTemplatesCount() {
    return mPref.getTemplatesCount();
  }
}
