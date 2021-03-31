package au.em.covidapp.ui.login;

import au.em.covidapp.R;
import au.em.covidapp.data.DataManager;
import au.em.covidapp.data.model.responses.LoginResponse;
import au.em.covidapp.injection.ConfigPersistent;
import au.em.covidapp.ui.base.BasePresenter;
import au.em.covidapp.util.RxUtil;
import au.em.covidapp.util.ViewDialog;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import javax.inject.Inject;
import retrofit2.HttpException;

@ConfigPersistent
public class LoginPresenter extends BasePresenter<LoginMvpView> {

  private final DataManager mDataManager;
  private Disposable mDisposable;

  @Inject LoginPresenter(DataManager dataManager) {
    mDataManager = dataManager;
  }

  @Override public void attachView(LoginMvpView mapView) {
    super.attachView(mapView);
  }

  @Override public void detachView() {
    super.detachView();
    if (mDisposable != null) {
      mDisposable.dispose();
    }
  }

  void login(String userName, String password) {
    checkViewAttached();
    RxUtil.dispose(mDisposable);
    mDataManager.login(userName, password)
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(Schedulers.io())
        .subscribe(new Observer<LoginResponse>() {
          @Override public void onSubscribe(Disposable d) {
            mDisposable = d;
            getMvpView().startProgress();
          }

          @Override public void onNext(LoginResponse loginResponse) {
            getMvpView().stopProgress();
            if (loginResponse.getUser() != null) {
              getMvpView().onSuccessLogin(loginResponse.getUser().get(0));
            } else {
              ViewDialog.showDialog(getContext(), getContext().getString(R.string.error_error),
                  "Invalid Username or Password", getContext().getString(R.string.label_ok));
            }
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
