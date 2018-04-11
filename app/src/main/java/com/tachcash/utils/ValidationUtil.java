package com.tachcash.utils;

import android.text.TextUtils;

/**
 * Created by Alexandra on 17.11.2017.
 */

public class ValidationUtil {

  public final static boolean isValidEmail(String target) {
    if (TextUtils.isEmpty(target)) {
      return false;
    } else {
      return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }
  }

  public final static boolean isValidPhone(String phone) {
    return !TextUtils.isEmpty(phone);
  }
}
