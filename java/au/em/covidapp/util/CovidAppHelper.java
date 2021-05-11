package au.em.covidapp.util;

import android.content.Context;
import au.em.covidapp.R;

public class CovidAppHelper {

  //Set error code and related messages
  public static String getErrorMessage(Context context, int errorCode) {
    String message = null;
    switch (errorCode) {
      case 402:
        message = context.getString(R.string.error_invalid_api_key);
        break;
      case 403:
        message = context.getString(R.string.error_user_name_exist);
        break;
      case 406:
      case 456:
        message = context.getString(R.string.error_credential_not_match);
        break;
      case 410:
      case 429:
      case 7999:
        message = context.getString(R.string.error_something_wrong);
        break;
      case 417:
      case 427:
        message = context.getString(R.string.error_invalid_password);
        break;
      case 418:
        message = context.getString(R.string.error_unkown);
        break;
      case 421:
      case 423:
        message = context.getString(R.string.error_data_not_found);
        break;
      case 422:
        message = context.getString(R.string.error_email_not_valid);
        break;
      case 424:
      case 404:
        message = context.getString(R.string.error_user_not_found);
        break;
      case 428:
        message = context.getString(R.string.error_email_not_match);
        break;
      case 500:
        message = context.getString(R.string.error_internal_server);
        break;
      case 401:
        message = context.getString(R.string.error_invalid_token);
        break;
      default:
        break;
    }
    return message;
  }
}
