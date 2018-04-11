package com.tachcash.utils;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import okhttp3.ResponseBody;
import org.json.JSONException;
import org.json.JSONObject;
import retrofit2.HttpException;
import timber.log.Timber;

public final class ErrorsUtils {

  public static String getError(Throwable throwable) {
    if (throwable instanceof HttpException) {
      ResponseBody body = ((HttpException) throwable).response().errorBody();
      try {
        String result = null;
        String errorBody = null;
        if (body != null) {
          errorBody = body.string();
        }
        JSONObject obj = new JSONObject(errorBody);
        if (obj.has("errors")) {
          obj = obj.getJSONObject("errors");
          result = obj.getJSONArray(obj.keys().next()).get(0).toString();
        } else if (obj.has("message")) {
          result = obj.getString("message");
        } else if (obj.has("error")) {
          result = obj.getString("error");
        }
        return result;
      } catch (IOException | JSONException e) {
        Timber.e(e);
      }
    }
    return null;
  }

  public static String getError(String errorBody) {
    Timber.e(errorBody);
    try {
      String result = null;
      JSONObject obj = new JSONObject(errorBody);
      if (obj.has("errors")) {
        obj = obj.getJSONObject("errors");
        result = obj.getJSONArray(obj.keys().next()).get(0).toString();
      } else if (obj.has("message")) {
        result = obj.getString("message");
      } else if (obj.has("error")) {
        result = obj.getString("error");
      }
      return result;
    } catch (JSONException e) {
      Timber.e(e);
    }
    return null;
  }

  public static int getErrorCode(String errorBody) {
    Timber.e(errorBody);
    try {
      JSONObject obj = new JSONObject(errorBody);
      return obj.getInt("code");
    } catch (JSONException e) {
      Timber.e(e);
    }
    return 0;
  }

  public static int getErrorCode(Throwable throwable) {
    if (throwable instanceof HttpException) {
      ResponseBody body = ((HttpException) throwable).response().errorBody();
      try {
        String errorBody = null;
        if (body != null) {
          errorBody = body.string();
        }
        JSONObject obj = new JSONObject(errorBody);
        return obj.getInt("code");
      } catch (IOException | JSONException e) {
        Timber.e(e);
      }
    }
    return 0;
  }

  public static boolean isNetworkError(Throwable throwable) {
    return (throwable instanceof SocketTimeoutException
        || throwable instanceof UnknownHostException);
  }
}
