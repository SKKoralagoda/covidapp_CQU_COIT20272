package au.em.covidapp.ui.main;

import au.em.covidapp.data.DataManager;
import au.em.covidapp.data.model.requests.CreateReportRequest;
import au.em.covidapp.data.model.requests.CreateTestRequest;
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
public class MainPresenter extends BasePresenter<MainMvpView> {

  private final DataManager mDataManager;
  private Disposable mDisposable;

  @Inject MainPresenter(DataManager dataManager) {
    mDataManager = dataManager;
  }

  @Override public void attachView(MainMvpView mapView) {
    super.attachView(mapView);
  }

  @Override public void detachView() {
    super.detachView();
    if (mDisposable != null) {
      mDisposable.dispose();
    }
  }

  void createTest(CreateTestRequest createTestRequest) {
    checkViewAttached();
    RxUtil.dispose(mDisposable);
    mDataManager.createTest(createTestRequest)
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(Schedulers.io())
        .subscribe(new Observer<SuccessResponse>() {
          @Override public void onSubscribe(Disposable d) {
            mDisposable = d;
            getMvpView().startProgress();
          }

          @Override public void onNext(SuccessResponse successResponse) {
            getMvpView().onSuccessCreateTest(successResponse);
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

  void createReport(CreateReportRequest createReportRequest) {
    checkViewAttached();
    RxUtil.dispose(mDisposable);
    mDataManager.createReport(createReportRequest)
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(Schedulers.io())
        .subscribe(new Observer<SuccessResponse>() {
          @Override public void onSubscribe(Disposable d) {
            mDisposable = d;
            getMvpView().startProgress();
          }

          @Override public void onNext(SuccessResponse successResponse) {
            getMvpView().onSuccessCreateReport(successResponse);
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
