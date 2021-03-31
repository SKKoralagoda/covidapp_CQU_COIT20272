package au.em.covidapp.injection.component;

import au.em.covidapp.injection.ConfigPersistent;
import au.em.covidapp.injection.module.ActivityModule;
import dagger.Component;

/**
 * Created by Wageesha Chinthaka on 2019-06-19.
 */
@ConfigPersistent
@Component(dependencies = ApplicationComponent.class)
public interface ConfigPersistentComponent {
  ActivityComponent activityComponent(ActivityModule activityModule);
}
