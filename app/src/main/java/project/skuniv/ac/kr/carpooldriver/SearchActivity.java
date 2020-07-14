package project.skuniv.ac.kr.carpooldriver;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

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
import com.skt.Tmap.TMapData;
import com.skt.Tmap.TMapPoint;
import com.skt.Tmap.TMapPolyLine;
import com.skt.Tmap.TMapView;

import org.xml.sax.SAXException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;

import javax.xml.parsers.ParserConfigurationException;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import project.skuniv.ac.kr.carpooldriver.controller.DrivingController;
import project.skuniv.ac.kr.carpooldriver.domain.dto.driving.DrivingSaveDto;
import project.skuniv.ac.kr.carpooldriver.tmap.DirectionConverter;

import static project.skuniv.ac.kr.carpooldriver.IntroActivity.loginCheck;

public class SearchActivity extends AppCompatActivity implements OnMapReadyCallback,
        GoogleMap.OnPolylineClickListener, GoogleMap.OnPolygonClickListener {

    /**
     * T-map Api key : 2dfa2293-c24d-498d-ba81-f8271a4f5cd7
     */

    private Long startTime;
    private Long finishTime;
    private ArrayList<LatLng> latLngArrayList;
    private TMapPolyLine tMapPolyLine;
    private GoogleMap mMap;
    private Marker currentMarker;
    private Double departureLat, departureLng;
    private Double destinationLat, destinationLng;
    private String departureAddress, destinationAddress;
    private String departureName, destinationName;
    private Button searchButton;
    private DrivingController drivingController;
    private DrivingSaveDto drivingSaveDto;
    private Float distance;
    private DatePicker datePicker;
    private TimePicker timePicker;
    private Calendar calendar;

    private String date, time;

    private Context context;

    private SharedPreferences userId, auto;
    private SharedPreferences.Editor loginEditor, editor;
    private String loginId;

    private TMapView tMapView;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        context = getApplicationContext();

        tMapView = new TMapView(getApplicationContext());
        tMapView.setSKTMapApiKey("2dfa2293-c24d-498d-ba81-f8271a4f5cd7");
        tMapView.setLanguage(TMapView.LANGUAGE_KOREAN);

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

        datePicker = (DatePicker) findViewById(R.id.date_picker_search);
        timePicker = (TimePicker) findViewById(R.id.time_picker_search);

        calendar = Calendar.getInstance();

        int hour = calendar.get(calendar.HOUR_OF_DAY);
        int minute = calendar.get(calendar.MINUTE);
        int year = calendar.get(calendar.YEAR);
        int month = calendar.get(calendar.MONTH);
        int day = calendar.get(calendar.DAY_OF_MONTH);

        Log.d("Calendar : " ,hour + " / " + minute + " / " + year + " / " + month + " / " + day);

        date = year + "년 " + month + "월 " + day + "일 ";
        time = hour + "시 " + minute + "분";

        datePicker.setOnDateChangedListener(new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                date = year + "년 " + monthOfYear + "월 " + dayOfMonth + "일 ";
            }
        });

        timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                time = hourOfDay + "시 " + minute + "분";
            }
        });

        userId = getSharedPreferences("loginInformation", Activity.MODE_PRIVATE);
        loginEditor = userId.edit();

        loginId = userId.getString("loginId", null);

        // Get the SupportMapFragment and request notification when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map_search);

        mapFragment.getMapAsync(this);


        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                drivingSaveDto = new DrivingSaveDto(departureAddress, destinationAddress , distance + "Km", date + time, loginId);

                drivingController.saveDriving(drivingSaveDto);

                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        if(loginCheck) {
                            Toast.makeText(SearchActivity.this, "성공적으로 등록 되었습니다.", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplication(), StartingActivity.class);
                            startActivity(intent);
                            finish();
                        }
                        else {
                            Toast.makeText(SearchActivity.this, "등록 실패", Toast.LENGTH_SHORT).show();
                        }


                    }
                },2000);
            }
        });
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;

        final LatLng currentLatLng1 = new LatLng(departureLat, departureLng);
        final LatLng currentLatLng2 = new LatLng(destinationLat, destinationLng);

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

        // Add polylines and polygons to the map. This section shows just
        // a single polyline. Read the rest of the tutorial to learn more.
//        Polyline polyline = mMap.addPolyline(new PolylineOptions()
//                .clickable(true)
//                .add(
//                        new LatLng(departureLat, departureLng),
//                        new LatLng(destinationLat, destinationLng))
//                .width(5)
//                .color(Color.RED));

        Location departureLocation = new Location("departure");
        departureLocation.setLatitude(departureLat);
        departureLocation.setLongitude(departureLng);

        Location destinationLocation = new Location("destination");
        destinationLocation.setLatitude(destinationLat);
        destinationLocation.setLongitude(destinationLng);

        distance = departureLocation.distanceTo(destinationLocation);

        distance = distance / 1000;

        distance = (Math.round((distance * 100))) / 100.0F;

        if(0 < distance && distance < 5) {
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(lat, lng), 13));
        }
        else if(5 <= distance && distance < 10) {
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(lat, lng), 12));
        }
        else if(10 <= distance && distance < 20) {
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(lat, lng), 11));
        }
        else if(20 <= distance && distance <= 40) {
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(lat, lng), 10));
        }
        else if(40 <= distance && distance <= 80) {
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(lat, lng), 9));
        }
        else if(80 <= distance && distance < 160) {
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(lat, lng), 8));
        }
        else if(160 <= distance && distance < 320) {
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(lat, lng), 7));
        }
        else if(320 <= distance) {
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(lat, lng), 6));
        }

        Thread thread = new Thread() {
            public void run() {

                startTime = System.currentTimeMillis();

                TMapPoint startPoint = convertLatLngToMapPoint(currentLatLng1);
                TMapPoint finishPoint = convertLatLngToMapPoint(currentLatLng2);

                TMapData tMapData = new TMapData();

                Log.d("Point : " ,startPoint + "");
                Log.d("Point : " ,finishPoint + "");

                try {

                    tMapPolyLine = tMapData.findPathDataWithType(TMapData.TMapPathType.CAR_PATH, startPoint, finishPoint);

                    Log.d("PolyLine : ", tMapPolyLine.getLinePoint() + "");

                    latLngArrayList = convertTMapPointsToLatLng(tMapPolyLine.getLinePoint());

                    Log.d("ArrayList : ", latLngArrayList + "");

                } catch (Exception e) {
                    e.printStackTrace();
                }

                finishTime = startTime = System.currentTimeMillis();

            }
        };

        thread.start();

        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        mMap.addPolyline(DirectionConverter.createPolyline(context, latLngArrayList, 5, Color.GREEN));




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

    private TMapPoint convertLatLngToMapPoint(LatLng latLng) {
        return new TMapPoint(latLng.latitude, latLng.longitude);
    }

    private ArrayList<LatLng> convertTMapPointsToLatLng(ArrayList<TMapPoint> tMapPoints) {
        ArrayList<LatLng> latLngs = new ArrayList<>(tMapPoints.size());
        for (TMapPoint tMapPoint : tMapPoints) {
            latLngs.add(new LatLng(tMapPoint.getLatitude(), tMapPoint.getLongitude()));
        }
        return latLngs;
    }

}