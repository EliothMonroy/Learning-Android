package com.example.platzigram;

import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MyFirebaseMessagingService extends FirebaseMessagingService {
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        if(remoteMessage.getNotification().getBody()!=null){
            Log.w("FIREBASE-Notification", remoteMessage.getNotification().getBody());
        }else{
            Log.w("FIREBASE-Notification", "Notificación recibida de firebase");
        }
    }
    
    @Override
    public void onNewToken(String token) {
        Log.d("FirebaseMessagingSer", "Refreshed token: " + token);
    }
    
}
