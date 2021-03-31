package au.em.covidapp.injection;

import au.em.covidapp.ui.register.RegisterActivity;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import javax.inject.Inject;
import javax.inject.Qualifier;

/**
 * Created by Wageesha Chinthaka on 2019-06-19.
 */
@Qualifier
@Retention(RetentionPolicy.RUNTIME)
public @interface ActivityContext {
}
