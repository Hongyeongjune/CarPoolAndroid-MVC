package project.skuniv.ac.kr.carpooldriver;

import androidx.appcompat.app.AppCompatActivity;
import project.skuniv.ac.kr.carpooldriver.adapter.MatchingDrivingListAdapter;
import project.skuniv.ac.kr.carpooldriver.controller.DrivingController;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class RequestDrivingActivity extends AppCompatActivity {

    private MatchingDrivingListAdapter matchingDrivingListAdapter;
    private ListView listView;
    private DrivingController drivingController;
    private SharedPreferences userInformation, matchingList;
    private SharedPreferences.Editor loginEditor, drivingEditor;
    private String loginId;
    private Button listReturn;
    private int matchingCheck = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_driving);

        listView = (ListView) findViewById(R.id.request_driving_list_view);
        matchingDrivingListAdapter = new MatchingDrivingListAdapter();
        listView.setAdapter(matchingDrivingListAdapter);
        drivingController = new DrivingController(getApplicationContext());

        listReturn = (Button) findViewById(R.id.driving_list_return_button);

        userInformation = getSharedPreferences("loginInformation", Activity.MODE_PRIVATE);
        loginEditor = userInformation.edit();

        loginId = userInformation.getString("loginId", null);

        listReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if(getIntent().getIntExtra("matchingCheck", 0) == 1) {
                    Intent intent = new Intent(getApplicationContext(), RequestDriverActivity.class);
                    intent.putExtra("requestUserDno", matchingList.getLong("matchingListDno" + position, 0L));
                    intent.putExtra("requestUserId", matchingList.getString("matchingListUserId" + position, null));
                    intent.putExtra("finishCheck", 1);
                    startActivity(intent);
                }
                else if(getIntent().getIntExtra("matchingCheck", 0) == 2) {
                    Intent intent = new Intent(getApplicationContext(), RequestDriverActivity.class);
                    intent.putExtra("requestUserDno", matchingList.getLong("matchingListDno" + position, 0L));
                    intent.putExtra("requestUserId", matchingList.getString("matchingListUserId" + position, null));
                    intent.putExtra("finishCheck", 2);
                    startActivity(intent);
                }

            }
        });

        Log.d("MatchingLogin Id : ", loginId);

        if (getIntent().getIntExtra("matchingCheck", 0) == 1) {

            drivingController.getMatchingList(loginId);

            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {

                    matchingList = getSharedPreferences("matchingList", Activity.MODE_PRIVATE);
                    drivingEditor = matchingList.edit();

                    //            Log.d("DrivingList : ", matchingList.getString("matchingListDeparture" + 0, null));

                    int size = matchingList.getInt("matchingListSize", 0);

                    Log.d("MatchingSize : " , size + "");
                    for (int i = 0; i < size; i++) {
                        matchingDrivingListAdapter.addItem(matchingList.getLong("matchingListDno" + i, 0L),
                                matchingList.getString("matchingListDistance" + i, null),
                                matchingList.getString("matchingListDeparture" + i, null),
                                matchingList.getString("matchingListDestination" + i, null),
                                matchingList.getString("matchingListDate" + i, null),
                                matchingList.getString("matchingListRegDate" + i, null),
                                matchingList.getBoolean("matchingListDriverCall" + i, false),
                                matchingList.getBoolean("matchingListDriverFinish" + i, false),
                                matchingList.getBoolean("matchingListUserFinish" + i, false),
                                matchingList.getBoolean("matchingListIsDriving" + i, false));
                    }

                    if(size == 0) {
                        matchingDrivingListAdapter.deleteItem();

                        Toast.makeText(RequestDrivingActivity.this, "요청 내역이 없습니다.", Toast.LENGTH_SHORT).show();
                    }


                    matchingDrivingListAdapter.notifyDataSetChanged();

                }
            }, 3000);
        } else if(getIntent().getIntExtra("matchingCheck", 0) == 2) {

            drivingController.getMatchingListByUserId(loginId);

            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {

                    matchingList = getSharedPreferences("matchingList", Activity.MODE_PRIVATE);
                    drivingEditor = matchingList.edit();

                    //            Log.d("DrivingList : ", matchingList.getString("matchingListDeparture" + 0, null));

                    int size = matchingList.getInt("matchingListSize", 0);

                    Log.d("MatchingSizeUserId : " , size + "");

                    for (int i = 0; i < size; i++) {
                        matchingDrivingListAdapter.addItem(matchingList.getLong("matchingListDno" + i, 0L),
                                matchingList.getString("matchingListDistance" + i, null),
                                matchingList.getString("matchingListDeparture" + i, null),
                                matchingList.getString("matchingListDestination" + i, null),
                                matchingList.getString("matchingListDate" + i, null),
                                matchingList.getString("matchingListRegDate" + i, null),
                                matchingList.getBoolean("matchingListDriverCall" + i, false),
                                matchingList.getBoolean("matchingListDriverFinish" + i, false),
                                matchingList.getBoolean("matchingListUserFinish" + i, false),
                                matchingList.getBoolean("matchingListIsDriving" + i, false));
                    }

                    if(size == 0) {
                        matchingDrivingListAdapter.deleteItem();

                        Toast.makeText(RequestDrivingActivity.this, "요청 내역이 없습니다.", Toast.LENGTH_SHORT).show();
                    }

                    matchingDrivingListAdapter.notifyDataSetChanged();

                }
            }, 3000);
        }
    }
}
