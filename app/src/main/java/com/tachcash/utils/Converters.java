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

  public static String getFormattedDateFromTimestamp(Long timestampInMilliSeconds) {
    Date date = new Date();
    date.setTime(timestampInMilliSeconds);
    return new SimpleDateFormat("hh:mm, d.MM.yy", Locale.getDefault()).format(date);
  }
}
