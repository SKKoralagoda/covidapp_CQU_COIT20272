package au.em.covidapp.ui.test;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import androidx.appcompat.widget.Toolbar;
import au.em.covidapp.R;
import au.em.covidapp.data.local.PreferenceHelper;
import au.em.covidapp.data.model.Symptoms;
import au.em.covidapp.data.model.User;
import au.em.covidapp.data.model.requests.GenerateReportRequest;
import au.em.covidapp.data.model.requests.GetSymptomsRequest;
import au.em.covidapp.data.model.requests.UpdateSymptomRequest;
import au.em.covidapp.data.model.responses.SuccessResponse;
import au.em.covidapp.ui.base.BaseActivity;
import au.em.covidapp.ui.result.ResultActivity;
import au.em.covidapp.util.CovidAppHelper;
import au.em.covidapp.util.ViewDialog;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import javax.inject.Inject;

public class TestActivity extends BaseActivity implements TestMvpView {

  @Inject TestPresenter testPresenter;

  @BindView(R.id.toolbar_test) Toolbar mToolbar;
  @BindView(R.id.et_body_temperature) EditText etBodyTemperature;
  @BindView(R.id.rbtn_dry_cough_yes) RadioButton rbtnDryCoughYes;
  @BindView(R.id.rbtn_dry_cough_no) RadioButton rbtnDryCoughNo;
  @BindView(R.id.rbtn_sneezing_yes) RadioButton rbtnSneezingYes;
  @BindView(R.id.rbtn_sneezing_no) RadioButton rbtnSneezingNo;
  @BindView(R.id.rbtn_sore_throat_yes) RadioButton rbtnSoreThroatYes;
  @BindView(R.id.rbtn_sore_throat_no) RadioButton rbtnSoreThroatNo;
  @BindView(R.id.rbtn_runny_nose_yes) RadioButton rbtnRunnyNoseYes;
  @BindView(R.id.rbtn_runny_nose_no) RadioButton rbtnRunnyNoseNo;
  @BindView(R.id.rbtn_stomach_pain_yes) RadioButton rbtnStomachPainYes;
  @BindView(R.id.rbtn_stomach_pain_no) RadioButton rbtnStomachPainNo;
  @BindView(R.id.rbtn_diarrhea_yes) RadioButton rbtnDiarrheaYes;
  @BindView(R.id.rbtn_diarrhea_no) RadioButton rbtnDiarrheaNo;
  @BindView(R.id.rbtn_weakness_yes) RadioButton rbtnWeaknessYes;
  @BindView(R.id.rbtn_weakness_no) RadioButton rbtnWeaknessNo;
  @BindView(R.id.rbtn_moderate_cough_yes) RadioButton rbtnModerateCoughYes;
  @BindView(R.id.rbtn_moderate_cough_no) RadioButton rbtnModerateCoughNo;
  @BindView(R.id.rbtn_high_cough_yes) RadioButton rbtnHighCoughYes;
  @BindView(R.id.rbtn_high_cough_no) RadioButton rbtnHighCoughNo;
  @BindView(R.id.rbtn_breathless_yes) RadioButton rbtnBreathlessYes;
  @BindView(R.id.rbtn_breathless_no) RadioButton rbtnBreathlessNo;
  @BindView(R.id.rbtn_difficult_breathe_yes) RadioButton rbtnDifficultBreatheYes;
  @BindView(R.id.rbtn_difficult_breathe_no) RadioButton rbtnDifficultBreatheNo;
  @BindView(R.id.rbtn_drowsiness_yes) RadioButton rbtnDrowsinessYes;
  @BindView(R.id.rbtn_drowsiness_no) RadioButton rbtnDrowsinessNo;
  @BindView(R.id.rbtn_chest_pain_yes) RadioButton rbtnChestPainYes;
  @BindView(R.id.rbtn_chest_pain_no) RadioButton rbtnChestPainNo;
  @BindView(R.id.rbtn_severe_weakness_yes) RadioButton rbtnSevereWeaknessYes;
  @BindView(R.id.rbtn_severe_weakness_no) RadioButton rbtnSevereWeaknessNo;
  @BindView(R.id.rbtn_diabetes_yes) RadioButton rbtnDiabetesYes;
  @BindView(R.id.rbtn_diabetes_no) RadioButton rbtnDiabetesNo;
  @BindView(R.id.rbtn_high_bp_yes) RadioButton rbtnHighBpYes;
  @BindView(R.id.rbtn_high_bp_no) RadioButton rbtnHighBpNo;
  @BindView(R.id.rbtn_heart_disease_yes) RadioButton rbtnHeartDiseaseYes;
  @BindView(R.id.rbtn_heart_disease_no) RadioButton rbtnHeartDiseaseNo;
  @BindView(R.id.rbtn_kidney_disease_yes) RadioButton rbtnKidneyDiseaseYes;
  @BindView(R.id.rbtn_kidney_disease_no) RadioButton rbtnKidneyDiseaseNo;
  @BindView(R.id.rbtn_lung_disorder_yes) RadioButton rbtnLungDisorderYes;
  @BindView(R.id.rbtn_lung_disorder_no) RadioButton rbtnLungDisorderNo;
  @BindView(R.id.rbtn_stroke_yes) RadioButton rbtnStrokeYes;
  @BindView(R.id.rbtn_stroke_no) RadioButton rbtnStrokeNo;
  @BindView(R.id.rbtn_immune_yes) RadioButton rbtnImmuneYes;
  @BindView(R.id.rbtn_immune_no) RadioButton rbtnImmuneNo;
  @BindView(R.id.rbtn_travel_days_yes) RadioButton rbtnTravelDaysYes;
  @BindView(R.id.rbtn_travel_days_no) RadioButton rbtnTravelDaysNo;
  @BindView(R.id.rbtn_travel_hrs_yes) RadioButton rbtnTravelHrsYes;
  @BindView(R.id.rbtn_travel_hrs_no) RadioButton rbtnTravelHrsNo;
  @BindView(R.id.rbtn_contact_covid_yes) RadioButton rbtnContactCovidYes;
  @BindView(R.id.rbtn_contact_covid_no) RadioButton rbtnContactCovidNo;
  @BindView(R.id.btn_next) Button btnNext;

