package project.skuniv.ac.kr.carpooldriver.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import project.skuniv.ac.kr.carpooldriver.R;
import project.skuniv.ac.kr.carpooldriver.controller.DrivingController;
import project.skuniv.ac.kr.carpooldriver.domain.listItem.MatchingListItem;

public class MatchingDrivingListAdapter extends BaseAdapter {

    ArrayList<MatchingListItem> matchingListItems = new ArrayList<MatchingListItem>();
    DrivingController drivingController = new DrivingController();
    
    @Override
    public int getCount() {
        return matchingListItems.size();
    }

    @Override
    public Object getItem(int position) {
        return matchingListItems.get(position);
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
            convertView = inflater.inflate(R.layout.matching_item_list, parent, false);
        }

        TextView dno = (TextView) convertView.findViewById(R.id.matching_item_list_number);
        TextView distance = (TextView) convertView.findViewById(R.id.matching_item_list_distance);
        TextView departure = (TextView) convertView.findViewById(R.id.matching_item_list_departure);
        TextView destination = (TextView) convertView.findViewById(R.id.matching_item_list_destination);
        TextView date = (TextView) convertView.findViewById(R.id.matching_item_list_date);
        TextView regDate = (TextView) convertView.findViewById(R.id.matching_item_list_regdate);
        TextView driverCall = (TextView) convertView.findViewById(R.id.matching_item_list_driver_call);
        TextView driverFinish = (TextView) convertView.findViewById(R.id.matching_item_list_driver_finish);
        TextView userFinish = (TextView) convertView.findViewById(R.id.matching_item_list_user_finish);
        
        final MatchingListItem matchingListItem = matchingListItems.get(position);

        dno.setText(matchingListItem.getDno() + "");
        distance.setText(matchingListItem.getDistance());
        departure.setText(matchingListItem.getDeparture());
        destination.setText(matchingListItem.getDestination());
        date.setText(matchingListItem.getDate());
        regDate.setText(matchingListItem.getRegDate() + "");

        if(matchingListItem.isDriverCall())
            driverCall.setText("매칭 O");
        else driverCall.setText("매칭 X");

        Log.d("MatchingFinishCall : " ,  matchingListItem.isUserFinishCall() + "");
        if(matchingListItem.isDriverFinishCall())
            driverFinish.setText("운전자 O");
        else driverFinish.setText("운전자 X");
        if(matchingListItem.isUserFinishCall())
            userFinish.setText("사용자 O");
        else userFinish.setText("사용자 X");
        
        return convertView;
    }

    public void addItem(Long dno, String distance, String departure, String destination, String date,
                        String regDate, boolean driverCall, boolean driverFinish, boolean userFinish, boolean isDriving) {
        MatchingListItem matchingListItem = new MatchingListItem();

        matchingListItem.setDno(dno);
        matchingListItem.setDistance(distance);
        matchingListItem.setDeparture(departure);
        matchingListItem.setDestination(destination);
        matchingListItem.setDate(date);
        matchingListItem.setRegDate(regDate);
        matchingListItem.setDriverCall(driverCall);
        matchingListItem.setDriverFinishCall(driverFinish);
        matchingListItem.setUserFinishCall(userFinish);
        matchingListItem.setDriving(isDriving);

        matchingListItems.add(matchingListItem);
    }

    public void deleteItem() {

        matchingListItems.clear();

    }
}
