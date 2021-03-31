package au.em.covidapp.ui.register;

import au.em.covidapp.data.model.User;
import au.em.covidapp.ui.base.MvpView;

public interface RegisterMvpView extends MvpView {

  void startProgress();

  void onSuccessSignUp(User user);

  void showError(int errorCode);

  void stopProgress();
}
