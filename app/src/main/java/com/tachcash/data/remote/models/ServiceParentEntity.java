package com.tachcash.data.remote.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Alexandra on 11.04.2018.
 */
public class ServiceParentEntity {
  @SerializedName("uri") private String mUri;
  @SerializedName("name") private String mName;

  public ServiceParentEntity(String uri, String name) {
    mUri = uri;
    mName = name;
  }

  public ServiceParentEntity(String name) {
    mName = name;
  }

  public String getUri() {
    return mUri;
  }

  public String getName() {
    return mName;
  }
}
