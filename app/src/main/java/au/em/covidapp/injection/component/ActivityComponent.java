package au.em.covidapp.injection.component;

import au.em.covidapp.injection.PerActivity;
import au.em.covidapp.injection.module.ActivityModule;
import au.em.covidapp.ui.SplashActivity;
import au.em.covidapp.ui.login.LoginActivity;
import au.em.covidapp.ui.main.MainActivity;
import au.em.covidapp.ui.register.RegisterActivity;
import au.em.covidapp.ui.result.ResultActivity;
import au.em.covidapp.ui.test.TestActivity;
import dagger.Subcomponent;

/**
 * Created by Wageesha Chinthaka on 2019-06-19.
 */
@PerActivity
@Subcomponent(modules = ActivityModule.class)
public interface ActivityComponent {

  void inject(SplashActivity splashActivity);
  void inject(MainActivity mainActivity);
  void inject(RegisterActivity registerActivity);
  void inject(LoginActivity loginActivity);
  void inject(TestActivity testActivity);
  void inject(ResultActivity resultActivity);
}
