package project.skuniv.ac.kr.carpooldriver;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.widget.Toast;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import project.skuniv.ac.kr.carpooldriver.controller.UserController;
import project.skuniv.ac.kr.carpooldriver.domain.dto.user.SignInDto;

public class IntroActivity extends AppCompatActivity {

    private SharedPreferences auto;
    private SharedPreferences.Editor editor;

    private SharedPreferences userInformation;
    private SharedPreferences.Editor loginEditor;

    private String autoId;
    private String autoPw;
    private SignInDto signInDto;
    private UserController userController;
    public static Boolean loginCheck;

    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        try {
            PackageInfo info = getPackageManager().getPackageInfo("project.skuniv.ac.kr.carpooluser", PackageManager.GET_SIGNATURES);
            for(Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.e("KeyHash", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        auto = getSharedPreferences("autoSignIn", Activity.MODE_PRIVATE);
        userInformation = getSharedPreferences("loginInformation", Activity.MODE_PRIVATE);

        editor = auto.edit();
        loginEditor = userInformation.edit();
        if(autoId == null) {

            autoId = new String("");
        }
        autoId = auto.getString("autoId", null);
        autoPw = auto.getString("autoPw", null);
        userController = new UserController(getApplicationContext());
        handler = new Handler();

        if (autoId != null) {
            signInDto = new SignInDto(autoId.toString(), autoPw.toString());

            userController.singIn(signInDto);

            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    if(loginCheck) {
                        loginEditor.putString("loginId", autoId);
                        loginEditor.commit();
                        Intent intent = new Intent(getApplicationContext(), StartingActivity.class);
                        startActivity(intent); //다음화면으로 넘어감
                        finish();
                    }
                    else {
                        Toast.makeText(IntroActivity.this, "자동로그인 실패", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }
            }, 3000);

        } else {
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {

                    Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(intent); //다음화면으로 넘어감
                    finish();
                }
            }, 3000);

        }
    }
}
