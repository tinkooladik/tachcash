package com.tachcash.data.remote.models;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Alexandra on 11.04.2018.
 */
public class ServiceParentEntity implements Parcelable {
  @SerializedName("uri") private String mUri;
  @SerializedName("name") private String mName;

  public ServiceParentEntity(String uri, String name) {
    mUri = uri;
    mName = name;
  }

  public ServiceParentEntity(String name) {
    mName = name;
  }

  protected ServiceParentEntity(Parcel in) {
    mUri = in.readString();
    mName = in.readString();
  }

  public static final Creator<ServiceParentEntity> CREATOR = new Creator<ServiceParentEntity>() {
    @Override public ServiceParentEntity createFromParcel(Parcel in) {
      return new ServiceParentEntity(in);
    }

    @Override public ServiceParentEntity[] newArray(int size) {
      return new ServiceParentEntity[size];
    }
  };

  public String getUri() {
    return mUri;
  }

  public String getName() {
    return mName;
  }

  @Override public int describeContents() {
    return 0;
  }

  @Override public void writeToParcel(Parcel dest, int flags) {
    dest.writeString(mUri);
    dest.writeString(mName);
  }
}
