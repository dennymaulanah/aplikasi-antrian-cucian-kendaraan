package com.den.mageracuke.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.den.mageracuke.R;
import com.den.mageracuke.adapter.Adapter;
import com.den.mageracuke.app.AppController;
import com.den.mageracuke.data.Data;
import com.den.mageracuke.util.Server;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.android.volley.VolleyLog.TAG;


public class Antrian extends Fragment implements SwipeRefreshLayout.OnRefreshListener {


    List<Data> itemList = new ArrayList<Data>();
    ListView list;
    SwipeRefreshLayout swipe;
    Adapter adapter;


    private static String url_select     = Server.URL + "antrian.php";

    public static final String TAG_ID            = "id";
    public static final String TAG_NO            = "no_kendaraan";
    public static final String TAG_NAMA          = "pemilik";
    public static final String TAG_TANGGAL       = "waktu_antri";
    public static final String TAG_STATUS        = "status";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.f_antrian, null);


        swipe   = (SwipeRefreshLayout) v.findViewById(R.id.swipe_refresh_layout);
        list    = (ListView) v.findViewById(R.id.list);

        adapter = new Adapter(getActivity(), itemList);
        list.setAdapter(adapter);

        // menamilkan widget refresh
        swipe.setOnRefreshListener(this);

        swipe.post(new Runnable() {
                       @Override
                       public void run() {
                           swipe.setRefreshing(true);
                           itemList.clear();
                           adapter.notifyDataSetChanged();
                           callVolley();
                       }
                   }
        );


        return v;
    }
    @Override
    public void onRefresh() {
        itemList.clear();
        adapter.notifyDataSetChanged();
        callVolley();
    }
    // untuk menampilkan semua data pada listview
    private void callVolley(){
        itemList.clear();
        adapter.notifyDataSetChanged();
        swipe.setRefreshing(true);

        // membuat request JSON
        JsonArrayRequest jArr = new JsonArrayRequest(url_select, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Log.d(TAG, response.toString());

                // Parsing json
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject obj = response.getJSONObject(i);

                        Data item = new Data();

                        item.setId(obj.getString(TAG_ID));
                        item.setNo(obj.getString(TAG_NO));
                        item.setNama(obj.getString(TAG_NAMA));
                        item.setTanggal(obj.getString(TAG_TANGGAL));
                        item.setStatus(obj.getString(TAG_STATUS));

                        // menambah item ke array
                        itemList.add(item);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                // notifikasi adanya perubahan data pada adapter
                adapter.notifyDataSetChanged();

                swipe.setRefreshing(false);
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                swipe.setRefreshing(false);
            }
        });

        // menambah request ke request queue
        AppController.getInstance().addToRequestQueue(jArr);
    }
}