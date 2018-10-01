/*Created By @ Dipendra Pant(2018)*/
package com.example.dipak.doctor;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MyFirebaseMessegingService extends FirebaseMessagingService {
    public MyFirebaseMessegingService() {
    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        sendNotification(remoteMessage.getNotification().getBody());
    }

    private void sendNotification(String message) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        PendingIntent pendingIntent = PendingIntent.getActivity(
                this,
                9, intent, PendingIntent.FLAG_ONE_SHOT);

        Uri defauiltSoundUri = RingtoneManager.getDefaultUri(
                RingtoneManager.TYPE_NOTIFICATION);
//Notification build
        NotificationCompat.Builder notificationBuilder = new
                NotificationCompat.Builder(this);
        notificationBuilder.setSmallIcon(R.drawable.doctoricon);
        notificationBuilder.setContentTitle("Doctor Finder Notification");
        notificationBuilder.setContentText(message);
        notificationBuilder.setAutoCancel(true);
        notificationBuilder.setSound(defauiltSoundUri);
        notificationBuilder.setContentIntent(pendingIntent);

        //Display notofication
        NotificationManager notificationManager = (NotificationManager)
                getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(9, notificationBuilder.build());

    }


}
