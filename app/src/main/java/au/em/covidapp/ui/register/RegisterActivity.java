package au.em.covidapp.ui.register;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;
import au.em.covidapp.CovidAppConstants;
import au.em.covidapp.R;
import au.em.covidapp.data.local.PreferenceHelper;
import au.em.covidapp.data.model.User;
import au.em.covidapp.data.model.requests.RegisterRequest;
import au.em.covidapp.ui.base.BaseActivity;
import au.em.covidapp.ui.main.MainActivity;
import au.em.covidapp.util.CovidAppHelper;
import au.em.covidapp.util.EditTextError;
import au.em.covidapp.util.NetworkUtils;
import au.em.covidapp.util.ValidatorUtil;
import au.em.covidapp.util.ViewDialog;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import javax.inject.Inject;

public class RegisterActivity extends BaseActivity implements RegisterMvpView {

  @Inject RegisterPresenter registerPresenter;

  @BindView(R.id.toolbar_register) Toolbar mToolbar;
  @BindView(R.id.et_first_name) EditText etFirstName;
  @BindView(R.id.et_last_name) EditText etLastName;
  @BindView(R.id.et_phone) EditText etPhone;
  @BindView(R.id.et_house_no) EditText etHouseNo;
  @BindView(R.id.et_house_lane) EditText etHouseLane;
  @BindView(R.id.et_house_suburb) EditText etHouseSuburb;
  @BindView(R.id.et_house_postcode) EditText etHousePostcode;
  @BindView(R.id.et_dob) EditText etDob;
  @BindView(R.id.rbtn_male) RadioButton rbtnMale;
  @BindView(R.id.rbtn_female) RadioButton rbtnFemale;
  @BindView(R.id.et_email) EditText etEmail;
  @BindView(R.id.et_password) EditText etPassword;
  @BindView(R.id.et_confirm_password) EditText etConfirmPassword;
  @BindView(R.id.tv_register_username) EditText tvRegisterUsername;

