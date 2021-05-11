package au.em.covidapp.injection.module;

import android.app.Application;
import android.content.Context;
import au.em.covidapp.data.remote.ApiService;
import au.em.covidapp.injection.ApplicationContext;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;

@Module
public class ApplicationModule {

  private final Application mApplication;

  public ApplicationModule(Application application) {
    mApplication = application;
  }

  @Provides
  Application provideApplication() {
    return mApplication;
  }

  @Provides
  @ApplicationContext
  Context provideContext() {
    return mApplication;
  }

  @Provides
  @Singleton
  ApiService provideService() {
    return ApiService.Creator.newApiService();
  }
}
