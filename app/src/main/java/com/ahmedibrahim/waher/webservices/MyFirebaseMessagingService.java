package com.ahmedibrahim.waher.webservices;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.util.Log;

import com.ahmedibrahim.waher.R;
import com.ahmedibrahim.waher.activities.MainActivity;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by cca on 27/12/2018.
 */

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    private static final String TAG = "MyFirebaseMsgService";
    Context context;


    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        // Create and show notification
        this.context = context;

       // remoteMessage.getNotific  ation().getTitle()
        //JSONObject jsonObject = new JSONObject(remoteMessage.getData());
        sendNotification(remoteMessage.getNotification().getBody(),remoteMessage.getNotification().getTitle());

         //   remoteMessage.getNotification().getTitle().toString();
        if (remoteMessage.getData().size() > 0) {
          //  sendNotification(remoteMessage.getNotification().getBody(),remoteMessage.getNotification().getTitle());
           // JSONObject jsonObject = new JSONObject(remoteMessage.getData());
           // Log.i("recive_00",""+remoteMessage.getNotification().getBody()+" "+remoteMessage.getNotification().getTitle().toString());
            /*
            try {
                sendNotification(jsonObject.getString("title"),  "sdd");
                Log.i("recive_0",""+jsonObject.getString("title")+""+jsonObject.getString("description"));
              //  Log.i("title_0",""+jsonObject.getString("title"));
            } catch (JSONException e) {
                e.printStackTrace();
                Log.i("recive_0",""+e);
            }
            */
        }
    }


    private void sendNotification(String messageBody,String title) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 /* Request code */, intent, PendingIntent.FLAG_ONE_SHOT);
        Uri defaultSoundUri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        android.support.v4.app.NotificationCompat.Builder notificationBuilder = new android.support.v4.app.NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.btn_requstes)
                .setContentTitle(title)
                .setContentText(messageBody)
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setContentIntent(pendingIntent);
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0 /* ID of notification */, notificationBuilder.build());
    }
}