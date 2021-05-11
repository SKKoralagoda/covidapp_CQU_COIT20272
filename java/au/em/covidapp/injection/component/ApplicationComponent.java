package au.em.covidapp.injection.component;

import android.app.Application;
import android.content.Context;
import au.em.covidapp.data.DataManager;
import au.em.covidapp.data.local.PreferenceHelper;
import au.em.covidapp.data.remote.ApiService;
import au.em.covidapp.injection.ApplicationContext;
import au.em.covidapp.injection.module.ApplicationModule;
import dagger.Component;
import javax.inject.Singleton;

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

  //Main Supported component class files
  @ApplicationContext
  Context context();

  Application application();

  ApiService apiService();

  DataManager dataManager();

  PreferenceHelper preferenceHelper();
}
