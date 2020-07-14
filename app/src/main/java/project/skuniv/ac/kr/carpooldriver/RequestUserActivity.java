package project.skuniv.ac.kr.carpooldriver;

import androidx.appcompat.app.AppCompatActivity;
import project.skuniv.ac.kr.carpooldriver.controller.DrivingController;
import project.skuniv.ac.kr.carpooldriver.controller.FcmController;
import project.skuniv.ac.kr.carpooldriver.domain.dto.fcm.SendDto;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class RequestUserActivity extends AppCompatActivity {

    private SharedPreferences userInformation, login;
    private SharedPreferences.Editor userEditor, loginEditor;
    private String id, name, age, sex, phone;
    private TextView userId, userName, userAge, userSex, userPhone;
    private Button request, cancel;
    private DrivingController drivingController;
    private FcmController fcmController;

    private String driverId;

    private String requestId;
    private Long requestDno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_user);

        requestId = getIntent().getStringExtra("requestUserId");
        requestDno = getIntent().getLongExtra("requestUserDno", 0L);

        drivingController = new DrivingController(getApplicationContext());
        fcmController = new FcmController(getApplicationContext());

        drivingController.requestGetUserInformation(requestId);

        login = getSharedPreferences("loginInformation", Activity.MODE_PRIVATE);
        loginEditor = login.edit();

        driverId = login.getString("loginId", null);

        Log.d("Login Id : ", driverId);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                userInformation = getSharedPreferences("requestUserInformation", Activity.MODE_PRIVATE);
                userEditor = userInformation.edit();

                id = userInformation.getString("requestId", null);
                name = userInformation.getString("requestName", null);
                age = userInformation.getString("requestAge", null);
                sex = userInformation.getString("requestSex", null);
                phone = userInformation.getString("requestPhone", null);

                userId.setText(id);
                userName.setText(name);
                userAge.setText(age);
                userSex.setText(sex);
                userPhone.setText(phone);

            }
        },2000);

        request = (Button) findViewById(R.id.request_start_button);
        cancel = (Button) findViewById(R.id.request_cancel_button);

        userId = (TextView) findViewById(R.id.request_user_id);
        userName = (TextView) findViewById(R.id.request_user_name);
        userAge = (TextView) findViewById(R.id.request_user_age);
        userSex = (TextView) findViewById(R.id.request_user_sex);
        userPhone = (TextView) findViewById(R.id.request_user_phone);

        request.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                drivingController.updateDrivingRequest(requestId, requestDno);
                drivingController.updateMatchingDriver(requestId, requestDno, driverId);
                fcmController.firebaseMessagingSend(new SendDto("",requestId, "Matching Request", "confirm matching!!!"));

                Toast.makeText(RequestUserActivity.this, "신청 성공", Toast.LENGTH_SHORT).show();

                finish();
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
