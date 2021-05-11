package au.em.covidapp.util;

import android.content.Context;
import android.widget.EditText;

public class EditTextError {

  //Common functionalities to set error dialog in all app
  public static void setError(Context context, EditText editText, int errorMessage) {
    String message = context.getString(errorMessage);
    editText.setError(message);
    editText.requestFocus();
  }

}
