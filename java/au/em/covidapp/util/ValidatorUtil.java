package au.em.covidapp.util;

import android.text.TextUtils;

public class ValidatorUtil {

  //Common functionalities for validation in all app
  public final static boolean isValidEmailAddress(String email) {
    if (TextUtils.isEmpty(email)) {
      return false;
    } else {
      return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
  }
}
