package com.tachcash.data.remote.models;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;

/**
 * Created by John on 20.11.2017.
 */

public class ServiceChildren implements Parcelable {

  @SerializedName("id") private int id;
  @SerializedName("name") private String name;
  @SerializedName("logo") private String logo;
  @SerializedName("fee") private String fee;
  @SerializedName("link") private String link;
  private int parent_id;

  public int getParentId() {
    return parent_id;
  }

  public void setParentId(int parent_id) {
    this.parent_id = parent_id;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getLogo() {
    return logo;
  }

  public void setLogo(String logo) {
    this.logo = logo;
  }

  public String getFee() {
    return fee;
  }

  public void setFee(String fee) {
    this.fee = fee;
  }

  public String getLink() {
    return link;
  }

  public void setLink(String link) {
    this.link = link;
  }

  @Override public int describeContents() {
    return 0;
  }

  @Override public void writeToParcel(Parcel dest, int flags) {
    dest.writeInt(this.id);
    dest.writeString(this.name);
    dest.writeString(this.logo);
    dest.writeString(this.fee);
    dest.writeString(this.link);
    dest.writeInt(this.parent_id);
  }

  public ServiceChildren() {
  }

  protected ServiceChildren(Parcel in) {
    this.id = in.readInt();
    this.name = in.readString();
    this.logo = in.readString();
    this.fee = in.readString();
    this.link = in.readString();
    this.parent_id = in.readInt();
  }

  public static final Creator<ServiceChildren> CREATOR = new Creator<ServiceChildren>() {
    @Override public ServiceChildren createFromParcel(Parcel source) {
      return new ServiceChildren(source);
    }

    @Override public ServiceChildren[] newArray(int size) {
      return new ServiceChildren[size];
    }
  };
}
