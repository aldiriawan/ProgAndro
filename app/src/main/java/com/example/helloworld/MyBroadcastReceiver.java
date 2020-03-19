package com.example.helloworld;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class MyBroadcastReceiver extends BroadcastReceiver {

    private String TAG = MyBroadcastReceiver.class.getSimpleName();

    public void onReceive(Context context, Intent intent){
        Log.i(TAG,"test" + intent.getAction());
        if(intent.getAction().equalsIgnoreCase("My_ACTION")){
            Log.i(TAG, "onReceive: ");
        }
    }
}
