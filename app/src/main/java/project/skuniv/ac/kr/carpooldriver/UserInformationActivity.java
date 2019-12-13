package project.skuniv.ac.kr.carpooldriver;

import android.app.Activity;
import android.content.SharedPreferences;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class UserInformationActivity extends AppCompatActivity {

    private SharedPreferences userInformation;
    private SharedPreferences.Editor loginEditor;
    private String id, name, age, sex, phone;
    private TextView userId, userName, userAge, userSex, userPhone;

    private Button update, cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_information);

        userInformation = getSharedPreferences("loginInformation", Activity.MODE_PRIVATE);
        loginEditor = userInformation.edit();

        id = userInformation.getString("loginId", null);
        name = userInformation.getString("loginName", null);
        age = userInformation.getString("loginAge", null);
        sex = userInformation.getString("loginSex", null);
        phone = userInformation.getString("loginPhone", null);

        update = (Button) findViewById(R.id.button_info_update);
        cancel = (Button) findViewById(R.id.button_info_cancel);

        userId = (TextView) findViewById(R.id.textView_info_id);
        userName = (TextView) findViewById(R.id.textView_info_name);
        userAge = (TextView) findViewById(R.id.textView_info_age);
        userSex = (TextView) findViewById(R.id.textView_info_sex);
        userPhone = (TextView) findViewById(R.id.textView_info_phone);

        userId.setText(id);
        userName.setText(name);
        userAge.setText(age);
        userSex.setText(sex);
        userPhone.setText(phone);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}
