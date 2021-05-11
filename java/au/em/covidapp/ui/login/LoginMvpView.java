package au.em.covidapp.ui.login;

import au.em.covidapp.data.model.User;
import au.em.covidapp.ui.base.MvpView;

public interface LoginMvpView extends MvpView {

  void startProgress();

  void onSuccessLogin(User user);

  void showError(int errorCode);

  void stopProgress();
}
