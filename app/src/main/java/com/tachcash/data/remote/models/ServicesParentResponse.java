package com.tachcash.data.remote.models;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;
import java.util.List;

/**
 * Created by John on 17.11.2017.
 */

public class ServicesParentResponse implements Parcelable {

  @SerializedName("phoenix_categories") private List<ServiceParent> mServicesParent;

  public List<ServiceParent> getServicesParent() {
    return mServicesParent;
  }

  public void setServicesParent(List<ServiceParent> servicesParent) {
    mServicesParent = servicesParent;
  }

  @Override public int describeContents() {
    return 0;
  }

  @Override public void writeToParcel(Parcel dest, int flags) {
    dest.writeTypedList(this.mServicesParent);
  }

  public ServicesParentResponse() {
  }

  protected ServicesParentResponse(Parcel in) {
    this.mServicesParent = in.createTypedArrayList(ServiceParent.CREATOR);
  }

  public static final Creator<ServicesParentResponse> CREATOR =
      new Creator<ServicesParentResponse>() {
        @Override public ServicesParentResponse createFromParcel(Parcel source) {
          return new ServicesParentResponse(source);
        }

        @Override public ServicesParentResponse[] newArray(int size) {
          return new ServicesParentResponse[size];
        }
      };
}
