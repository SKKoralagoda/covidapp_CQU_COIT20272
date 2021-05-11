package au.em.covidapp.ui.map;

import android.os.AsyncTask;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import java.util.HashMap;
import java.util.List;

public class GetNearbyPlaceData extends AsyncTask<Object, String, String> {

  //variable
  private String googlePlacesData;
  private GoogleMap mMap;
  private String url;
  private int DEFAULT_ZOOM = 12;

  @Override
  protected String doInBackground(Object... params) {
    try {
      mMap = (GoogleMap) params[0];
      url = (String) params[1];
      DownloadUrl downloadUrl = new DownloadUrl();
      googlePlacesData = downloadUrl.readUrl(url);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return googlePlacesData;
  }

  @Override
  protected void onPostExecute(String result) {
    try {
      List<HashMap<String, String>> nearbyPlacesList = null;
      DataParser dataParser = new DataParser();
      nearbyPlacesList = dataParser.parse(result);
      ShowNearbyPlaces(nearbyPlacesList);
    } catch (NullPointerException e) {
      e.fillInStackTrace();
    }
  }

  private void ShowNearbyPlaces(List<HashMap<String, String>> nearbyPlacesList) {
    try {
      for (int i = 0; i < nearbyPlacesList.size(); i++) {
        MarkerOptions markerOptions = new MarkerOptions();
        HashMap<String, String> googlePlace = nearbyPlacesList.get(i);
        double lat = Double.parseDouble(googlePlace.get("lat"));
        double lng = Double.parseDouble(googlePlace.get("lng"));
        String placeName = googlePlace.get("place_name");
        String vicinity = googlePlace.get("vicinity");
        LatLng latLng = new LatLng(lat, lng);
        markerOptions.position(latLng);
        markerOptions.title(placeName + " : " + vicinity);
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
        mMap.addMarker(markerOptions);

        ////////////////////////
        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(DEFAULT_ZOOM));
      }
    } catch (NullPointerException e) {
      e.fillInStackTrace();
    }
  }
}
