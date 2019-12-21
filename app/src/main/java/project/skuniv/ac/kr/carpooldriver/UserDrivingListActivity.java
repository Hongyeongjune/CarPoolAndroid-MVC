package project.skuniv.ac.kr.carpooldriver;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import project.skuniv.ac.kr.carpooldriver.adapter.UserDrivingListAdapter;
import project.skuniv.ac.kr.carpooldriver.controller.DrivingController;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.time.LocalDate;

public class UserDrivingListActivity extends AppCompatActivity {

    public static Boolean changeCheck = false;

    private UserDrivingListAdapter userDrivingListAdapter;
    private ListView listView;
    private DrivingController drivingController;
    private SharedPreferences userInformation, drivingList;
    private SharedPreferences.Editor loginEditor, drivingEditor;
    private String loginId;
    private Button listReturn;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_driving_list);

        listView = (ListView) findViewById(R.id.user_driving_list_view);
        userDrivingListAdapter = new UserDrivingListAdapter();
        listView.setAdapter(userDrivingListAdapter);
        drivingController = new DrivingController(getApplicationContext());

        listReturn = (Button) findViewById(R.id.driving_list_return_button);

        userInformation = getSharedPreferences("loginInformation", Activity.MODE_PRIVATE);
        loginEditor = userInformation.edit();

        loginId = userInformation.getString("loginId", null);

        Log.d("Login Id : ", loginId);

        if(getIntent().getIntExtra("startList",0) == 1) {
            drivingController.getDrivingByUserId(loginId);

            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {

                    drivingList = getSharedPreferences("drivingListByUserId", Activity.MODE_PRIVATE);
                    drivingEditor = drivingList.edit();

                    Log.d("DrivingList : ", drivingList.getString("drivingListDeparture" + 0, null));

                    int size = drivingList.getInt("drivingListSize", 0);

                    for (int i = 0; i < size; i++) {
                        userDrivingListAdapter.addItem(drivingList.getLong("drivingListDno" + i, 0L),
                                drivingList.getString("drivingListDistance" + i, null),
                                drivingList.getString("drivingListDeparture" + i, null),
                                drivingList.getString("drivingListDestination" + i, null),
                                drivingList.getString("drivingListDate" + i, null),
                                drivingList.getString("drivingListRegDate" + i, null));
                    }

                    if(size == 0) {
                        userDrivingListAdapter.deleteItem();

                        Toast.makeText(UserDrivingListActivity.this, "요청 내역이 없습니다.", Toast.LENGTH_SHORT).show();
                    }



                    userDrivingListAdapter.notifyDataSetChanged();

                }
            }, 3000);
        }
        else if(getIntent().getIntExtra("finishList", 0) == 2) {
            drivingController.getDrivingByUserIdAndFinish(loginId);

            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {

                    drivingList = getSharedPreferences("drivingListByUserIdAndFinish", Activity.MODE_PRIVATE);
                    drivingEditor = drivingList.edit();

                    Log.d("DrivingList : ", drivingList.getString("drivingFinishListDeparture" + 0, null));

                    int size = drivingList.getInt("drivingFinishListSize", 0);

                    for (int i = 0; i < size; i++) {
                        userDrivingListAdapter.addItem(drivingList.getLong("drivingFinishListDno" + i, 0L),
                                drivingList.getString("drivingFinishListDistance" + i, null),
                                drivingList.getString("drivingFinishListDeparture" + i, null),
                                drivingList.getString("drivingFinishListDestination" + i, null),
                                drivingList.getString("drivingFinishListDate" + i, null),
                                drivingList.getString("drivingFinishListRegDate" + i, null));
                    }

                    if(size == 0) {
                        userDrivingListAdapter.deleteItem();

                        Toast.makeText(UserDrivingListActivity.this, "요청 내역이 없습니다.", Toast.LENGTH_SHORT).show();
                    }

                    userDrivingListAdapter.notifyDataSetChanged();

                }
            }, 3000);

        }


        listReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}
