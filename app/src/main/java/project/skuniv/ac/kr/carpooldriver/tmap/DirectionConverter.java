package project.skuniv.ac.kr.carpooldriver.tmap;

import android.content.Context;
import android.util.DisplayMetrics;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.ArrayList;

public class DirectionConverter {

    public static PolylineOptions createPolyline(Context context, ArrayList<LatLng> locationList, int width, int color) {
        PolylineOptions rectLine = new PolylineOptions().clickable(true).color(color).width(width);

        for(LatLng latLng : locationList) {
            rectLine.add(latLng);
        }

        return rectLine;
    }
}
