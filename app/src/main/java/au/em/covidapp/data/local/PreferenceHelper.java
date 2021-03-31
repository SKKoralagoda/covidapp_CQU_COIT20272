package au.em.covidapp.data.local;

import android.content.Context;
import android.content.SharedPreferences;
import au.em.covidapp.CovidAppConstants;
import au.em.covidapp.data.model.User;
import au.em.covidapp.injection.ApplicationContext;
import com.google.gson.Gson;
import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by Wageesha Chinthaka on 2019-06-19.
 */
@Singleton
public class PreferenceHelper {

  private static final String PREF_FILE_NAME = "beFit_file";

  private final SharedPreferences mPref;

  @Inject
  public PreferenceHelper(@ApplicationContext Context context) {
    mPref = context.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE);
  }

  public static void saveToPreferences(Context context, User user, boolean userLoggedInState) {
    final SharedPreferences preferences = context.getSharedPreferences(
        CovidAppConstants.PREFERENCES, Context.MODE_PRIVATE);
    final SharedPreferences.Editor editor = preferences.edit();

    try {
      editor.putString(
          CovidAppConstants.PREF_KEY_ACCESS_TOKEN, user.getFirstName()
      );
      editor.putInt(CovidAppConstants.PREF_KEY_USER_ID, Integer.parseInt(user.getUserId())
      );

      editor.putBoolean(
          CovidAppConstants.PREF_KEY_USER_LOGGED_IN_STATE, userLoggedInState
      );

      editor.apply();
    } catch (final Exception e) {
      e.printStackTrace();
    }
  }

  public static void saveUser(Context context, User user) {
    final SharedPreferences preferences = context.getSharedPreferences(
        CovidAppConstants.PREFERENCES, Context.MODE_PRIVATE);
    final SharedPreferences.Editor editor = preferences.edit();

    Gson gson = new Gson();
    String userJson = gson.toJson(user);
    editor.putString(CovidAppConstants.PREF_KEY_USER, userJson);
    editor.apply();
  }

  public static boolean getUserLoggedInState(Context context) {
    final SharedPreferences prefs = getPreferences(context);

    return prefs.getBoolean(CovidAppConstants.PREF_KEY_USER_LOGGED_IN_STATE, false);
  }

  public static String getAccessToken(Context context) {
    final SharedPreferences prefs = getPreferences(context);

    return prefs.getString(CovidAppConstants.PREF_KEY_ACCESS_TOKEN, "");
  }

  private static SharedPreferences getPreferences(Context context) {
    return context.getSharedPreferences(CovidAppConstants.PREFERENCES, Context.MODE_PRIVATE);
  }

  public static void removePreferences(Context context) {
    SharedPreferences preferences =
        context.getSharedPreferences(CovidAppConstants.PREFERENCES, Context.MODE_PRIVATE);

    preferences.edit().remove(CovidAppConstants.PREF_KEY_ACCESS_TOKEN).apply();
    preferences.edit().remove(CovidAppConstants.PREF_KEY_USER_ID).apply();
    preferences.edit().putBoolean(CovidAppConstants.PREF_KEY_USER_LOGGED_IN_STATE, false).apply();
  }

  public static void removeUser(Context context) {
    SharedPreferences preferences =
        context.getSharedPreferences(CovidAppConstants.PREFERENCES, Context.MODE_PRIVATE);

    preferences.edit().remove(CovidAppConstants.PREF_KEY_USER).apply();
  }

  public static User getUserObject(Context context) {
    final SharedPreferences prefs = getPreferences(context);
    String jsonString = prefs.getString(CovidAppConstants.PREF_KEY_USER, "");

    Gson gson = new Gson();

    User userObj = gson.fromJson(jsonString, User.class);

    return userObj;
  }
}
