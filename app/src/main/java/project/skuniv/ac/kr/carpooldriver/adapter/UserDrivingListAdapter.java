package project.skuniv.ac.kr.carpooldriver.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

import project.skuniv.ac.kr.carpooldriver.R;
import project.skuniv.ac.kr.carpooldriver.controller.DrivingController;
import project.skuniv.ac.kr.carpooldriver.domain.listItem.UserListItem;

public class UserDrivingListAdapter extends BaseAdapter {

    ArrayList<UserListItem> userListItems = new ArrayList<UserListItem>();
    DrivingController drivingController = new DrivingController();
    private SharedPreferences drivingList, userInformation;
    private SharedPreferences.Editor drivingEditor, loginEditor;

    public UserDrivingListAdapter() {

    }

    @Override
    public int getCount() {
        return userListItems.size();
    }

    @Override
    public Object getItem(int position) {
        return userListItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        final int pos = position;
        final Context context = parent.getContext();

        if(convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.user_item_list, parent, false);
        }

        TextView dno = (TextView) convertView.findViewById(R.id.user_item_list_number);
        TextView distance = (TextView) convertView.findViewById(R.id.user_item_list_distance);
        TextView departure = (TextView) convertView.findViewById(R.id.user_item_list_departure);
        TextView destination = (TextView) convertView.findViewById(R.id.user_item_list_destination);
        TextView date = (TextView) convertView.findViewById(R.id.user_item_list_date);
        TextView regDate = (TextView) convertView.findViewById(R.id.user_item_list_regdate);
        Button delete = (Button) convertView.findViewById(R.id.user_item_list_delete);

        final UserListItem userListItem = userListItems.get(position);

        dno.setText(userListItem.getDno() + "");
        distance.setText(userListItem.getDistance());
        departure.setText(userListItem.getDeparture());
        destination.setText(userListItem.getDestination());
        date.setText(userListItem.getDate());
        regDate.setText(userListItem.getRegDate() + "");

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                drivingList = context.getSharedPreferences("drivingListByUserId",Activity.MODE_PRIVATE);
                drivingEditor = drivingList.edit();

                userInformation = context.getSharedPreferences("loginInformation", Activity.MODE_PRIVATE);
                loginEditor = userInformation.edit();


                drivingController.removeDrivingList(userInformation.getString("loginId",null),
                        drivingList.getLong("drivingListDno" + pos, 0L));

                if( pos >= 0) {

                    Log.d("list item : " , userListItems.get(pos).getDeparture() + " / " + pos);

                    userListItems.remove(getItem(pos));

                    notifyDataSetChanged();
                }

            }
        });

        return convertView;

    }

    public void addItem(Long dno, String distance, String departure, String destination, String date, String regDate) {
        UserListItem userListItem = new UserListItem();

        userListItem.setDno(dno);
        userListItem.setDistance(distance);
        userListItem.setDeparture(departure);
        userListItem.setDestination(destination);
        userListItem.setDate(date);
        userListItem.setRegDate(regDate);

        userListItems.add(userListItem);
    }

}
