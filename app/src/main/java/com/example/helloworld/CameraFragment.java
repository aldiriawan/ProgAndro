package com.example.helloworld;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Camera;
import android.net.Uri;
import android.os.Bundle;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class CameraFragment extends Fragment {

    public static final int MY_PERMISSIONS_REQUEST_CAMERA = 100;
    public static final String ALLOW_KEY = "ALLOWED";
    public static final String CAMERA_PREF = "settingsPreference";

    public static CameraFragment newInstance() {
        CameraFragment camera = new CameraFragment();
        return camera;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_camera, container, false);
        return view;
    }

    public static Boolean getFromPref(Context context, String key) {
        SharedPreferences myPrefs = context.getSharedPreferences(CAMERA_PREF,
                Context.MODE_PRIVATE);
        return (myPrefs.getBoolean(key, false));
    }

    private void checkCameraHardware() {
        AlertDialog alertDialog = new AlertDialog.Builder(requireActivity()).create();
        alertDialog.setTitle("Alert");
        alertDialog.setMessage("Aplikasi Membutuhkan akses kamera!");
        if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            if (getFromPref(requireContext(), ALLOW_KEY)) {
                alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Tidak Diijinkan",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "SETTINGS",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                alertDialog.show();
            } else if (ContextCompat.checkSelfPermission(requireContext(),
                    Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(requireActivity(),
                        Manifest.permission.CAMERA)) {
                    alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Tidak Diijinkan",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Ijinkan",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                    ActivityCompat.requestPermissions(requireActivity(),
                                            new String[]{Manifest.permission.CAMERA},
                                            MY_PERMISSIONS_REQUEST_CAMERA);
                                }
                            });
                    alertDialog.show();
                } else {
                    ActivityCompat.requestPermissions(requireActivity(),
                            new String[]{Manifest.permission.CAMERA},
                            MY_PERMISSIONS_REQUEST_CAMERA);
                }
            }
        } else {
            Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
            startActivity(intent);
        }
    }

    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_CAMERA: {
                for (int i = 0, len = permissions.length; i < len; i++) {
                    String permission = permissions[i];
                    if (grantResults[i] == PackageManager.PERMISSION_DENIED) {
                        boolean showRationale =
                                ActivityCompat.shouldShowRequestPermissionRationale(
                                        requireActivity(), permission);
                        if (showRationale) {
                            AlertDialog alertDialog = new AlertDialog.Builder(requireActivity()).create();
                            alertDialog.setTitle("Alert");
                            alertDialog.setMessage("App needs to access the Camera.");
                            alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "DONT ALLOW",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int which) {
                                            dialog.dismiss();
                                        }
                                    });
                            alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "ALLOW",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int which) {
                                            dialog.dismiss();
                                            ActivityCompat.requestPermissions(requireActivity(),
                                                    new String[]{Manifest.permission.CAMERA},
                                                    MY_PERMISSIONS_REQUEST_CAMERA);
                                        }
                                    });
                            alertDialog.show();
//                        } else if (!showRationale) {
//                            Context context;
//                            SharedPreferences myPrefs = context.getSharedPreferences(CAMERA_PREF,
//                                    Context.MODE_PRIVATE);
//                            SharedPreferences.Editor prefsEditor = myPrefs.edit();
//                            String key;
//                            prefsEditor.putBoolean(key, allowed);
//                            prefsEditor.commit();
//                        }
                        }
                    }
                }
            }
        }
    }
}
