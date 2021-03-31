package au.em.covidapp.ui.base;

/**
 * Created by Wageesha Chinthaka on 2019-06-20.
 */
public interface Presenter <V extends MvpView> {

  void attachView(V mapView);

  void detachView();

}
