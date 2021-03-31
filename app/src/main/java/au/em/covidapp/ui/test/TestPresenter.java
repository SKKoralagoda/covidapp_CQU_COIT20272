package au.em.covidapp.ui.test;

import au.em.covidapp.data.DataManager;
import au.em.covidapp.data.model.requests.GenerateReportRequest;
import au.em.covidapp.data.model.requests.GetSymptomsRequest;
import au.em.covidapp.data.model.requests.UpdateSymptomRequest;
import au.em.covidapp.data.model.responses.GetSymptomsResponse;
import au.em.covidapp.data.model.responses.SuccessResponse;
import au.em.covidapp.injection.ConfigPersistent;
import au.em.covidapp.ui.base.BasePresenter;
import au.em.covidapp.util.RxUtil;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import javax.inject.Inject;
import retrofit2.HttpException;

@ConfigPersistent
public class TestPresenter extends BasePresenter<TestMvpView> {

  private final DataManager mDataManager;
  private Disposable mDisposable;

  @Inject TestPresenter(DataManager dataManager) {
    mDataManager = dataManager;
  }

  @Override public void attachView(TestMvpView mapView) {
    super.attachView(mapView);
  }

  @Override public void detachView() {
    super.detachView();
    if (mDisposable != null) {
      mDisposable.dispose();
    }
  }

  void getSymptoms(GetSymptomsRequest getSymptomsRequest) {
    checkViewAttached();
    RxUtil.dispose(mDisposable);
    mDataManager.getSymptoms(getSymptomsRequest)
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(Schedulers.io())
        .subscribe(new Observer<GetSymptomsResponse>() {
          @Override public void onSubscribe(Disposable d) {
            mDisposable = d;
            getMvpView().startProgress();
          }

          @Override public void onNext(GetSymptomsResponse getSymptomsResponse) {
            getMvpView().onSuccessGetSymptoms(getSymptomsResponse.getSymptomList());
          }

          @Override public void onError(Throwable e) {
            try {
              getMvpView().showError(((HttpException) e).code());
            } catch (ClassCastException cce) {
              getMvpView().showError(7999);
            }
          }

          @Override public void onComplete() {
            getMvpView().stopProgress();
          }
        });
  }

  void updateSymptoms(UpdateSymptomRequest request) {
    checkViewAttached();
    RxUtil.dispose(mDisposable);
    mDataManager.updateSymptoms(request)
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(Schedulers.io())
        .subscribe(new Observer<SuccessResponse>() {
          @Override public void onSubscribe(Disposable d) {
            mDisposable = d;
            getMvpView().startProgress();
          }

          @Override public void onNext(SuccessResponse successResponse) {
            getMvpView().onSuccessUpdateSymptoms(successResponse);
          }

          @Override public void onError(Throwable e) {
            try {
              getMvpView().showError(((HttpException) e).code());
            } catch (ClassCastException cce) {
              getMvpView().showError(7999);
            }
          }

          @Override public void onComplete() {
            getMvpView().stopProgress();
          }
        });
  }

  void generateReport(GenerateReportRequest generateReportRequest) {
    checkViewAttached();
    RxUtil.dispose(mDisposable);
    mDataManager.generateReport(generateReportRequest)
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(Schedulers.io())
        .subscribe(new Observer<SuccessResponse>() {
          @Override public void onSubscribe(Disposable d) {
            mDisposable = d;
            getMvpView().startProgress();
          }

          @Override public void onNext(SuccessResponse successResponse) {
            getMvpView().generateReport(successResponse);
          }

          @Override public void onError(Throwable e) {
            try {
              getMvpView().showError(((HttpException) e).code());
            } catch (ClassCastException cce) {
              getMvpView().showError(7999);
            }
          }

          @Override public void onComplete() {
            getMvpView().stopProgress();
          }
        });
  }
}
