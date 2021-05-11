package au.em.covidapp.ui.base;

import android.app.Dialog;
import android.os.Bundle;
import android.view.Window;
import androidx.annotation.Nullable;
import androidx.collection.LongSparseArray;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.Toast;
import au.em.covidapp.BaseApplication;
import au.em.covidapp.R;
import au.em.covidapp.data.local.PreferenceHelper;
import au.em.covidapp.injection.component.ActivityComponent;
import au.em.covidapp.injection.component.ConfigPersistentComponent;
import au.em.covidapp.injection.component.DaggerConfigPersistentComponent;
import au.em.covidapp.injection.module.ActivityModule;
import au.em.covidapp.util.ViewDialog;
import java.util.concurrent.atomic.AtomicLong;

public class BaseActivity extends AppCompatActivity {

 //declaration of used variables
  private static final String KEY_ACTIVITY_ID = "KEY_ACTIVITY_ID";
  private static final AtomicLong NEXT_ID = new AtomicLong(0);
  private static final LongSparseArray<ConfigPersistentComponent>
      sComponentsMap = new LongSparseArray<>();
  public String accessToken;
  private ActivityComponent mActivityComponent;
  private long mActivityId;
  private Dialog mProgress;

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    mActivityId = savedInstanceState != null ?
        savedInstanceState.getLong(KEY_ACTIVITY_ID) : NEXT_ID.getAndIncrement();

    ConfigPersistentComponent configPersistentComponent = sComponentsMap.get(mActivityId, null);

    if (configPersistentComponent == null) {
      configPersistentComponent = DaggerConfigPersistentComponent.builder()
          .applicationComponent(BaseApplication.get(this).getComponent())
          .build();
      sComponentsMap.put(mActivityId, configPersistentComponent);
    }
    mActivityComponent = configPersistentComponent.activityComponent(new ActivityModule(this));
    accessToken = "Bearer " + PreferenceHelper.getAccessToken(this);
  }

  //Some common functions to be used in almost all activities
  @Override
  protected void onSaveInstanceState(Bundle outState) {
    super.onSaveInstanceState(outState);
    outState.putLong(KEY_ACTIVITY_ID, mActivityId);
  }

  @Override
  protected void onDestroy() {
    if (!isChangingConfigurations()) {
      sComponentsMap.remove(mActivityId);
    }
    super.onDestroy();
  }

  public ActivityComponent activityComponent() {
    return mActivityComponent;
  }

  public void showToast(String message) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
  }

  public void showProgress() {

    try {
      if (mProgress == null) {
        mProgress = new Dialog(this, R.style.ProgressbarStyle);
        mProgress.requestWindowFeature(Window.FEATURE_NO_TITLE);
        mProgress.setContentView(R.layout.content_page_loader);
        mProgress.setCancelable(false);
      }

      if (!mProgress.isShowing()) {
        mProgress.show();
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void dismissProgress() {
    if (mProgress != null && mProgress.isShowing()) {
      mProgress.dismiss();
      mProgress = null;
    }
  }

  public void showError(String message) {
    ViewDialog.showDialog(this, getString(R.string.error_error), message,
        getString(R.string.label_ok));
  }
}
