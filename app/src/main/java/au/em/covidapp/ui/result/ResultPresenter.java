package au.em.covidapp.ui.result;

import au.em.covidapp.data.DataManager;
import au.em.covidapp.data.model.requests.GetReportRequest;
import au.em.covidapp.data.model.responses.GetResultResponse;
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
public class ResultPresenter extends BasePresenter<ResultMvpView> {

  private final DataManager mDataManager;
  private Disposable mDisposable;

  @Inject ResultPresenter(DataManager dataManager) {
    mDataManager = dataManager;
  }

  @Override public void attachView(ResultMvpView mapView) {
    super.attachView(mapView);
  }

  @Override public void detachView() {
    super.detachView();
    if (mDisposable != null) {
      mDisposable.dispose();
    }
  }

  void getReport(GetReportRequest getReportRequest) {
    checkViewAttached();
    RxUtil.dispose(mDisposable);
    mDataManager.getResult(getReportRequest)
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(Schedulers.io())
        .subscribe(new Observer<GetResultResponse>() {
          @Override public void onSubscribe(Disposable d) {
            mDisposable = d;
            getMvpView().startProgress();
          }

          @Override public void onNext(GetResultResponse getResultResponse) {
            getMvpView().onSuccessGetReport(getResultResponse.getResultList());
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
