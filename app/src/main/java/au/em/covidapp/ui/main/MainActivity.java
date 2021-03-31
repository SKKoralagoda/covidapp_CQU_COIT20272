package au.em.covidapp.ui.main;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import au.em.covidapp.CovidAppConstants;
import au.em.covidapp.R;
import au.em.covidapp.data.local.PreferenceHelper;
import au.em.covidapp.data.model.User;
import au.em.covidapp.data.model.requests.CreateReportRequest;
import au.em.covidapp.data.model.requests.CreateTestRequest;
import au.em.covidapp.data.model.responses.SuccessResponse;
import au.em.covidapp.ui.SplashActivity;
import au.em.covidapp.ui.base.BaseActivity;
import au.em.covidapp.ui.map.MapsActivity;
import au.em.covidapp.ui.result.ResultActivity;
import au.em.covidapp.ui.test.TestActivity;
import au.em.covidapp.util.CovidAppHelper;
import au.em.covidapp.util.ViewDialog;
import butterknife.ButterKnife;
import butterknife.OnClick;
import javax.inject.Inject;

public class MainActivity extends BaseActivity implements MainMvpView {

  @Inject MainPresenter mainPresenter;
  private User user;
  private CreateTestRequest createTestRequest;
  private CreateReportRequest createReportRequest;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    init();
  }

  private void init() {
    activityComponent().inject(this);
    mainPresenter.attachView(this);
    ButterKnife.bind(this);
    user = PreferenceHelper.getUserObject(this);

    if (getIntent().hasExtra(CovidAppConstants.IS_CREATE_TEST_ALLOWED)) {
      if (getIntent().getIntExtra(CovidAppConstants.IS_CREATE_TEST_ALLOWED, 0)
          == CovidAppConstants.FROM_REGISTER) {
        createTest();
      }
    }
  }

  private void createTest() {
    createTestRequest = new CreateTestRequest();
    createTestRequest.setUserId(user.getUserId());
    mainPresenter.createTest(createTestRequest);
  }

  private void createReport() {
    createReportRequest = new CreateReportRequest();
    createReportRequest.setUserId(user.getUserId());
    mainPresenter.createReport(createReportRequest);
  }

  @OnClick({R.id.ibtn_map, R.id.ibtn_news, R.id.ibtn_meter, R.id.cl_take_test,
      R.id.cl_previous_results, R.id.cl_user_manual, R.id.iv_logout})
  public void onViewClicked(View view) {
    switch (view.getId()) {
      case R.id.ibtn_map:
        startActivity(new Intent(this, MapsActivity.class));
        break;
      case R.id.ibtn_news:
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(
            "https://www.health.nsw.gov.au/infectious/covid-19/pages/recent-case-updates.aspx"));
        startActivity(intent);
        break;
      case R.id.ibtn_meter:
        startActivity(new Intent(this, MeterActivity.class));
        break;
      case R.id.cl_take_test:
        startActivity(new Intent(this, TestActivity.class));
        break;
      case R.id.cl_previous_results:
        startActivity(new Intent(this, ResultActivity.class));
        break;
      case R.id.cl_user_manual:
        Intent userManualIntent = new Intent(Intent.ACTION_VIEW);
        userManualIntent.setData(Uri.parse(
            "http://covid-app.site/covid-app/user-manual/help.html"));
        startActivity(userManualIntent);
        break;
      case R.id.iv_logout:
        ViewDialog.showConfirmDialog(this, getString(R.string.label_confirmation),
            getString(R.string.label_logout_confirmation),
            getString(R.string.label_no), () -> {

            }, getString(R.string.label_yes), () -> {
              PreferenceHelper.removePreferences(this);
              PreferenceHelper.removeUser(this);
              startActivity(new Intent(this, SplashActivity.class));
              finishAffinity();
            });
        break;
    }
  }

  @Override public void startProgress() {
    showProgress();
  }

  @Override public void onSuccessCreateTest(SuccessResponse successResponse) {
    dismissProgress();
    ViewDialog.showDialog(this, getString(R.string.label_success), "Test Created", "Continue",
        () -> createReport());
  }

  @Override public void onSuccessCreateReport(SuccessResponse successResponse) {
    ViewDialog.showDialog(this, getString(R.string.label_success), "Report Created",
        getString(R.string.label_ok));
  }

  @Override public void showError(int errorCode) {
    dismissProgress();
    ViewDialog.showDialog(this, getString(R.string.error_error),
        CovidAppHelper.getErrorMessage(this, errorCode), getString(R.string.label_ok));
  }

  @Override public void stopProgress() {
    dismissProgress();
  }
}
