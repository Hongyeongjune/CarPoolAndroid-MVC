package project.skuniv.ac.kr.carpooldriver.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import project.skuniv.ac.kr.carpooldriver.R;
import project.skuniv.ac.kr.carpooldriver.controller.DrivingController;
import project.skuniv.ac.kr.carpooldriver.domain.listItem.FinishListItem;

public class FinishDrivingListAdapter extends BaseAdapter {
    
    ArrayList<FinishListItem> finishListItems = new ArrayList<FinishListItem>();
    DrivingController drivingController = new DrivingController();
    
    @Override
    public int getCount() {
        return finishListItems.size();
    }

    @Override
    public Object getItem(int position) {
        return finishListItems.get(position);
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
            convertView = inflater.inflate(R.layout.finish_item_list, parent, false);
        }

        TextView dno = (TextView) convertView.findViewById(R.id.finish_item_list_number);
        TextView distance = (TextView) convertView.findViewById(R.id.finish_item_list_distance);
        TextView departure = (TextView) convertView.findViewById(R.id.finish_item_list_departure);
        TextView destination = (TextView) convertView.findViewById(R.id.finish_item_list_destination);
        TextView date = (TextView) convertView.findViewById(R.id.finish_item_list_date);
        TextView regDate = (TextView) convertView.findViewById(R.id.finish_item_list_regdate);
        
        final FinishListItem finishListItem = finishListItems.get(position);

        dno.setText(finishListItem.getDno() + "");
        distance.setText(finishListItem.getDistance());
        departure.setText(finishListItem.getDeparture());
        destination.setText(finishListItem.getDestination());
        date.setText(finishListItem.getDate());
        regDate.setText(finishListItem.getRegDate() + "");
        
        return convertView;
    }

    public void addItem(Long dno, String distance, String departure, String destination, String date, String regDate) {
        FinishListItem finishListItem = new FinishListItem();

        finishListItem.setDno(dno);
        finishListItem.setDistance(distance);
        finishListItem.setDeparture(departure);
        finishListItem.setDestination(destination);
        finishListItem.setDate(date);
        finishListItem.setRegDate(regDate);

        finishListItems.add(finishListItem);
    }

    public void deleteItem() {

        finishListItems.clear();

    }
}
