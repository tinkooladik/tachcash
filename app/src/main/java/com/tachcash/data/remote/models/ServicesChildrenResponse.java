package com.tachcash.data.remote.models;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;
import java.util.List;

/**
 * Created by John on 21.11.2017.
 */

public class ServicesChildrenResponse implements Parcelable {

  @SerializedName("phoenix_providers") private List<ServiceChildren> mServicesChildren;
  @SerializedName("date") private String mDate;

  public List<ServiceChildren> getServicesChildren() {
    return mServicesChildren;
  }

  public void setServicesChildren(List<ServiceChildren> servicesChildren) {
    mServicesChildren = servicesChildren;
  }

  public String getDate() {
    return mDate;
  }

  public void setDate(String mDate) {
    this.mDate = mDate;
  }

  @Override public int describeContents() {
    return 0;
  }

  @Override public void writeToParcel(Parcel dest, int flags) {
    dest.writeTypedList(this.mServicesChildren);
    dest.writeString(this.mDate);
  }

  public ServicesChildrenResponse() {
  }

  protected ServicesChildrenResponse(Parcel in) {
    this.mServicesChildren = in.createTypedArrayList(ServiceChildren.CREATOR);
    this.mDate = in.readString();
  }

  public static final Creator<ServicesChildrenResponse> CREATOR =
      new Creator<ServicesChildrenResponse>() {
        @Override public ServicesChildrenResponse createFromParcel(Parcel source) {
          return new ServicesChildrenResponse(source);
        }

        @Override public ServicesChildrenResponse[] newArray(int size) {
          return new ServicesChildrenResponse[size];
        }
      };
}
