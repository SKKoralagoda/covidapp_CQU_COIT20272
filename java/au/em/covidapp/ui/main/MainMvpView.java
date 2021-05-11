package au.em.covidapp.ui.main;

import au.em.covidapp.data.model.responses.SuccessResponse;
import au.em.covidapp.ui.base.MvpView;

public interface MainMvpView extends MvpView {

  void startProgress();

  void onSuccessCreateTest(SuccessResponse successResponse);

  void onSuccessCreateReport(SuccessResponse successResponse);

  void showError(int errorCode);

  void stopProgress();
}
