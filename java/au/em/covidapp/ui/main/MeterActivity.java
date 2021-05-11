package au.em.covidapp.ui.main;

import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import au.em.covidapp.R;
import au.em.covidapp.ui.base.BaseActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MeterActivity extends BaseActivity {

  @BindView(R.id.toolbar_meter) Toolbar mToolbar;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_meter);
    ButterKnife.bind(this);
    setToolbar();
  }

  private void setToolbar() {
    setSupportActionBar(mToolbar);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    getSupportActionBar().setDisplayShowHomeEnabled(true);
    getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back);
  }

  @Override public boolean onSupportNavigateUp() {
    onBackPressed();
    return super.onSupportNavigateUp();
  }
}
