package au.em.covidapp.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;
import au.em.covidapp.CovidAppConstants;
import au.em.covidapp.R;
import au.em.covidapp.data.local.PreferenceHelper;
import au.em.covidapp.data.model.User;
import au.em.covidapp.ui.base.BaseActivity;
import au.em.covidapp.ui.main.MainActivity;
import au.em.covidapp.util.CovidAppHelper;
import au.em.covidapp.util.EditTextError;
import au.em.covidapp.util.ValidatorUtil;
import au.em.covidapp.util.ViewDialog;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import javax.inject.Inject;

public class LoginActivity extends BaseActivity implements LoginMvpView {

  @Inject LoginPresenter loginPresenter;

  @BindView(R.id.toolbar_login) Toolbar mToolbar;
  @BindView(R.id.et_username) EditText etUsername;
  @BindView(R.id.et_password) EditText etPassword;
  @BindView(R.id.tv_show_hide_password) TextView tvShowHidePassword;

  private String lStrUserName, lStrPassword;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_login);
    init();
  }

  private void init() {
    activityComponent().inject(this);
    loginPresenter.attachView(this);
    ButterKnife.bind(this);
    setToolbar();
  }

  private void setToolbar() {
    setSupportActionBar(mToolbar);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    getSupportActionBar().setDisplayShowHomeEnabled(true);
    getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back);
  }

  private void showHidePassword() {
    if (etPassword.getTransformationMethod().equals(PasswordTransformationMethod.getInstance())) {
      tvShowHidePassword.setText(R.string.label_hide);
      etPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
    } else {
      tvShowHidePassword.setText(R.string.label_show);
      etPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
    }
  }

  private void onClickLogin() {
    lStrUserName = etUsername.getText().toString().trim();
    lStrPassword = etPassword.getText().toString().trim();

    if (lStrUserName.isEmpty()) {
      EditTextError.setError(this, etUsername, R.string.label_username_required);
      return;
    }

    if (!ValidatorUtil.isValidEmailAddress(lStrUserName)) {
      EditTextError.setError(this, etUsername, R.string.label_username_not_valid);
      return;
    }

    if (lStrPassword.isEmpty()) {
      EditTextError.setError(this, etPassword, R.string.label_password_required);
      return;
    }

    loginPresenter.login(lStrUserName, lStrPassword);
  }

  @Override public boolean onSupportNavigateUp() {
    onBackPressed();
    return super.onSupportNavigateUp();
  }

  @OnClick({R.id.tv_show_hide_password, R.id.btn_login}) public void onViewClicked(View view) {
    switch (view.getId()) {
      case R.id.tv_show_hide_password:
        showHidePassword();
        break;
      case R.id.btn_login:
        onClickLogin();
        break;
    }
  }

  @Override public void startProgress() {
    showProgress();
  }

  @Override public void onSuccessLogin(User user) {
    dismissProgress();
    if (user == null) {
      ViewDialog.showDialog(this, getString(R.string.error_error),
          "Invalid Username or Password", getString(R.string.label_ok));
    } else {
      PreferenceHelper.saveUser(this, user);
      Intent intent = new Intent(this, MainActivity.class);
      intent.putExtra(CovidAppConstants.IS_CREATE_TEST_ALLOWED, CovidAppConstants.FROM_LOGIN);
      startActivity(intent);
      finishAffinity();
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
