package au.em.covidapp.ui.result;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import au.em.covidapp.R;
import au.em.covidapp.data.local.PreferenceHelper;
import au.em.covidapp.data.model.Result;
import au.em.covidapp.data.model.User;
import au.em.covidapp.data.model.requests.GetReportRequest;
import au.em.covidapp.ui.base.BaseActivity;
import au.em.covidapp.ui.map.MapsActivity;
import au.em.covidapp.util.CovidAppHelper;
import au.em.covidapp.util.ViewDialog;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import java.util.List;
import javax.inject.Inject;

public class ResultActivity extends BaseActivity implements ResultMvpView {

  @Inject ResultPresenter resultPresenter;

  @BindView(R.id.toolbar_result) Toolbar mToolbar;
  @BindView(R.id.tv_risk_meter) TextView tvRiskMeter;
  @BindView(R.id.tv_recommendation) TextView tvRecommendation;
  @BindView(R.id.cl_result_base) ConstraintLayout clResultBase;
  @BindView(R.id.tv_no_data_result) TextView tvNoDataResult;

  private User user;
  private GetReportRequest getReportRequest;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_result);
    ButterKnife.bind(this);
    init();
  }

  private void init() {
    activityComponent().inject(this);
    resultPresenter.attachView(this);
    ButterKnife.bind(this);
    setToolbar();
    user = PreferenceHelper.getUserObject(this);
    getReportRequest = new GetReportRequest();
    getReportRequest.setUserId(user.getUserId());
    resultPresenter.getReport(getReportRequest);
  }

  private void setToolbar() {
    setSupportActionBar(mToolbar);
    getSupportActionBar().setDisplayShowHomeEnabled(true);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    getSupportActionBar().setTitle(getString(R.string.label_results));
    getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back);
  }

  private void setData(Result data) {
    tvRiskMeter.setText(data.getRisk());
    tvRecommendation.setText(data.getRecommended());
  }

  @Override public boolean onSupportNavigateUp() {
    onBackPressed();
    return super.onSupportNavigateUp();
  }

  @OnClick(R.id.btn_go_consultant) public void onViewClicked() {
    startActivity(new Intent(this, MapsActivity.class));
  }

  @Override public void startProgress() {
    showProgress();
  }

  @Override public void onSuccessGetReport(List<Result> reportList) {
    dismissProgress();

    if (reportList != null && reportList.size() > 0) {
      tvNoDataResult.setVisibility(View.GONE);
      clResultBase.setVisibility(View.VISIBLE);
      setData(reportList.get(0));
    } else {
      clResultBase.setVisibility(View.GONE);
      tvNoDataResult.setVisibility(View.VISIBLE);
    }
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
