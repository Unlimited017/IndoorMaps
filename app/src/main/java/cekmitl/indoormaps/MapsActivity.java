package cekmitl.indoormaps;

import android.app.Dialog;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.*;
import com.google.android.gms.maps.model.*;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    GoogleMap mGoogleMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(googleServicesAvailable()){
            Toast.makeText(this, "เข้าใช้งานสำเร็จ", Toast.LENGTH_LONG).show();
            setContentView(R.layout.activity_maps);
            //initMap();
        } else {
            // No google Maps Layout
        }
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.mapFragment);
        mapFragment.getMapAsync(this);
    }

    public boolean googleServicesAvailable() {
        GoogleApiAvailability api = GoogleApiAvailability.getInstance();
        int isAvialable = api.isGooglePlayServicesAvailable(this);
        if(isAvialable == ConnectionResult.SUCCESS){
            return true;
        } else if (api.isUserResolvableError(isAvialable)){
            Dialog dialog = api.getErrorDialog(this, isAvialable, 0);
            dialog.show();
        } else {
            Toast.makeText(this, "กรุณาเชื่อมต่ออินเทอร์เน็ต", Toast.LENGTH_LONG).show();
        }
        return false;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mGoogleMap = googleMap;

        // Edit Start Map
        goToLocationZoom(13.746830, 100.535066, 18);
        mGoogleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        mGoogleMap.setIndoorEnabled(true);
        mGoogleMap.getUiSettings().setIndoorLevelPickerEnabled(true);
        mGoogleMap.getUiSettings().setTiltGesturesEnabled(true);
        mGoogleMap.getUiSettings().setMapToolbarEnabled(true);

        /*
        ไว้ก่อน
        mGoogleMap.addPolyline(new PolylineOptions().geodesic(true)
                .add(new LatLng(13.746830, 100.535066))
        ); */
    }

    private void goToLocationZoom(double lat,double lng, float zoom){
        LatLng ll = new LatLng(lat,lng);
        CameraUpdate update = CameraUpdateFactory.newLatLngZoom(ll, zoom);
        mGoogleMap.moveCamera(update);
    }
}