  final Calendar myCalendar = Calendar.getInstance();
  private String lStrFirstName, lStrLastName, lStrPhone, lStrHouseNo, lStrHouseLane, lStrEmail,
      lStrHouseSuburb, lStrHousePostCode, lStrDOB, lStringGender, lStrPassword, lStrConfirmPassword;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_register);
    ButterKnife.bind(this);
    init();
  }

  private void init() {
    ButterKnife.bind(this);
    activityComponent().inject(this);
    registerPresenter.attachView(this);
    setToolbar();
    lStringGender = "male";
    setDatePicker();

    etEmail.addTextChangedListener(new TextWatcher() {
      @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {

      }

      @Override public void onTextChanged(CharSequence s, int start, int before, int count) {
        tvRegisterUsername.setText(s.toString());
      }

      @Override public void afterTextChanged(Editable s) {

      }
    });
  }

  private void setToolbar() {
    setSupportActionBar(mToolbar);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    getSupportActionBar().setDisplayShowHomeEnabled(true);
    getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back);
  }

  private void setDatePicker() {
    DatePickerDialog.OnDateSetListener date = (view, year, monthOfYear, dayOfMonth) -> {
      myCalendar.set(Calendar.YEAR, year);
      myCalendar.set(Calendar.MONTH, monthOfYear);
      myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
      updateLabel();
    };

    etDob.setOnClickListener(v -> new DatePickerDialog(RegisterActivity.this, date, myCalendar
        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
        myCalendar.get(Calendar.DAY_OF_MONTH)).show());
  }

  private void updateLabel() {
    String myFormat = "yyyy-MM-dd";
    SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
    etDob.setText(sdf.format(myCalendar.getTime()));
  }

  private int getAge(String dobString) {
    Date date = null;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
    try {
      date = sdf.parse(dobString);
    } catch (ParseException e) {
      e.printStackTrace();
    }

    if (date == null) return 0;

    Calendar dob = Calendar.getInstance();
    Calendar today = Calendar.getInstance();

    dob.setTime(date);

    int year = dob.get(Calendar.YEAR);
    int month = dob.get(Calendar.MONTH);
    int day = dob.get(Calendar.DAY_OF_MONTH);

    dob.set(year, month + 1, day);

    int age = today.get(Calendar.YEAR) - dob.get(Calendar.YEAR);

    if (today.get(Calendar.DAY_OF_YEAR) < dob.get(Calendar.DAY_OF_YEAR)) {
      age--;
    }

    return age;
  }

  private void actionOnClickRegister() {
    lStrFirstName = etFirstName.getText().toString().trim();
    lStrLastName = etLastName.getText().toString().trim();
    lStrPhone = etPhone.getText().toString().trim();
    lStrHouseNo = etHouseNo.getText().toString().trim();
    lStrHouseLane = etHouseLane.getText().toString().trim();
    lStrHouseSuburb = etHouseSuburb.getText().toString().trim();
    lStrHousePostCode = etHousePostcode.getText().toString().trim();
    lStrDOB = etDob.getText().toString().trim();
    lStrEmail = etEmail.getText().toString().trim();
    lStrPassword = etPassword.getText().toString().trim();
    lStrConfirmPassword = etConfirmPassword.getText().toString().trim();

    if (lStrFirstName.isEmpty()) {
      EditTextError.setError(this, etFirstName, R.string.label_first_name_required);
      return;
    }

    if (lStrLastName.isEmpty()) {
      EditTextError.setError(this, etLastName, R.string.label_last_name_required);
      return;
    }

    if (lStrPhone.isEmpty()) {
      EditTextError.setError(this, etPhone, R.string.label_phone_required);
      return;
    }

    if (lStrPhone.length() < 6 || lStrPhone.length() > 13) {
      EditTextError.setError(this, etPhone, R.string.label_phone_not_valid);
      return;
    }

    if (lStrHouseNo.isEmpty() || lStrHouseLane.isEmpty()) {
      Toast.makeText(this, "Please enter house address", Toast.LENGTH_SHORT).show();
      return;
    }

    if (lStrDOB.isEmpty()) {
      EditTextError.setError(this, etDob, R.string.label_dob_required);
      return;
    }

    if (lStringGender.isEmpty()) {
      Toast.makeText(this, "Please select your gender", Toast.LENGTH_SHORT).show();
      return;
    }

    if (lStrEmail.isEmpty()) {
      EditTextError.setError(this, etEmail, R.string.label_email_required);
      return;
    }

    if (!ValidatorUtil.isValidEmailAddress(lStrEmail)) {
      EditTextError.setError(this, etEmail, R.string.label_email_not_valid);
      return;
    }

    if (lStrPassword.isEmpty()) {
      EditTextError.setError(this, etPassword, R.string.label_password_required);
      return;
    }

    if (lStrPassword.length() < 8) {
      EditTextError.setError(this, etPassword, R.string.label_password_length);
      return;
    }

    if (lStrConfirmPassword.isEmpty()) {
      EditTextError.setError(this, etConfirmPassword, R.string.label_re_enter_password);
      return;
    }

    if (!lStrPassword.equals(lStrConfirmPassword)) {
      EditTextError.setError(this, etConfirmPassword, R.string.label_password_not_match);
      return;
    }

    if (NetworkUtils.isNetworkConnected(this)) {
      RegisterRequest request = new RegisterRequest();
      request.setFirstName(lStrFirstName);
      request.setLastName(lStrLastName);
      request.setPhone(lStrPhone);
      request.setHouseNo(lStrHouseNo);
      request.setHouseLane(lStrHouseLane);
      request.setHouseSuburb(lStrHouseSuburb);
      request.setPostCode(lStrHousePostCode);
      request.setAge(String.valueOf(getAge(etDob.getText().toString().trim())));
      request.setGender(lStringGender);
      request.setEmail(lStrEmail);
      request.setUserName(tvRegisterUsername.getText().toString().trim());
      request.setPassword(lStrPassword);
      registerPresenter.registerUser(request);
    } else {
      Toast.makeText(this, getString(R.string.label_check_internet), Toast.LENGTH_SHORT).show();
    }
  }

  @Override public boolean onSupportNavigateUp() {
    onBackPressed();
    return super.onSupportNavigateUp();
  }

  @OnClick({R.id.rbtn_male, R.id.rbtn_female, R.id.btn_register})
  public void onViewClicked(View view) {
    switch (view.getId()) {
      case R.id.rbtn_male:
        lStringGender = "Male";
        break;
      case R.id.rbtn_female:
        lStringGender = "Female";
        break;
      case R.id.btn_register:
        actionOnClickRegister();
        break;
    }
  }

  @Override public void startProgress() {
    showProgress();
  }

  @Override public void onSuccessSignUp(User user) {
    dismissProgress();
    if (user == null) {
      ViewDialog.showDialog(this, getString(R.string.error_error),
          "Invalid Username or Password", getString(R.string.label_ok));
    } else {
      PreferenceHelper.saveUser(this, user);
      Intent intent = new Intent(this, MainActivity.class);
      intent.putExtra(CovidAppConstants.IS_CREATE_TEST_ALLOWED, CovidAppConstants.FROM_REGISTER);
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
