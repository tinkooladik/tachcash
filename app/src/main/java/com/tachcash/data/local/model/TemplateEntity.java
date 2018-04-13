package com.tachcash.data.local.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Alexandra on 13.04.2018.
 */
public class TemplateEntity implements Parcelable {

  private int mService;
  private Long mAccount;
  private int mAmount;
  private String mDate;
  private String mTachcashCode;
  private String mIcon;
  private String mServiceName;

  public TemplateEntity() {
  }

  public TemplateEntity(int service, Long account, int amount, String date, String tachcashCode,
      String icon, String serviceName) {
    mService = service;
    mAccount = account;
    mAmount = amount;
    mDate = date;
    mTachcashCode = tachcashCode;
    mIcon = icon;
    mServiceName = serviceName;
  }

  protected TemplateEntity(Parcel in) {
    mService = in.readInt();
    mAccount = in.readLong();
    mAmount = in.readInt();
    mDate = in.readString();
    mTachcashCode = in.readString();
    mIcon = in.readString();
    mServiceName = in.readString();
  }

  @Override public void writeToParcel(Parcel dest, int flags) {
    dest.writeInt(mService);
    dest.writeLong(mAccount);
    dest.writeInt(mAmount);
    dest.writeString(mDate);
    dest.writeString(mTachcashCode);
    dest.writeString(mIcon);
    dest.writeString(mServiceName);
  }

  @Override public int describeContents() {
    return 0;
  }

  public static final Creator<TemplateEntity> CREATOR = new Creator<TemplateEntity>() {
    @Override public TemplateEntity createFromParcel(Parcel in) {
      return new TemplateEntity(in);
    }

    @Override public TemplateEntity[] newArray(int size) {
      return new TemplateEntity[size];
    }
  };

  public int getService() {
    return mService;
  }

  public Long getAccount() {
    return mAccount;
  }

  public int getAmount() {
    return mAmount;
  }

  public String getDate() {
    return mDate;
  }

  public void setService(int service) {
    mService = service;
  }

  public void setAccount(Long account) {
    mAccount = account;
  }

  public void setAmount(int amount) {
    mAmount = amount;
  }

  public void setDate(String date) {
    mDate = date;
  }

  public void setTachcashCode(String tachcashCode) {
    mTachcashCode = tachcashCode;
  }

  public void setIcon(String icon) {
    mIcon = icon;
  }

  public String getTachcashCode() {
    return mTachcashCode;
  }

  public String getIcon() {
    return mIcon;
  }

  public String getServiceName() {
    return mServiceName;
  }

  public void setServiceName(String serviceName) {
    mServiceName = serviceName;
  }

  @Override public boolean equals(Object obj) {
    TemplateEntity o2 = (TemplateEntity) obj;
    return getService() == o2.getService()
        && getAccount().equals(o2.getAccount())
        && getAmount() == o2.getAmount()
        && getDate().equals(o2.getDate())
        && getTachcashCode().equals(o2.getTachcashCode())
        && getIcon().equals(o2.getIcon())
        && getServiceName().equals(o2.getServiceName());
  }
}
