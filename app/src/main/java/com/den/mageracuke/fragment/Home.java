package com.den.mageracuke.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.den.mageracuke.R;

public class Home extends Fragment {

    TextView kendara, tipe, nopol, stat, waktu;
    String token,status, no_kendaraan, kendaraan, tipe_kendaraan, waktu_keluar;

    public final static String TAG_TOKEN = "token";
    public final static String TAG_STATUS = "status";
    public final static String TAG_NO = "no_kendaraan";
    public final static String TAG_KENDARAAN = "kendaraan";
    public final static String TAG_TIPE = "tipe_kendaraan";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.f_home, null);

        kendara = v.findViewById(R.id.kendaraan);
        tipe = v.findViewById(R.id.tipe);
        nopol = v.findViewById(R.id.nopol);
        stat = v.findViewById(R.id.status);

        token = getActivity().getIntent().getStringExtra(TAG_TOKEN);
        kendaraan = getActivity().getIntent().getStringExtra(TAG_KENDARAAN);
        tipe_kendaraan = getActivity().getIntent().getStringExtra(TAG_TIPE);
        no_kendaraan = getActivity().getIntent().getStringExtra(TAG_NO);
        status = getActivity().getIntent().getStringExtra(TAG_STATUS);

        tipe.setText(tipe_kendaraan);
        nopol.setText(no_kendaraan);

        int ken = Integer.parseInt(kendaraan);

        if(ken == 1) {
            kendara.setText("Motor");
        } else if(ken == 2) {
            kendara.setText("Mobil");
        }


        return v;
    }
}
