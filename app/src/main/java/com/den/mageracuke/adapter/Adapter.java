package com.den.mageracuke.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.den.mageracuke.R;
import com.den.mageracuke.data.Data;

import java.util.List;

public class Adapter extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private List<Data> items;
    public Adapter(Activity activity, List<Data> items) {
        this.activity = activity;
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int location) {
        return items.get(location);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (inflater == null)
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null)
            convertView = inflater.inflate(R.layout.list_row, null);

        TextView id = (TextView) convertView.findViewById(R.id.id);
        TextView nama = (TextView) convertView.findViewById(R.id.nama);
        TextView no = (TextView) convertView.findViewById(R.id.nopol);
        TextView tanggal = (TextView) convertView.findViewById(R.id.tanggal);
        TextView status = (TextView) convertView.findViewById(R.id.status);

        Data data = items.get(position);

        id.setText(data.getId());
        no.setText(data.getNo());
        nama.setText(data.getNama());
        tanggal.setText(data.getTanggal());


        if (data.getStatus() == 1){
            status.setText("Menunggu");
        }else if(data.getStatus() == 2){
            status.setText("Proses");
        }else if(data.getStatus() == 3){
            status.setText("Selesai");
        }else if(data.getStatus() == 4) {
            status.setText("Telah diambil");
        }else{
            status.setText("Coba");
        }
        return convertView;
    }

}