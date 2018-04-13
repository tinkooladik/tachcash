package com.tachcash.data.remote.models;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;

/**
 * Created by John on 17.11.2017.
 */

public class ServiceParent implements Parcelable {

  @SerializedName("id") private int id;
  @SerializedName("title") private String title;
  @SerializedName("desc") private String desc;
  @SerializedName("logo") private String logo;
  @SerializedName("slug") private String slug;
  @SerializedName("priority") private String priority;
  @SerializedName("services_count") private int services_count;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getDesc() {
    return desc;
  }

  public void setDesc(String desc) {
    this.desc = desc;
  }

  public String getLogo() {
    return logo;
  }

  public void setLogo(String logo) {
    this.logo = logo;
  }

  public String getSlug() {
    return slug;
  }

  public void setSlug(String slug) {
    this.slug = slug;
  }

  public String getPriority() {
    return priority;
  }

  public void setPriority(String priority) {
    this.priority = priority;
  }

  public int getServicesCount() {
    return services_count;
  }

  public void setServicesCount(int services_count) {
    this.services_count = services_count;
  }

  @Override public int describeContents() {
    return 0;
  }

  @Override public void writeToParcel(Parcel dest, int flags) {
    dest.writeInt(this.id);
    dest.writeString(this.title);
    dest.writeString(this.desc);
    dest.writeString(this.logo);
    dest.writeString(this.slug);
    dest.writeString(this.priority);
    dest.writeInt(this.services_count);
  }

  public ServiceParent() {
  }

  protected ServiceParent(Parcel in) {
    this.id = in.readInt();
    this.title = in.readString();
    this.desc = in.readString();
    this.logo = in.readString();
    this.slug = in.readString();
    this.priority = in.readString();
    this.services_count = in.readInt();
  }

  public static final Creator<ServiceParent> CREATOR = new Creator<ServiceParent>() {
    @Override public ServiceParent createFromParcel(Parcel source) {
      return new ServiceParent(source);
    }

    @Override public ServiceParent[] newArray(int size) {
      return new ServiceParent[size];
    }
  };
}
