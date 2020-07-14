package project.skuniv.ac.kr.carpooldriver;

import android.content.Intent;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import project.skuniv.ac.kr.carpooldriver.controller.UserController;
import project.skuniv.ac.kr.carpooldriver.domain.dto.user.CheckUserIdDto;
import project.skuniv.ac.kr.carpooldriver.domain.dto.user.SignUpDto;
import project.skuniv.ac.kr.carpooldriver.fcm.FcmTokenService;

import static project.skuniv.ac.kr.carpooldriver.IntroActivity.loginCheck;

public class SignUpActivity extends AppCompatActivity {

    private EditText id, password, name, age, sex, phone;
    private Button join, cancel, check;
    private SignUpDto signupDto;
    private UserController userController;

    private Boolean idCheck = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        id = (EditText) findViewById(R.id.editTextSignId);
        password = (EditText) findViewById(R.id.editTextSignPassword);
        name = (EditText) findViewById(R.id.editTextSignName);
        age = (EditText) findViewById(R.id.editTextSignAge);
        sex = (EditText) findViewById(R.id.editTextSignSex);
        phone = (EditText) findViewById(R.id.editTextSignPhone);
        join = (Button) findViewById(R.id.buttonJoin);
        cancel = (Button) findViewById(R.id.buttonCancel);
        check = (Button) findViewById(R.id.buttonIdCheck);
        userController = new UserController(getApplicationContext());

        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userController.checkUserId(new CheckUserIdDto(id.getText().toString()));

                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if(loginCheck) {
                            Toast.makeText(SignUpActivity.this, "사용 가능", Toast.LENGTH_SHORT).show();
                            idCheck = true;
                        }
                        else {
                            Toast.makeText(SignUpActivity.this, "중복된 아이디", Toast.LENGTH_SHORT).show();
                            idCheck = false;
                        }
                    }
                }, 2000);
            }
        });

        join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(idCheck && !password.getText().toString().equals("")) {

                    signupDto = new SignUpDto(id.getText().toString(), password.getText().toString(), name.getText().toString(),
                            age.getText().toString(), sex.getText().toString(), phone.getText().toString(), FcmTokenService.getFcmToken());

                    userController.signUp(signupDto);
                    Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(intent);
                    finish();
                }
                else {
                    Toast.makeText(SignUpActivity.this, "중복확인을 하거나 값을 다 채우세요", Toast.LENGTH_SHORT).show();
                }
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

