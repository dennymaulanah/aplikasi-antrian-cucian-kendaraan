package com.den.mageracuke;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.den.mageracuke.app.AppController;
import com.den.mageracuke.util.Server;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Login extends AppCompatActivity {

    ProgressDialog pDialog;
    Button btn_login;
    EditText txt_token;

    int success;
    ConnectivityManager conMgr;

    private String url = Server.URL + "login.php";

    private static final String TAG = Login.class.getSimpleName();

    private static final String TAG_SUCCESS = "success";
    private static final String TAG_MESSAGE = "message";

    public final static String TAG_TOKEN = "token";
    public final static String TAG_ID = "id";
    public final static String TAG_PEMILIK = "pemilik";
    public final static String TAG_STATUS = "status";
    public final static String TAG_NO = "no_kendaraan";
    public final static String TAG_KENDARAAN = "kendaraan";
    public final static String TAG_TIPE = "tipe_kendaraan";
    public final static String TAG_WAKTU = "waktu_keluar";

    String tag_json_obj = "json_obj_req";

    SharedPreferences sharedpreferences;
    Boolean session = false;
    String id, token,pemilik, status, no_kendaraan, kendaraan, tipe_kendaraan, waktu_keluar;
    public static final String my_shared_preferences = "my_shared_preferences";
    public static final String session_status = "session_status";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        conMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        {
            if (conMgr.getActiveNetworkInfo() != null
                    && conMgr.getActiveNetworkInfo().isAvailable()
                    && conMgr.getActiveNetworkInfo().isConnected()) {
            } else {
                Toast.makeText(getApplicationContext(), "No Internet Connection",
                        Toast.LENGTH_LONG).show();
            }
        }

        btn_login = (Button) findViewById(R.id.btn_login);
        txt_token = (EditText) findViewById(R.id.txt_token);
        // Cek session login jika TRUE maka langsung buka MainActivity
        sharedpreferences = getSharedPreferences(my_shared_preferences, Context.MODE_PRIVATE);
        session = sharedpreferences.getBoolean(session_status, false);
        id = sharedpreferences.getString(TAG_ID, null);
        token = sharedpreferences.getString(TAG_TOKEN, null);
        pemilik = sharedpreferences.getString(TAG_PEMILIK, null);
        status = sharedpreferences.getString(TAG_STATUS, null);
        no_kendaraan = sharedpreferences.getString(TAG_NO, null);
        kendaraan = sharedpreferences.getString(TAG_KENDARAAN, null);
        tipe_kendaraan = sharedpreferences.getString(TAG_TIPE, null);
        waktu_keluar = sharedpreferences.getString(TAG_WAKTU, null);

        if (session) {
            Intent intent = new Intent(Login.this, MainActivity.class);
            intent.putExtra(TAG_ID, id);
            intent.putExtra(TAG_TOKEN, token);
            intent.putExtra(TAG_PEMILIK, pemilik);
            intent.putExtra(TAG_STATUS, status);
            intent.putExtra(TAG_NO, no_kendaraan);
            intent.putExtra(TAG_KENDARAAN, kendaraan);
            intent.putExtra(TAG_TIPE, tipe_kendaraan);
            intent.putExtra(TAG_WAKTU, waktu_keluar);
            finish();
            startActivity(intent);
        }


        btn_login.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                String token = txt_token.getText().toString();

                // mengecek kolom yang kosong
                if (token.trim().length() > 0) {
                    if (conMgr.getActiveNetworkInfo() != null
                            && conMgr.getActiveNetworkInfo().isAvailable()
                            && conMgr.getActiveNetworkInfo().isConnected()) {
                        checkLogin(token);
                    } else {
                        Toast.makeText(getApplicationContext() ,"No Internet Connection", Toast.LENGTH_LONG).show();
                    }
                } else {
                    // Prompt user to enter credentials
                    Toast.makeText(getApplicationContext() ,"Kolom tidak boleh kosong", Toast.LENGTH_LONG).show();
                }
            }
        });


    }

    private void checkLogin(final String token) {
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);
        pDialog.setMessage("Logging in ...");
        showDialog();

        StringRequest strReq = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.e(TAG, "Login Response: " + response.toString());
                hideDialog();

                try {
                    JSONObject jObj = new JSONObject(response);
                    success = jObj.getInt(TAG_SUCCESS);

                    // Check for error node in json
                    if (success == 1) {
                        String token = jObj.getString(TAG_TOKEN);
                        String id = jObj.getString(TAG_ID);
                        String pemilik = jObj.getString(TAG_PEMILIK);
                        String status = jObj.getString(TAG_STATUS);
                        String no_kendaraan = jObj.getString(TAG_NO);
                        String kendaraan = jObj.getString(TAG_KENDARAAN);
                        String tipe_kendaraan = jObj.getString(TAG_TIPE);
                        String waktu_keluar = jObj.getString(TAG_WAKTU);

                        Log.e("Successfully Login!", jObj.toString());

                        Toast.makeText(getApplicationContext(), jObj.getString(TAG_MESSAGE), Toast.LENGTH_LONG).show();

                        // menyimpan login ke session
                        SharedPreferences.Editor editor = sharedpreferences.edit();
                        editor.putBoolean(session_status, true);
                        editor.putString(TAG_ID, id);
                        editor.putString(TAG_TOKEN, token);
                        editor.putString(TAG_PEMILIK, pemilik);
                        editor.putString(TAG_STATUS, status);
                        editor.putString(TAG_NO, no_kendaraan);
                        editor.putString(TAG_KENDARAAN, kendaraan);
                        editor.putString(TAG_TIPE, tipe_kendaraan);
                        editor.putString(TAG_WAKTU, waktu_keluar);
                        editor.commit();

                        // Memanggil main activity
                        Intent intent = new Intent(Login.this, MainActivity.class);
                        intent.putExtra(TAG_ID, id);
                        intent.putExtra(TAG_TOKEN, token);
                        intent.putExtra(TAG_PEMILIK, pemilik);
                        intent.putExtra(TAG_STATUS, status);
                        intent.putExtra(TAG_NO, no_kendaraan);
                        intent.putExtra(TAG_KENDARAAN, kendaraan);
                        intent.putExtra(TAG_TIPE, tipe_kendaraan);
                        intent.putExtra(TAG_WAKTU, waktu_keluar);
                        finish();
                        startActivity(intent);
                    } else {
                        Toast.makeText(getApplicationContext(),
                                jObj.getString(TAG_MESSAGE), Toast.LENGTH_LONG).show();

                    }
                } catch (JSONException e) {
                    // JSON error
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Login Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_LONG).show();

                hideDialog();

            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                // Posting parameters to login url
                Map<String, String> params = new HashMap<String, String>();
                params.put("token", token);

                return params;
            }

        };

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq, tag_json_obj);
    }

    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideDialog() {
        if (pDialog.isShowing())
             pDialog.dismiss();
    }
}