package com.tachcash.data.remote.models;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;

/**
 * Created by John on 25.01.2018.
 */

public class SystemRegisterResponse implements Parcelable {

  @SerializedName("reqn") private int mReqn;
  @SerializedName("secret") private String mSecret;
  @SerializedName("token") private String mToken;

  public int getReqn() {
    return mReqn;
  }

  public void setReqn(int mReqn) {
    this.mReqn = mReqn;
  }

  public String getSecret() {
    return mSecret;
  }

  public void setSecret(String mSecret) {
    this.mSecret = mSecret;
  }

  public String getToken() {
    return mToken;
  }

  public void setToken(String mToken) {
    this.mToken = mToken;
  }

  @Override public int describeContents() {
    return 0;
  }

  @Override public void writeToParcel(Parcel dest, int flags) {
    dest.writeInt(this.mReqn);
    dest.writeString(this.mSecret);
    dest.writeString(this.mToken);
  }

  public SystemRegisterResponse() {
  }

  protected SystemRegisterResponse(Parcel in) {
    this.mReqn = in.readInt();
    this.mSecret = in.readString();
    this.mToken = in.readString();
  }

  public static final Creator<SystemRegisterResponse> CREATOR =
      new Creator<SystemRegisterResponse>() {
        @Override public SystemRegisterResponse createFromParcel(Parcel source) {
          return new SystemRegisterResponse(source);
        }

        @Override public SystemRegisterResponse[] newArray(int size) {
          return new SystemRegisterResponse[size];
        }
      };
}
