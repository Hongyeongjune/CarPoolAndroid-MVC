package project.skuniv.ac.kr.carpooldriver;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import androidx.appcompat.app.AppCompatActivity;
import project.skuniv.ac.kr.carpooldriver.controller.DrivingController;
import project.skuniv.ac.kr.carpooldriver.domain.dto.driving.DrivingSaveDto;

public class SearchActivity extends AppCompatActivity implements OnMapReadyCallback,
        GoogleMap.OnPolylineClickListener, GoogleMap.OnPolygonClickListener {

    private GoogleMap mMap;
    private Marker currentMarker;
    private Double departureLat, departureLng;
    private Double destinationLat, destinationLng;
    private String departureAddress, destinationAddress;
    private String departureName, destinationName;
    private Button searchButton;
    private DrivingController drivingController;
    private DrivingSaveDto drivingSaveDto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        departureLat = getIntent().getDoubleExtra("departureLat", 1);
        departureLng = getIntent().getDoubleExtra("departureLng",1);
        destinationLat = getIntent().getDoubleExtra("destinationLat",1);
        destinationLng = getIntent().getDoubleExtra("destinationLng",1);
        departureAddress = getIntent().getStringExtra("departureAddress");
        destinationAddress = getIntent().getStringExtra("destinationAddress");
        departureName = getIntent().getStringExtra("departureName");
        destinationName = getIntent().getStringExtra("destinationName");
        searchButton = (Button) findViewById(R.id.search_button);
        drivingController = new DrivingController(getApplicationContext());

        // Get the SupportMapFragment and request notification when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map_search);

        mapFragment.getMapAsync(this);


        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                drivingSaveDto = new DrivingSaveDto(departureAddress, destinationAddress ,"2Km", )

                Intent intent = new Intent(getApplication(), StartingActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;

        LatLng currentLatLng1 = new LatLng(departureLat, departureLng);
        LatLng currentLatLng2 = new LatLng(destinationLat, destinationLng);

        MarkerOptions markerOptions1 = new MarkerOptions();
        markerOptions1.position(currentLatLng1);
        markerOptions1.title(departureName);
        markerOptions1.snippet(departureAddress);
        markerOptions1.draggable(true);

        currentMarker = mMap.addMarker(markerOptions1);

        MarkerOptions markerOptions2 = new MarkerOptions();
        markerOptions2.position(currentLatLng2);
        markerOptions2.title(destinationName);
        markerOptions2.snippet(destinationAddress);
        markerOptions2.draggable(true);

        currentMarker = mMap.addMarker(markerOptions2);

        double lat = (departureLat + destinationLat) / 2;
        double lng = (departureLng + destinationLng) / 2;

        Log.d("Lat / Lng : " ,lat + " / " + lng);

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(lat, lng), 12));

        // Add polylines and polygons to the map. This section shows just
        // a single polyline. Read the rest of the tutorial to learn more.
        Polyline polyline = mMap.addPolyline(new PolylineOptions()
                .clickable(true)
                .add(
                        new LatLng(departureLat, departureLng),
                        new LatLng(destinationLat, destinationLng))
                .width(5)
                .color(Color.RED));

        // Set listeners for click events.
        googleMap.setOnPolylineClickListener(this);
        googleMap.setOnPolygonClickListener(this);
    }

    @Override
    public void onPolygonClick(Polygon polygon) {

    }

    @Override
    public void onPolylineClick(Polyline polyline) {

    }
}