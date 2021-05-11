package au.em.covidapp.ui.test;

import au.em.covidapp.data.model.Symptoms;
import au.em.covidapp.data.model.responses.SuccessResponse;
import au.em.covidapp.ui.base.MvpView;
import java.util.List;

public interface TestMvpView extends MvpView {

  void startProgress();

  void onSuccessGetSymptoms(List<Symptoms> symptomsList);

  void onSuccessUpdateSymptoms(SuccessResponse successResponse);

  void generateReport(SuccessResponse successResponse);

  void showError(int errorCode);

  void stopProgress();
}
