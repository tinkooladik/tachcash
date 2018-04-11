package com.tachcash.utils;

import android.text.InputFilter;
import android.text.Spanned;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Alexandra on 01.12.2017.
 */

public class InputFilterMinMax implements InputFilter {

  private Pattern mPattern;

  public InputFilterMinMax(int digitsBeforeZero, int digitsAfterZero) {
    mPattern = Pattern.compile("[0-9]{0,"
        + (digitsBeforeZero - 1)
        + "}+((\\.[0-9]{0,"
        + (digitsAfterZero - 1)
        + "})?)||(\\.)?");
  }

  @Override
  public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart,
      int dend) {

    Matcher matcher = mPattern.matcher(dest);
    if (!matcher.matches()) return "";
    return null;
  }
}
