package com.example.helloworld;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;

public class WifiCheck extends Application {

    public static final String onWiFi = "WiFi is Turned On";
    public static final String offWiFi = "WiFi is Turned Off";

    @Override
    public void onCreate() {
        super.onCreate();
        createNotification();
    }

    private void createNotification() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel on = new NotificationChannel(
                    onWiFi,
                    "WiFi is Turned On",
                    NotificationManager.IMPORTANCE_HIGH);//importance high diprioritas tingkat tinggi
            on.setDescription("WiFi is Turned On");

            NotificationChannel off = new NotificationChannel(
                    offWiFi,
                    "WiFi is Turned Off",
                    NotificationManager.IMPORTANCE_LOW);
            off.setDescription("WiFi is Turned Off");

            NotificationManager manager = getSystemService(NotificationManager.class);
            assert manager != null;
            manager.createNotificationChannel(on);
            manager.createNotificationChannel(off);
        }
    }

}
