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

public class RequestDriverActivity extends AppCompatActivity {

    private SharedPreferences userInformation, login;
    private SharedPreferences.Editor userEditor, loginEditor;
    private String id, name, age, sex, phone;
    private TextView userId, userName, userAge, userSex, userPhone;
    private Button cancel, returnButton, finish;
    private DrivingController drivingController;

    private String driverId;

    private String requestId;
    private Long requestDno;

    private FcmController fcmController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_driver);

        requestId = getIntent().getStringExtra("requestUserId");
        requestDno = getIntent().getLongExtra("requestUserDno", 0L);

        Log.d("Request Id : ", requestId);

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

        cancel = (Button) findViewById(R.id.request_cancel_button);
        returnButton = (Button) findViewById(R.id.request_return_button);
        finish = (Button) findViewById(R.id.request_finish_button);

        userId = (TextView) findViewById(R.id.request_user_id);
        userName = (TextView) findViewById(R.id.request_user_name);
        userAge = (TextView) findViewById(R.id.request_user_age);
        userSex = (TextView) findViewById(R.id.request_user_sex);
        userPhone = (TextView) findViewById(R.id.request_user_phone);
        
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                
                drivingController.updateMatchingCancel(requestDno);
                fcmController.firebaseMessagingSend(new SendDto("",
                        requestId, "Matching cancel", "Confirm Matching List !!!"));
                Toast.makeText(RequestDriverActivity.this, "매칭 취소!", Toast.LENGTH_SHORT).show();

                finish();
            }
        });

        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(getIntent().getIntExtra("finishCheck", 0) == 1) {
                    drivingController.updateFinishByDriver(requestDno);
                    fcmController.firebaseMessagingSend(new SendDto("", requestId, "Driving finish", "Confirm driving finish message from driver!!!"));
                    Toast.makeText(RequestDriverActivity.this, "운행 완료", Toast.LENGTH_SHORT).show();

                    finish();
                }
                else if(getIntent().getIntExtra("finishCheck", 0) == 2) {
                    drivingController.updateFinishByUser(requestDno);

                    fcmController.firebaseMessagingSend(new SendDto("", requestId, "Driving finish", "Confirm driving finish message from user!!!"));
                    Toast.makeText(RequestDriverActivity.this, "운행 완료", Toast.LENGTH_SHORT).show();

                    finish();
                }

            }
        });

        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
