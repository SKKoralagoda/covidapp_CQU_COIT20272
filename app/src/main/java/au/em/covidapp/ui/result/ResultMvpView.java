package au.em.covidapp.ui.result;

import au.em.covidapp.data.model.Result;
import au.em.covidapp.ui.base.MvpView;
import java.util.List;

public interface ResultMvpView extends MvpView {

  void startProgress();

  void onSuccessGetReport(List<Result> reportList);

  void showError(int errorCode);

  void stopProgress();
}
