package project.skuniv.ac.kr.carpooldriver;

import androidx.appcompat.app.AppCompatActivity;
import project.skuniv.ac.kr.carpooldriver.adapter.FinishDrivingListAdapter;
import project.skuniv.ac.kr.carpooldriver.controller.DrivingController;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class FinishDrivingActivity extends AppCompatActivity {

    private FinishDrivingListAdapter finishDrivingListAdapter;
    private ListView listView;
    private DrivingController drivingController;
    private SharedPreferences userInformation, finishList;
    private SharedPreferences.Editor loginEditor, drivingEditor;
    private String loginId;
    private Button listReturn;
    private int matchingCheck = 0;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finish_driving);
        
        listView = (ListView) findViewById(R.id.finish_driving_list_view);
        finishDrivingListAdapter = new FinishDrivingListAdapter();
        listView.setAdapter(finishDrivingListAdapter);
        
        drivingController = new DrivingController(getApplicationContext());

        listReturn = (Button) findViewById(R.id.finish_list_return_button);

        userInformation = getSharedPreferences("loginInformation", Activity.MODE_PRIVATE);
        loginEditor = userInformation.edit();

        loginId = userInformation.getString("loginId", null);

        listReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        
        if(getIntent().getIntExtra("finishCheckByGroup", 0) == 1 ) {
            
            drivingController.getFinishByDriver(loginId);

            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {

                    finishList = getSharedPreferences("finishListByDriver", Activity.MODE_PRIVATE);
                    drivingEditor = finishList.edit();

                    

                    int size = finishList.getInt("finishListByDriverSize", 0);


                    for (int i = 0; i < size; i++) {
                        finishDrivingListAdapter.addItem(finishList.getLong("finishListByDriverDno" + i, 0L),
                                finishList.getString("finishListByDriverDistance" + i, null),
                                finishList.getString("finishListByDriverDeparture" + i, null),
                                finishList.getString("finishListByDriverDestination" + i, null),
                                finishList.getString("finishListByDriverDate" + i, null),
                                finishList.getString("finishListByDriverRegDate" + i, null));
                    }

                    if(size == 0) {
                        finishDrivingListAdapter.deleteItem();

                        Toast.makeText(FinishDrivingActivity.this, "요청 내역이 없습니다.", Toast.LENGTH_SHORT).show();
                    }


                    finishDrivingListAdapter.notifyDataSetChanged();
                    
                }
            }, 3000);
        }
        else if(getIntent().getIntExtra("finishCheckByGroup", 0) == 2 ) {

            drivingController.getFinishByUser(loginId);

            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {

                    finishList = getSharedPreferences("finishListByUser", Activity.MODE_PRIVATE);
                    drivingEditor = finishList.edit();

                    int size = finishList.getInt("finishListByUserSize", 0);


                    for (int i = 0; i < size; i++) {
                        finishDrivingListAdapter.addItem(finishList.getLong("finishListByUserDno" + i, 0L),
                                finishList.getString("finishListByUserDistance" + i, null),
                                finishList.getString("finishListByUserDeparture" + i, null),
                                finishList.getString("finishListByUserDestination" + i, null),
                                finishList.getString("finishListByUserDate" + i, null),
                                finishList.getString("finishListByUserRegDate" + i, null));
                    }

                    if(size == 0) {
                        finishDrivingListAdapter.deleteItem();

                        Toast.makeText(FinishDrivingActivity.this, "요청 내역이 없습니다.", Toast.LENGTH_SHORT).show();
                    }


                    finishDrivingListAdapter.notifyDataSetChanged();

                }
            }, 3000);
        }
    }
}
