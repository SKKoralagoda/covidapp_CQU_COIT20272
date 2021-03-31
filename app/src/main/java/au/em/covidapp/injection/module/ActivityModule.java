package au.em.covidapp.injection.module;

import android.app.Activity;
import android.content.Context;
import au.em.covidapp.injection.ActivityContext;
import dagger.Module;
import dagger.Provides;

/**
 * Created by Wageesha Chinthaka on 2019-06-19.
 */
@Module
public class ActivityModule {

  private Activity mActivity;

  public ActivityModule(Activity activity){mActivity = activity;}

  @Provides
  Activity provideActivity() {
    return mActivity;
  }

  @Provides
  @ActivityContext
  Context providesContext() {
    return mActivity;
  }
}
