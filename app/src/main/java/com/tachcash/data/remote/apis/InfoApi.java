package com.tachcash.data.remote.apis;

import com.tachcash.data.remote.models.ServicesChildrenResponse;
import com.tachcash.data.remote.models.ServicesParentResponse;
import com.tachcash.data.remote.models.SystemRegisterRequest;
import com.tachcash.data.remote.models.SystemRegisterResponse;
import io.reactivex.Single;
import java.util.Map;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by Alexandra on 21.11.2017.
 */

public interface InfoApi {

  @GET("phoenix/categories/info/0") Single<ServicesParentResponse> getServicesParent(
      @HeaderMap Map<String, String> headersAuth);

  @GET("phoenix/categories/info/{parenId}") Single<ServicesChildrenResponse> getServicesChildren(
      @HeaderMap Map<String, String> headersAuth, @Path("parenId") int parenId);

  @POST("system-register") Single<SystemRegisterResponse> systemRegister(
      @Body SystemRegisterRequest request);

}
