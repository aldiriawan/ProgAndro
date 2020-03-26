package com.example.helloworld;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import static android.content.Context.JOB_SCHEDULER_SERVICE;
import static com.example.helloworld.Profile.TAG;


public class Home extends Fragment {
    SharedPreferences sharedPreferences;
    private Button logout;
    private static final String TAG = "Activities";
    private Button btnStartJob;
    private Button btnCancelJob;
    //btnStartJob = findViewById(R.id.startJob);
    //btnCancelJob = findViewById(R.id.cancelJob);

    public static Home newInstance() {
        Home home = new Home();
        return home;
    }
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup viewGroup, Bundle savedInstanceState) {
        super.onCreateView(inflater, viewGroup, savedInstanceState);
        View rootView = inflater.inflate(R.layout.fragment_home, viewGroup, false);
        logout = rootView.findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sharedPreferences = getActivity().getSharedPreferences("my_preference", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.clear();
                editor.commit();
                Intent logout = new Intent(getActivity(), LoginActivity.class);
                startActivity(logout);
            }
        });

        return rootView;
    }
//    public void scheduleJob(View view){
//        ComponentName componentName = new ComponentName(getApplicationContext(), MyJobService.class);
//        JobInfo info = new JobInfo.Builder(123, componentName)
//                .setRequiredNetworkType(JobInfo.NETWORK_TYPE_UNMETERED)
//                .setPersisted(true)
//                .setPeriodic(15 * 60 * 1000) // tiap 15 menit
//                .build();
//
//        JobScheduler scheduler = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);
//        int resultCode = scheduler.schedule(info);
//        if(resultCode == JobScheduler.RESULT_SUCCESS){
//            Log.i(TAG, "scheduleJob = Job Scheduled");
//        } else {
//            Log.i(TAG, "scheduleJob: Job scheduling failed");
//        }
//    }
//
//    public void cancelJob(View view){
//        JobScheduler scheduler = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);
//        scheduler.cancel(123);
//        Log.i(TAG,"cancelJob");
//    }
}
