package com.example.androidproject.controller.Firebase;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;

import com.example.androidproject.R;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class FirebaseMessagingController extends FirebaseMessagingService {
    private static final String TAG = "FCM Service";
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        String title = remoteMessage.getNotification().getTitle();
        String content = remoteMessage.getNotification().getBody();
        showNotification(title, content);
    }

    private void showNotification(String title, String content){
        NotificationManager notif = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        Notification notify = new Notification.Builder(getApplicationContext()).setContentTitle(title).setContentText(content).setSmallIcon(R.drawable.ic_launcher_background).build();
        notify.flags |= Notification.FLAG_AUTO_CANCEL;
        notif.notify(0, notify);
    }
}