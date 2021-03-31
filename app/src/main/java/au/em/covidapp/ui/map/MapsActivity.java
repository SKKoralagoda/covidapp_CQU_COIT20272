package au.em.covidapp.ui.map;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import au.em.covidapp.R;
import au.em.covidapp.ui.base.BaseActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import io.reactivex.annotations.NonNull;

public class MapsActivity extends BaseActivity implements OnMapReadyCallback,
    GoogleApiClient.ConnectionCallbacks,
    GoogleApiClient.OnConnectionFailedListener,
    LocationListener {

  @BindView(R.id.toolbar_map) Toolbar mToolbar;
  @BindView(R.id.map) MapView mvResultsMap;

  public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;
  double latitude;
  double longitude;
  GoogleApiClient mGoogleApiClient;
  Location mLastLocation;
  Marker mCurrLocationMarker;
  LocationRequest mLocationRequest;
  private GoogleMap mMap;
  private int PROXIMITY_RADIUS = 10000;
  private int DEFAULT_ZOOM = 12;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_maps);
    ButterKnife.bind(this);
    mvResultsMap.onCreate(savedInstanceState);
    setToolbar();

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
      checkLocationPermission();
    }

    mvResultsMap.getMapAsync(this);
  }

  private void setToolbar() {
    setSupportActionBar(mToolbar);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    getSupportActionBar().setDisplayShowHomeEnabled(true);
    getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back);
    getSupportActionBar().setTitle(R.string.label_consultant);
  }

  @Override public void onStart() {
    super.onStart();
    mvResultsMap.onStart();
  }

  @Override public void onResume() {
    super.onResume();
    mvResultsMap.onResume();
  }

  @Override public void onPause() {
    super.onPause();
    mvResultsMap.onPause();
  }

  @Override public void onStop() {
    super.onStop();
    mvResultsMap.onStop();
  }

  @Override public void onLowMemory() {
    super.onLowMemory();
    mvResultsMap.onLowMemory();
  }

  private boolean CheckGooglePlayServices() {
    GoogleApiAvailability googleAPI = GoogleApiAvailability.getInstance();
    int result = googleAPI.isGooglePlayServicesAvailable(this);
    if (result != ConnectionResult.SUCCESS) {
      if (googleAPI.isUserResolvableError(result)) {
        googleAPI.getErrorDialog(this, result,
            0).show();
      }
      return false;
    }
    return true;
  }

  @Override
  public void onMapReady(GoogleMap googleMap) {
    mMap = googleMap;

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
      if (ContextCompat.checkSelfPermission(this,
          Manifest.permission.ACCESS_FINE_LOCATION)
          == PackageManager.PERMISSION_GRANTED) {
        buildGoogleApiClient();
        mMap.setMyLocationEnabled(true);
      }
    } else {
      buildGoogleApiClient();
      mMap.setMyLocationEnabled(true);
    }
  }

  protected synchronized void buildGoogleApiClient() {
    mGoogleApiClient = new GoogleApiClient.Builder(this)
        .addConnectionCallbacks(this)
        .addOnConnectionFailedListener(this)
        .addApi(LocationServices.API)
        .build();
    mGoogleApiClient.connect();
  }

  @Override
  public void onConnected(Bundle bundle) {
    mLocationRequest = new LocationRequest();
    mLocationRequest.setInterval(1000);
    mLocationRequest.setFastestInterval(1000);
    mLocationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
    if (ContextCompat.checkSelfPermission(this,
        Manifest.permission.ACCESS_FINE_LOCATION)
        == PackageManager.PERMISSION_GRANTED) {
      LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest,
          this);
    }
  }

  private String getUrl(double latitude, double longitude) {

    StringBuilder googlePlacesUrl =
        new StringBuilder("https://maps.googleapis.com/maps/api/place/nearbysearch/json?");
    googlePlacesUrl.append("location=" + latitude + "," + longitude);
    googlePlacesUrl.append("&radius=" + PROXIMITY_RADIUS);
    googlePlacesUrl.append("&keyword=hospital");
    googlePlacesUrl.append("&sensor=true");
    googlePlacesUrl.append("&key=" + "AIzaSyCMd5lxwPonkrbJBj4DE6ZnRHF2Nr_0cDU");
    return (googlePlacesUrl.toString());
  }

  @Override
  public void onConnectionSuspended(int i) {

  }

  @Override
  public void onLocationChanged(Location location) {
    mLastLocation = location;
    if (mCurrLocationMarker != null) {
      mCurrLocationMarker.remove();
    }

    latitude = location.getLatitude();
    longitude = location.getLongitude();
    LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
    MarkerOptions markerOptions = new MarkerOptions();
    markerOptions.position(latLng);
    markerOptions.title("Current Position");
    markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA));
    mCurrLocationMarker = mMap.addMarker(markerOptions);
    mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
    mMap.animateCamera(CameraUpdateFactory.zoomTo(DEFAULT_ZOOM));

    String url = getUrl(latitude, longitude);
    Object[] DataTransfer = new Object[2];
    DataTransfer[0] = mMap;
    DataTransfer[1] = url;
    GetNearbyPlaceData getNearbyPlacesData = new GetNearbyPlaceData();
    getNearbyPlacesData.execute(DataTransfer);

    if (mGoogleApiClient != null) {
      LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, this);
    }
  }

  public boolean checkLocationPermission() {
    if (ContextCompat.checkSelfPermission(this,
        Manifest.permission.ACCESS_FINE_LOCATION)
        != PackageManager.PERMISSION_GRANTED) {

      if (ActivityCompat.shouldShowRequestPermissionRationale(this,
          Manifest.permission.ACCESS_FINE_LOCATION)) {

        ActivityCompat.requestPermissions(this,
            new String[] {Manifest.permission.ACCESS_FINE_LOCATION},
            MY_PERMISSIONS_REQUEST_LOCATION);
      } else {
        ActivityCompat.requestPermissions(this,
            new String[] {Manifest.permission.ACCESS_FINE_LOCATION},
            MY_PERMISSIONS_REQUEST_LOCATION);
      }
      return false;
    } else {
      return true;
    }
  }

  @Override
  public void onRequestPermissionsResult(int requestCode,
      String permissions[], int[] grantResults) {
    if (requestCode == MY_PERMISSIONS_REQUEST_LOCATION) {
      if (grantResults.length > 0
          && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

        if (ContextCompat.checkSelfPermission(this,
            Manifest.permission.ACCESS_FINE_LOCATION)
            == PackageManager.PERMISSION_GRANTED) {

          if (mGoogleApiClient == null) {
            buildGoogleApiClient();
          }
          mMap.setMyLocationEnabled(true);
        }
      }
    }
  }

  @Override public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
    mvResultsMap.setVisibility(View.GONE);
  }

  @Override public boolean onSupportNavigateUp() {
    onBackPressed();
    return super.onSupportNavigateUp();
  }

  @OnClick({R.id.btn_make_appointment, R.id.btn_call}) public void onViewClicked(View view) {
    switch (view.getId()) {
      case R.id.btn_make_appointment:
      case R.id.btn_call:
        Toast.makeText(this, "This feature will be coming soon", Toast.LENGTH_SHORT).show();
        break;
    }
  }
}
