package com.tachcash.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import timber.log.Timber;

/**
 * Created by Alexandra on 11/3/2017.
 */

public class Converters {

  public static String setCurrentTimeZone(String dateUTC) {
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
    df.setTimeZone(TimeZone.getTimeZone("UTC"));
    Date date = null;
    try {
      date = df.parse(dateUTC);
    } catch (ParseException e) {
      Timber.e(e);
    }
    df.setTimeZone(TimeZone.getDefault());
    return df.format(date);
  }

  public static String getMD5sign(String token, String timestamp, String secret) {
    final String s = token + timestamp + secret;
    try {
      MessageDigest digest = MessageDigest.getInstance("MD5");
      digest.update(s.getBytes());
      byte messageDigest[] = digest.digest();
      StringBuilder sb = new StringBuilder();
      for (byte b : messageDigest) {
        sb.append(String.format("%02x", b & 0xff));
      }
      return sb.toString();
    } catch (NoSuchAlgorithmException e) {
      Timber.e(e);
    }
    return "";
  }

  public static String hideEmail(String email) {
    if (email.length() >= 4) {
      StringBuilder sb = new StringBuilder(email);
      for (int i = 2; i < sb.lastIndexOf(".") - 2; ++i) {
        if (sb.charAt(i) != '@') {
          sb.setCharAt(i, '*');
        }
      }
      return sb.toString();
    }
    return email;
  }

  public static String hidePhone(String phone) {
    if (phone.length() >= 4) {
      return "*" + phone.substring(phone.length() - 3, phone.length());
    }
    return phone;
  }

  public static String refactoringPhone(String phone) {
    String refPhone = phone.replaceAll(" ", "");
    if (refPhone.length() >= 10) {
      refPhone = refPhone.substring(refPhone.length() - 10);
      StringBuilder str = new StringBuilder(refPhone);
      str.insert(0, "38");
      refPhone = str.toString();
    } else {
      refPhone = "";
    }
    return refPhone;
  }
}
