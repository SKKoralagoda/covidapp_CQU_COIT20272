package au.em.covidapp;

import android.app.Application;
import android.content.Context;
import androidx.multidex.MultiDex;
import au.em.covidapp.injection.component.ApplicationComponent;
import au.em.covidapp.injection.component.DaggerApplicationComponent;
import au.em.covidapp.injection.module.ApplicationModule;

/**
 * Created by Wageesha Chinthaka on 2019-06-20.
 */
public class BaseApplication extends Application {

  ApplicationComponent mApplicationComponent;

  public static BaseApplication get(Context context) {
    return (BaseApplication) context.getApplicationContext();
  }

  @Override public void onCreate() {
    super.onCreate();
  }

  @Override protected void attachBaseContext(Context base) {
    super.attachBaseContext(base);
    MultiDex.install(this);
  }

  public ApplicationComponent getComponent() {
    if (mApplicationComponent == null) {
      mApplicationComponent = DaggerApplicationComponent.builder()
          .applicationModule(new ApplicationModule(this))
          .build();
    }
    return mApplicationComponent;
  }

  // Needed to replace the component with a test specific one
  public void setComponent(ApplicationComponent applicationComponent) {
    mApplicationComponent = applicationComponent;
  }
}
