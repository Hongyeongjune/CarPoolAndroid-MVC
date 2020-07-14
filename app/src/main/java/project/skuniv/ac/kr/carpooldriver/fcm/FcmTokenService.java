package project.skuniv.ac.kr.carpooldriver.fcm;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;

public class FcmTokenService {

    public static String getFcmToken() {
        Log.d("디바이스 토큰 : ", FirebaseInstanceId.getInstance().getToken() + "");
        return FirebaseInstanceId.getInstance().getToken();
    }
}
