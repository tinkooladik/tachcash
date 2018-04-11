package com.tachcash.utils;

/**
 * Created by Alexandra on 11/3/2017.
 */

public class Constants {

  // public static final String BASE_URL = "https://prenew.hotcoin.io/"; //test
  public static final String BASE_URL = "https://api.hotcoin.io/"; //prod
  public static final String GRANT_TYPE = "password";
  public static final String CLIENT_ID = "2";
  // public static final String CLIENT_SECRET = "rVdgcQFdyhJwMxMj9NIAh8vemkit5X0Y8aJAO3Hj"; //test
  public static final String CLIENT_SECRET = "qsgl49gtH2O1NffgPDgsvKyEYWO2gN5pr1J0lvuT"; //prod

  public final class ArgumentKeys {
    public static final String EMAIL = "EMAIL";
    public static final String PHONE = "PHONE";
    public static final String TRANSACTION = "TRANSACTION";
    public static final String WALLET = "WALLET";
    public static final String AMOUNT = "AMOUNT";
    public static final String COMMENT = "COMMENT";
    public static final String TITLE_EMAIL = "TITLE_EMAIL";
    public static final String MESSAGE = "MESSAGE";
    public static final String REDIRECT_URL = "REDIRECT_URL";
    public static final String SEND_REQUEST = "SEND_REQUEST";
    public static final String TRANSACTION_ID = "TRANSACTION_ID";
    public static final String CHECK_TYPE = "CHECK_TYPE";
    public static final String FIAT_BALANCE = "FIAT_BALANCE";
    public static final String IS_REG_FINISHED = "IS_REG_FINISHED";
    public static final String UPDATE_LANGUAGE = "UPDATE_LANGUAGE";
  }

  public final class Other {
    public static final String EXCHANGE_RATE_ADAPTER_PRESENTER = "EXCHANGE_RATE_ADAPTER_PRESENTER";
    public static final int FIELD_FROM = 0;
    public static final int FIELD_TO = 1;
    public static final String TWO_FA_ENABLED = "enabled";
    public static final String TWO_FA_WAIT = "wait_confirm";
    public static final String TWO_FA_DISABLED = "disabled";
  }

  public final class TransactionType {
    public static final String INCOME = "income";
    public static final String OUTCOME = "outcome";
    public static final String ALL = "all";
    public static final String TR_STATUS_SUCCESS = "success";
    public static final String TR_STATUS_COMPLETE = "complete";
    public static final String WAIT_NONE = "none";
    public static final String OPERATION_SELL = "sell";
    public static final String OPERATION_SEND = "send";
    public static final String OPERATION_BUY = "buy";
    public static final String OPERATION_RECEIVE = "receive";
    public static final String STATUS_COMPLETE = "complete";
  }

  public final class AddressType {
    public static final String WALLET = "wallet";
    public static final String EMAIL = "email";
    public static final String PHONE = "phone";
  }

  public final class HISTORY {
    public static final String CREATED_AT = "created_at";
    public static final String ORDER_DESC = "desc";
    public static final String CHART_TIME_MONTH = "1M";
    public static final String CHART_TIME_HOUR = "1H";
    public static final String CHART_TIME_WEEK = "1W";
    public static final String CHART_TIME_YEAR = "1Y";
    public static final String STATUS_COMPLETE = "complete";
    public static final String STATUS_PAYED = "payed";
    public static final String STATUS_SUCCESS = "success";
    public static final String STATUS_CANCEL = "cancel";
    public static final String STATUS_PENDING = "pending";
    public static final String STATUS_CREATED = "created";
    public static final String STATUS_UNCONFIRMED = "unconfirmed";
    public static final String STATUS_PROCEEDED = "proceeded";
    public static final String STATUS_ERROR = "error";
  }

  public final class PushType {
    public static final String OAUTH_TOKEN_CREATED = "OAUTH_TOKEN_CREATED";
    public static final String TRANSACTION_SEND_CRYPTO = "TRANSACTION_SEND_CRYPTO";
    public static final String TRANSACTION_COMPLETE = "TRANSACTION_COMPLETE";
    public static final String QUERY_TYPE = "type=";
    public static final String QUERY_AGENT = "agent=";
    public static final String QUERY_IP = "ip=";
    public static final String QUERY_ADDRESS = "from_address=";
    public static final String QUERY_USER = "from_user=";
    public static final String QUERY_ID = "&id=";
    public static final String WALLET_ID = "wallet_id=";
    public static final String WALLET_CODE = "wallet_code=";
    public static final String SEPARATOR = "&";
    public static final String NOTIFICATION_CHANNEL_ID = "NOTIFICATION_CHANNEL_ID";
    public static final String NOTIFICATION_CHANNEL_NAME = "Hotcoin";
    public static final String NOTIFICATION_EXTRA = "NOTIFICATION_EXTRA";
  }

  public final class ResponseCode {
    public static final int CODE_OK = 200;
    public static final int CODE_UNAUTHENTICATED = 400;
    public static final int CODE_CONFIRM_PHONE = 1012;
    public static final int CODE_CONFIRM_EMAIL = 1013;
    public static final int CODE_PROVIDE_EMAIL = 1015;
    public static final int CODE_WRONG_VERIFICATION = 1700;
    public static final String STATUS_SUCCESS = "success";
    public static final String ERROR_UNAUTHENTICATED = "unauthenticated";
  }

  public final class Headers {
    public static final String ACCEPT = "Accept";
    public static final String APP_JSON = "application/json";
    public static final String LANG = "Accept-Language";
    public static final String USER_AGENT = "User-Agent";
  }

  public final class WebView {
    public static final String WEBVIEW_LOG =
        "javascript:console.log('<head>'+document.getElementsByTagName('html')[0].innerHTML+'</head>')";
    public static final String HOTCOIN_SUCCESS = "hotcoin success";
  }

  public final class ActivityResult {
    public static final int PICK_CONTACT = 243;
    public static final int EXPAND_EMAIL = 0;
    public static final int EXPAND_PASSWORD = 1;
    public static final int EXPAND_PROFILE = 2;
    public static final int EXPAND_SEND_WALLET = 0;
    public static final int EXPAND_SEND_EMAIL = 1;
    public static final int EXPAND_SEND_PHONE = 2;
  }
}
