package au.em.covidapp.ui.base;

public interface Presenter <V extends MvpView> {

  void attachView(V mapView);

  void detachView();

}
