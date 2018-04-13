package com.tachcash.data.remote.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by John on 25.01.2018.
 */

public class SystemRegisterRequest {

  @SerializedName("pin") private String mPin;
  @SerializedName("phone") private String mPhone;

  public SystemRegisterRequest(String pin, String phone) {
    mPin = pin;
    mPhone = phone;
  }
}
