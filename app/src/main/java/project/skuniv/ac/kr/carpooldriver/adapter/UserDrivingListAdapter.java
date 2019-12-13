package project.skuniv.ac.kr.carpooldriver.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.time.LocalDate;
import java.util.ArrayList;

import project.skuniv.ac.kr.carpooldriver.R;
import project.skuniv.ac.kr.carpooldriver.domain.listItem.UserListItem;

public class UserDrivingListAdapter extends BaseAdapter {

    ArrayList<UserListItem> userListItems = new ArrayList<UserListItem>();

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
    public View getView(int position, View convertView, ViewGroup parent) {

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

        UserListItem userListItem = userListItems.get(position);

        dno.setText(userListItem.getDate());
        distance.setText(userListItem.getDistance());
        departure.setText(userListItem.getDeparture());
        destination.setText(userListItem.getDestination());
        date.setText(userListItem.getDate());
        regDate.setText(userListItem.getRegDate() + "");
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        return convertView;

    }

    public void addItem(Long dno, String distance, String departure, String destination, String date, LocalDate regDate) {
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
