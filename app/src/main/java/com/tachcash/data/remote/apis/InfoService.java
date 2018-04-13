package com.tachcash.data.remote.apis;

import com.tachcash.data.remote.models.ServicesChildrenResponse;
import com.tachcash.data.remote.models.ServicesParentResponse;
import com.tachcash.data.remote.models.SystemRegisterRequest;
import com.tachcash.data.remote.models.SystemRegisterResponse;
import io.reactivex.Single;
import java.util.Map;

/**
 * Created by Alexandra on 21.11.2017.
 */

public class InfoService {

  private final InfoApi mInfoApi;

  public InfoService(InfoApi api) {
    this.mInfoApi = api;
  }

  public Single<ServicesParentResponse> getServicesParent(Map<String, String> headersAuth) {
    return mInfoApi.getServicesParent(headersAuth);
  }

  public Single<ServicesChildrenResponse> getServicesChildren(Map<String, String> headersAuth,
      int parenId) {
    return mInfoApi.getServicesChildren(headersAuth, parenId);
  }

  public Single<SystemRegisterResponse> systemRegister(SystemRegisterRequest request) {
    return mInfoApi.systemRegister(request);
  }
}
