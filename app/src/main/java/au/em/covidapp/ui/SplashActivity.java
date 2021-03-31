package au.em.covidapp.ui;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import au.em.covidapp.R;
import au.em.covidapp.ui.login.LoginActivity;
import au.em.covidapp.ui.register.RegisterActivity;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SplashActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_splash);
    ButterKnife.bind(this);
  }

  @OnClick({R.id.btn_goto_login, R.id.btn_goto_register}) public void onViewClicked(View view) {
    switch (view.getId()) {
      case R.id.btn_goto_login:
        startActivity(new Intent(this, LoginActivity.class));
        break;
      case R.id.btn_goto_register:
        startActivity(new Intent(this, RegisterActivity.class));
        break;
    }
  }
}
