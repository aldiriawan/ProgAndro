package com.example.helloworld;

import android.app.DownloadManager;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.helloworld.ui.main.StationAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Profile.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Profile#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Profile extends Fragment {
    public static final String TAG = "MiTAG";
    RequestQueue QUEUE;
    String URLHTTP;

    private List<Object> mRecyclerViewItems = new ArrayList<>();
    private RecyclerView.Adapter mAdapter;
    RecyclerView rv;

      public static Profile newInstance() {
        Profile profile = new Profile();
        return profile;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View rootView = inflater.inflate(R.layout.fragment_profilefragment, container, false);

        rv = (RecyclerView)rootView.findViewById(R.id.myrecyclerview);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));

        mAdapter = new StationAdapter(getContext(), mRecyclerViewItems, this);
        QUEUE= Volley.newRequestQueue(getContext());
        URLHTTP = getResources().getString(R.string.urlserver);
        httpGET(URLHTTP);
        return rootView;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void httpGET(String url){
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        parsingData(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                try {
                    String responseBody = new String(error.networkResponse.data, "utf-8");
                    Log.d(TAG, "ERROR" + responseBody);
                } catch (UnsupportedEncodingException errorr) {
                    Log.d(TAG, errorr.toString());
                }
            }
        }
        );
        QUEUE.add(stringRequest);
    }


    public void parsingData(String jsonData){
          try{
              JSONObject obj = new JSONObject(jsonData);
              JSONArray m_jArry = obj.getJSONArray("station");
              for(int i = 0; i < m_jArry.length(); i++){
                  JSONObject jo_inside = m_jArry.getJSONObject(i);
                  String nama = jo_inside.getString("title");
                  String gambar = jo_inside.getString("images");
                  String synopsys = jo_inside.getString("synopsis");

                  StationItem stationItem = new StationItem(nama, gambar, synopsys);
                  mRecyclerViewItems.add(stationItem);
              }

              rv.setAdapter(mAdapter);
          } catch (JSONException e){
              e.printStackTrace();
          }
    }
}