  private User user;
  private GetSymptomsRequest getSymptomsRequest;
  private UpdateSymptomRequest updateSymptomRequest;
  private GenerateReportRequest generateReportRequest;
  private SimpleDateFormat sdf;
  private String dryCough, sneezing, soreThroat, runnyNose, stomachPain, diarrhea, weakness,
      moderateCough, highCough, breathless, difficultToBreath, drowsiness, chestPain,
      severeWeakness, diabetes, highBP, heartDisease, kidneyDisease, lungDisorder, stroke, immune,
      travelDays, travelHrs, contactWithCovid;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_test);
    ButterKnife.bind(this);
    init();
  }

  private void init() {
    activityComponent().inject(this);
    testPresenter.attachView(this);
    ButterKnife.bind(this);
    setToolbar();
    user = PreferenceHelper.getUserObject(this);
    getSymptomsRequest = new GetSymptomsRequest();
    updateSymptomRequest = new UpdateSymptomRequest();
    generateReportRequest = new GenerateReportRequest();
    sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
    getSymptoms();
  }

  private void setToolbar() {
    setSupportActionBar(mToolbar);
    getSupportActionBar().setDisplayShowHomeEnabled(true);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    getSupportActionBar().setTitle(getString(R.string.label_test));
    getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back);
  }

  private void getSymptoms() {
    getSymptomsRequest.setUserId(user.getUserId());
    testPresenter.getSymptoms(getSymptomsRequest);
  }

  private void setData(Symptoms testData) {
    etBodyTemperature.setText(testData.getTemperature());

    if (testData.getDryCough() != null) {
      if (testData.getDryCough().equals("Y")) {
        rbtnDryCoughYes.setChecked(true);
      } else if (testData.getDryCough() != null && testData.getDryCough().equals("N")) {
        rbtnDryCoughNo.setChecked(true);
      }
    }

    if (testData.getSneezing() != null) {
      if (testData.getSneezing().equals("Y")) {
        rbtnSneezingYes.setChecked(true);
      } else if (testData.getSneezing().equals("N")) {
        rbtnSneezingNo.setChecked(true);
      }
    }

    if (testData.getSoreThroat() != null) {
      if (testData.getSoreThroat().equals("Y")) {
        rbtnSoreThroatYes.setChecked(true);
      } else if (testData.getSoreThroat().equals("N")) {
        rbtnSoreThroatNo.setChecked(true);
      }
    }

    if (testData.getRunnyNose() != null) {
      if (testData.getRunnyNose().equals("Y")) {
        rbtnRunnyNoseYes.setChecked(true);
      } else if (testData.getRunnyNose().equals("N")) {
        rbtnRunnyNoseNo.setChecked(true);
      }
    }

    if (testData.getStomachPain() != null) {
      if (testData.getStomachPain().equals("Y")) {
        rbtnStomachPainYes.setChecked(true);
      } else if (testData.getStomachPain().equals("N")) {
        rbtnStomachPainNo.setChecked(true);
      }
    }

    if (testData.getDiarrhea() != null) {
      if (testData.getDiarrhea().equals("Y")) {
        rbtnDiarrheaYes.setChecked(true);
      } else if (testData.getDiarrhea().equals("N")) {
        rbtnDiarrheaNo.setChecked(true);
      }
    }

    if (testData.getWeakness() != null) {
      if (testData.getWeakness().equals("Y")) {
        rbtnWeaknessYes.setChecked(true);
      } else if (testData.getWeakness().equals("N")) {
        rbtnWeaknessNo.setChecked(true);
      }
    }

    if (testData.getModerateCough() != null) {
      if (testData.getModerateCough().equals("Y")) {
        rbtnModerateCoughYes.setChecked(true);
      } else if (testData.getModerateCough().equals("N")) {
        rbtnModerateCoughNo.setChecked(true);
      }
    }

    if (testData.getHighCough() != null) {
      if (testData.getHighCough().equals("Y")) {
        rbtnHighCoughYes.setChecked(true);
      } else if (testData.getHighCough().equals("N")) {
        rbtnHighCoughNo.setChecked(true);
      }
    }

    if (testData.getFeelingBreathless() != null) {
      if (testData.getFeelingBreathless().equals("Y")) {
        rbtnBreathlessYes.setChecked(true);
      } else if (testData.getFeelingBreathless().equals("N")) {
        rbtnBreathlessNo.setChecked(true);
      }
    }

    if (testData.getDifficultBreathless() != null) {
      if (testData.getDifficultBreathless().equals("Y")) {
        rbtnDifficultBreatheYes.setChecked(true);
      } else if (testData.getDifficultBreathless().equals("N")) {
        rbtnDifficultBreatheNo.setChecked(true);
      }
    }

    if (testData.getDrowsiness() != null) {
      if (testData.getDrowsiness().equals("Y")) {
        rbtnDrowsinessYes.setChecked(true);
      } else if (testData.getDrowsiness().equals("N")) {
        rbtnDrowsinessNo.setChecked(true);
      }
    }

    if (testData.getChestPain() != null) {
      if (testData.getChestPain().equals("Y")) {
        rbtnChestPainYes.setChecked(true);
      } else if (testData.getChestPain().equals("N")) {
        rbtnChestPainNo.setChecked(true);
      }
    }

    if (testData.getSevereWeakness() != null) {
      if (testData.getSevereWeakness().equals("Y")) {
        rbtnSevereWeaknessYes.setChecked(true);
      } else if (testData.getSevereWeakness().equals("N")) {
        rbtnSevereWeaknessNo.setChecked(true);
      }
    }

    if (testData.getDiabetes() != null) {
      if (testData.getDiabetes().equals("Y")) {
        rbtnDiabetesYes.setChecked(true);
      } else if (testData.getDiabetes().equals("N")) {
        rbtnDiabetesNo.setChecked(true);
      }
    }

    if (testData.getHighBloodPressure() != null) {
      if (testData.getHighBloodPressure().equals("Y")) {
        rbtnHighBpYes.setChecked(true);
      } else if (testData.getHighBloodPressure().equals("N")) {
        rbtnHighBpNo.setChecked(true);
      }
    }

    if (testData.getHeartDisease() != null) {
      if (testData.getHeartDisease().equals("Y")) {
        rbtnHeartDiseaseYes.setChecked(true);
      } else if (testData.getHeartDisease().equals("N")) {
        rbtnHeartDiseaseNo.setChecked(true);
      }
    }

    if (testData.getKidneyDisease() != null) {
      if (testData.getKidneyDisease().equals("Y")) {
        rbtnKidneyDiseaseYes.setChecked(true);
      } else if (testData.getKidneyDisease().equals("N")) {
        rbtnKidneyDiseaseNo.setChecked(true);
      }
    }

    if (testData.getLungDisorder() != null) {
      if (testData.getLungDisorder().equals("Y")) {
        rbtnLungDisorderYes.setChecked(true);
      } else if (testData.getLungDisorder().equals("N")) {
        rbtnLungDisorderNo.setChecked(true);
      }
    }

    if (testData.getStroke() != null) {
      if (testData.getStroke().equals("Y")) {
        rbtnStrokeYes.setChecked(true);
      } else if (testData.getStroke().equals("N")) {
        rbtnStrokeNo.setChecked(true);
      }
    }

    if (testData.getCompromisedImmune() != null) {
      if (testData.getCompromisedImmune().equals("Y")) {
        rbtnImmuneYes.setChecked(true);
      } else if (testData.getCompromisedImmune().equals("N")) {
        rbtnImmuneNo.setChecked(true);
      }
    }

    if (testData.getTravelInDays() != null) {
      if (testData.getTravelInDays().equals("Y")) {
        rbtnTravelDaysYes.setChecked(true);
      } else if (testData.getTravelInDays().equals("N")) {
        rbtnTravelDaysNo.setChecked(true);
      }
    }

    if (testData.getTravelInHrs() != null) {
      if (testData.getTravelInHrs().equals("Y")) {
        rbtnTravelHrsYes.setChecked(true);
      } else if (testData.getTravelInHrs().equals("N")) {
        rbtnTravelHrsNo.setChecked(true);
      }
    }

    if (testData.getContactCNFCase() != null) {
      if (testData.getContactCNFCase().equals("Y")) {
        rbtnContactCovidYes.setChecked(true);
      } else if (testData.getContactCNFCase().equals("N")) {
        rbtnContactCovidNo.setChecked(true);
      }
    }
  }

  private void onClickSave() {

    if (rbtnDryCoughYes.isChecked()) {
      dryCough = "Y";
    } else {
      dryCough = "N";
    }

    if (rbtnSneezingYes.isChecked()) {
      sneezing = "Y";
    } else {
      sneezing = "N";
    }

    if (rbtnSoreThroatYes.isChecked()) {
      soreThroat = "Y";
    } else {
      soreThroat = "N";
    }

    if (rbtnRunnyNoseYes.isChecked()) {
      runnyNose = "Y";
    } else {
      runnyNose = "N";
    }

    if (rbtnStomachPainYes.isChecked()) {
      stomachPain = "Y";
    } else {
      stomachPain = "N";
    }

    if (rbtnDiarrheaYes.isChecked()) {
      diarrhea = "Y";
    } else {
      diarrhea = "N";
    }

    if (rbtnWeaknessYes.isChecked()) {
      weakness = "Y";
    } else {
      weakness = "N";
    }

    if (rbtnModerateCoughYes.isChecked()) {
      moderateCough = "Y";
    } else {
      moderateCough = "N";
    }

    if (rbtnHighCoughYes.isChecked()) {
      highCough = "Y";
    } else {
      highCough = "N";
    }

    if (rbtnBreathlessYes.isChecked()) {
      breathless = "Y";
    } else {
      breathless = "N";
    }

    if (rbtnDifficultBreatheYes.isChecked()) {
      difficultToBreath = "Y";
    } else {
      difficultToBreath = "N";
    }

    if (rbtnDrowsinessYes.isChecked()) {
      drowsiness = "Y";
    } else {
      drowsiness = "N";
    }

    if (rbtnChestPainYes.isChecked()) {
      chestPain = "Y";
    } else {
      chestPain = "N";
    }

    if (rbtnSevereWeaknessYes.isChecked()) {
      severeWeakness = "Y";
    } else {
      severeWeakness = "N";
    }

    if (rbtnDiabetesYes.isChecked()) {
      diabetes = "Y";
    } else {
      diabetes = "N";
    }

    if (rbtnHighBpYes.isChecked()) {
      highBP = "Y";
    } else {
      highBP = "N";
    }

    if (rbtnHeartDiseaseYes.isChecked()) {
      heartDisease = "Y";
    } else {
      heartDisease = "N";
    }

    if (rbtnKidneyDiseaseYes.isChecked()) {
      kidneyDisease = "Y";
    } else {
      kidneyDisease = "N";
    }

    if (rbtnLungDisorderYes.isChecked()) {
      lungDisorder = "Y";
    } else {
      lungDisorder = "N";
    }

    if (rbtnStrokeYes.isChecked()) {
      stroke = "Y";
    } else {
      stroke = "N";
    }

    if (rbtnImmuneYes.isChecked()) {
      immune = "Y";
    } else {
      immune = "N";
    }

    if (rbtnTravelDaysYes.isChecked()) {
      travelDays = "Y";
    } else {
      travelDays = "N";
    }

    if (rbtnTravelHrsYes.isChecked()) {
      travelHrs = "Y";
    } else {
      travelHrs = "N";
    }

    if (rbtnContactCovidYes.isChecked()) {
      contactWithCovid = "Y";
    } else {
      contactWithCovid = "N";
    }

    updateSymptomRequest.setUserId(user.getUserId());
    updateSymptomRequest.setTemperature(etBodyTemperature.getText().toString().trim());
    updateSymptomRequest.setDryCough(dryCough);
    updateSymptomRequest.setSneezing(sneezing);
    updateSymptomRequest.setSoreThroat(soreThroat);
    updateSymptomRequest.setRunnyNose(runnyNose);
    updateSymptomRequest.setStomachPain(stomachPain);
    updateSymptomRequest.setDiarrhea(diarrhea);
    updateSymptomRequest.setWeakness(weakness);
    updateSymptomRequest.setModerateCough(moderateCough);
    updateSymptomRequest.setFeelingBreathless(breathless);
    updateSymptomRequest.setDifficultBreathless(difficultToBreath);
    updateSymptomRequest.setDrowsiness(drowsiness);
    updateSymptomRequest.setChestPain(chestPain);
    updateSymptomRequest.setSevereWeakness(severeWeakness);
    updateSymptomRequest.setDiabetes(diabetes);
    updateSymptomRequest.setHighBloodPressure(highBP);
    updateSymptomRequest.setHeartDisease(heartDisease);
    updateSymptomRequest.setKidneyDisease(kidneyDisease);
    updateSymptomRequest.setLungDisorder(lungDisorder);
    updateSymptomRequest.setStroke(stroke);
    updateSymptomRequest.setCompromisedImmune(immune);
    updateSymptomRequest.setTravelInDays(travelDays);
    updateSymptomRequest.setTravelInHrs(travelHrs);
    updateSymptomRequest.setContactCNFCase(contactWithCovid);
    updateSymptomRequest.setDateTime(sdf.format(new Date()));
    testPresenter.updateSymptoms(updateSymptomRequest);
  }

  @Override public boolean onSupportNavigateUp() {
    onBackPressed();
    return super.onSupportNavigateUp();
  }

  @OnClick({R.id.btn_save, R.id.btn_next}) public void onViewClicked(View view) {
    switch (view.getId()) {
      case R.id.btn_save:
        onClickSave();
        break;
      case R.id.btn_next:
        generateReportRequest.setUserId(user.getUserId());
        testPresenter.generateReport(generateReportRequest);
        break;
    }
  }

  @Override public void startProgress() {
    showProgress();
  }

  @Override public void onSuccessGetSymptoms(List<Symptoms> symptomsList) {
    dismissProgress();
    if (symptomsList != null && symptomsList.size() > 0) {
      setData(symptomsList.get(0));
    }
  }

  @Override public void onSuccessUpdateSymptoms(SuccessResponse successResponse) {
    dismissProgress();
    btnNext.setBackgroundDrawable(getDrawable(R.drawable.bg_black_corner_round));
    btnNext.setClickable(true);
    btnNext.setEnabled(true);
    ViewDialog.showDialog(this, getString(R.string.label_success), "Test Updated",
        getString(R.string.label_ok),
        this::getSymptoms);
  }

  @Override public void generateReport(SuccessResponse successResponse) {
    dismissProgress();
    ViewDialog.showDialog(this, getString(R.string.label_success),
        "Report Generated. Please find your report at result page", getString(R.string.label_ok),
        () -> startActivity(new Intent(TestActivity.this, ResultActivity.class)));
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
