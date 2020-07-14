package project.skuniv.ac.kr.carpooldriver.fcm;

import android.app.ActivityManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.List;

import androidx.core.app.NotificationCompat;
import project.skuniv.ac.kr.carpooldriver.DriverStartingActivity;
import project.skuniv.ac.kr.carpooldriver.R;
import project.skuniv.ac.kr.carpooldriver.StartingActivity;

public class FireBaseMessagingService extends FirebaseMessagingService {

    private static final String CHANNEL_ID = "Channel ID";
    private final long[] VIBRATE = {500, 1000, 500, 1000};

    /**
     * 구글 토큰 값 얻기
     * @param token
     */
    @Override
    public void onNewToken(String token) {
        super.onNewToken(token);
        Log.d("FireBase : ", "FireBaseInstanceId" + token);
    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        if(remoteMessage.getNotification() != null) {
            notifyBackground(remoteMessage);
        }

    }

    private void notifyBackground(RemoteMessage remoteMessage) {

        Intent intent = new Intent(this, DriverStartingActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT);
        Uri uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        NotificationCompat.Builder notificationCompat = new NotificationCompat.Builder(this, CHANNEL_ID);
        notificationCompat.setContentIntent(pendingIntent);
        notificationCompat.setWhen(System.currentTimeMillis());
        notificationCompat.setSmallIcon(R.drawable.common_google_signin_btn_icon_light);
        notificationCompat.setContentTitle(remoteMessage.getNotification().getTitle());
        notificationCompat.setContentText(remoteMessage.getNotification().getBody());
        notificationCompat.setVibrate(VIBRATE);
        notificationCompat.setSound(uri);

        NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(0, notificationCompat.build());

    }
}
