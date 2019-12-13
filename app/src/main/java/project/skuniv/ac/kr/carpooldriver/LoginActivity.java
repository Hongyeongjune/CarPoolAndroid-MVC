package project.skuniv.ac.kr.carpooldriver;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import project.skuniv.ac.kr.carpooldriver.controller.UserController;
import project.skuniv.ac.kr.carpooldriver.domain.dto.user.SignInDto;

import static project.skuniv.ac.kr.carpooldriver.IntroActivity.loginCheck;

public class LoginActivity extends AppCompatActivity {

    private EditText id, password;
    private Button login, signUp;
    private UserController userController;
    private SignInDto signInDto;
    private SharedPreferences auto, userInformation;
    private SharedPreferences.Editor editor, loginEditor;
    private CheckBox checkBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        id = (EditText) findViewById(R.id.editTextId);
        password = (EditText) findViewById(R.id.editTextPassword);
        login = (Button) findViewById(R.id.buttonLogin);
        signUp = (Button) findViewById(R.id.buttonSignUp);
        checkBox = (CheckBox) findViewById(R.id.checkbox_auto_login);
        userController = new UserController(getApplicationContext());

        auto = getSharedPreferences("autoSignIn", Activity.MODE_PRIVATE);
        userInformation = getSharedPreferences("loginInformation",Activity.MODE_PRIVATE);
        editor = auto.edit();
        loginEditor = userInformation.edit();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                signInDto = new SignInDto(id.getText().toString(), password.getText().toString());
                userController.singIn(signInDto);

                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if(!loginCheck) {
                            Toast.makeText(LoginActivity.this, "로그인 실패", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        loginEditor.putString("loginId", id.getText().toString());
                        loginEditor.commit();
                        try {
                            if(checkBox.isChecked()) {
                                editor.putString("autoId", id.getText().toString());
                                editor.putString("autoPw", password.getText().toString());
                                editor.commit();
                            }
                            else {
                                editor.putString("autoId", null);
                                editor.putString("autoPw", null);
                                editor.commit();
                            }
                        }catch (Exception e) {
                            Toast.makeText(LoginActivity.this, "로그인 실패", Toast.LENGTH_SHORT).show();
                        }

                        Intent intent = new Intent(getApplicationContext(), StartingActivity.class);
                        startActivity(intent);
                        finish();
                    }
                },1000);
            }
        });

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SignUpActivity.class);
                startActivity(intent);
            }
        });
    }
}
