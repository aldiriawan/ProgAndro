package com.example.helloworld;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Context;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

public class MyJobService extends JobService {

    private static final String TAG = MyJobService.class.getSimpleName();
    private boolean jobCancelled = false;
    Context context;

    @Override
    public boolean onStartJob(JobParameters params) {
        Log.i(TAG,"onStartJob:");
        doBackground(params);
        return true;
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        Log.i(TAG,"onStopJob: cancel;");
        jobCancelled = true;
        return true;
    }

    private void doBackground(final JobParameters parameters){
        //simulasi sebuah proses berjalan secara async
        new Thread(
                //anonymous object
                new Runnable() {
                    @Override
                    public void run() {
                        for(int i = 0; i < 10; i++){
                            Log.i(TAG, "run: " + i);

                            final int finalI = i;
                            Handler mHandler = new Handler(getMainLooper());
                            mHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(getApplicationContext(), "Handler Run : " + finalI, Toast.LENGTH_SHORT).show();
                                }
                            });

                            if(jobCancelled){
                                return;
                            }
                            //simulasi proses yang berjalan selama 3 detik
                            try{
                                Thread.sleep(3000);
                            } catch(InterruptedException e){
                                Log.e(TAG,"InterruptedException: ", e.getCause());
                            }
                        }

                        Log.i(TAG,"run: JOB FINISHED");
                    }
                }).start();
    }
}
