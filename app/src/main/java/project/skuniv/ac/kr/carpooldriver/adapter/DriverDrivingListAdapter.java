package project.skuniv.ac.kr.carpooldriver.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import project.skuniv.ac.kr.carpooldriver.R;
import project.skuniv.ac.kr.carpooldriver.RequestUserActivity;
import project.skuniv.ac.kr.carpooldriver.controller.DrivingController;
import project.skuniv.ac.kr.carpooldriver.domain.listItem.DriverListItem;

import static project.skuniv.ac.kr.carpooldriver.DriverStartingActivity.userCheck;

public class DriverDrivingListAdapter extends BaseAdapter {

    ArrayList<DriverListItem> driverListItems = new ArrayList<DriverListItem>();
    DrivingController drivingController = new DrivingController();
    private SharedPreferences drivingList, requestUser;
    private SharedPreferences.Editor drivingEditor, requestEditor;
    
    @Override
    public int getCount() {
        return driverListItems.size();
    }

    @Override
    public Object getItem(int position) {
        return driverListItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;
        final Context context = parent.getContext();

        if(convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.driver_item_list, parent, false);
        }

        TextView dno = (TextView) convertView.findViewById(R.id.driver_item_list_number);
        TextView distance = (TextView) convertView.findViewById(R.id.driver_item_list_distance);
        TextView departure = (TextView) convertView.findViewById(R.id.driver_item_list_departure);
        TextView destination = (TextView) convertView.findViewById(R.id.driver_item_list_destination);
        TextView date = (TextView) convertView.findViewById(R.id.driver_item_list_date);
        TextView regDate = (TextView) convertView.findViewById(R.id.driver_item_list_regdate);
        Button check = (Button) convertView.findViewById(R.id.driver_item_list_check);

        final DriverListItem driverListItem = driverListItems.get(position);

        dno.setText(driverListItem.getDno() + "");
        distance.setText(driverListItem.getDistance());
        departure.setText(driverListItem.getDeparture());
        destination.setText(driverListItem.getDestination());
        date.setText(driverListItem.getDate());
        regDate.setText(driverListItem.getRegDate() + "");

        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(userCheck == 1) {

                    drivingList = context.getSharedPreferences("drivingAllList", Activity.MODE_PRIVATE);
                    drivingEditor = drivingList.edit();

                    Log.d("Driving User : ", drivingList.getString("drivingAllListUserId" + pos, null)) ;

                    Intent intent = new Intent(context.getApplicationContext(), RequestUserActivity.class);
                    intent.putExtra("requestUserDno", drivingList.getLong("drivingAllListDno" + pos, 0L));
                    intent.putExtra("requestUserId", drivingList.getString("drivingAllListUserId" + pos,null));
                    context.startActivity(intent);

                }
                else if(userCheck == 2) {

                    drivingList = context.getSharedPreferences("drivingDepartureList", Activity.MODE_PRIVATE);
                    drivingEditor = drivingList.edit();

                    Intent intent = new Intent(context.getApplicationContext(), RequestUserActivity.class);
                    intent.putExtra("requestUserDno", drivingList.getLong("drivingDepartureListDno" + pos, 0L));
                    intent.putExtra("requestUserId", drivingList.getString("drivingDepartureListUserId" + pos,null));
                    context.startActivity(intent);

                }
                else if(userCheck == 3) {

                    drivingList = context.getSharedPreferences("drivingDestinationList", Activity.MODE_PRIVATE);
                    drivingEditor = drivingList.edit();

                    Intent intent = new Intent(context.getApplicationContext(), RequestUserActivity.class);
                    intent.putExtra("requestUserDno", drivingList.getLong("drivingDestinationListDno" + pos, 0L));
                    intent.putExtra("requestUserId", drivingList.getString("drivingDestinationListUserId" + pos,null));
                    context.startActivity(intent);

                }
            }
        });

        return convertView;
    }

    public void addItem(Long dno, String distance, String departure, String destination, String date, String regDate) {
        DriverListItem driverListItem = new DriverListItem();

        driverListItem.setDno(dno);
        driverListItem.setDistance(distance);
        driverListItem.setDeparture(departure);
        driverListItem.setDestination(destination);
        driverListItem.setDate(date);
        driverListItem.setRegDate(regDate);

        driverListItems.add(driverListItem);
    }

    public void deleteItem() {

        driverListItems.clear();

    }

}
