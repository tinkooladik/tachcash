package com.tachcash.utils;

/**
 * Created by Alexandra on 11/3/2017.
 */

public class Constants {

  private static final String DOMEN_PROD_LIVE = "api.tachcard.com";
  private static final String DOMEN_LIVE = "sandbox.tachcard.com";
  private static final String DOMEN_TEST = "test-api.tachcard.com";
  private static final String DOMEN_TEST_IOS = "ios-api.tachcard.com";
  private static final String DOMEN_TEST_ANDROID = "android-api.tachcard.com";
  public static final String BASE_URL = "https://" + DOMEN_PROD_LIVE + "/";

  /** argument keys */
  public static final String SERVICE_PARENT = "SERVICE_PARENT";
  public static final String TEMPLATE_ENTITY = "TEMPLATE_ENTITY";
  public static final String TEMPLATE_ENTITY_LIST = "TEMPLATE_ENTITY_LIST";

  /** fragments */
  public static final String FRAGMENT_CATALOG = "FRAGMENT_CATALOG";
  public static final String FRAGMENT_CATALOG_CHILD = "FRAGMENT_CATALOG_CHILD";
  public static final String FRAGMENT_PAYMENT = "FRAGMENT_PAYMENT";
  public static final String FRAGMENT_TEMPLATES = "FRAGMENT_TEMPLATES";
  public static final String FRAGMENT_MAP = "FRAGMENT_MAP";

  /** headers */
  public static final String HEADER_X_AUTH_TOKEN = "X-Auth-Token";
  public static final String HEADER_X_API_VERSION = "X-API-Version";
  public static final String HEADER_X_AUTH_APP_KEY = "X-Auth-App-Key";
  public static final String HEADER_X_AUTH_REGN = "X-Auth-Reqn";
  public static final String HEADER_X_AUTH_SIGN = "X-Auth-Sign";
  public static final String HEADER_USER_AGENT = "User-Agent";
  public static final String HEADER_ACCEPT_KEY = "accept";
  public static final String HEADER_ACCEPT_VALUE = "application/json";
  public static final String LANG = "Accept-Language";
  public static final String API_VERSION = "3";
  public static final String AUTH_APP_KEY_LIVE = "b41aa1963de1c6dafcf4aa025bae559d";
  public static final String AUTH_API_KEY_TEST = "8fa0373960942cc8dc48f8477bb9a919";

  /** register */
  public static final String SYSTEM_PHONE = "0000000001";
  public static final String SYSTEM_PIN = "8947";
  public static final String SYSTEM_REG = "1";

  /** other */
  public static final String SLUG_FAVORITES = "favorites";

}
