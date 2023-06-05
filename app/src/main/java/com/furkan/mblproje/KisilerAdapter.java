package com.furkan.mblproje;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.List;

public class KisilerAdapter extends ArrayAdapter<Kisi> {

    public KisilerAdapter( Context context, List<Kisi> kisiler) {
        super(context, 0, kisiler);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.kisi_list_item, parent, false);
        }

        Kisi kisi = getItem(position);

        TextView TxtName = convertView.findViewById(R.id.TxtName);
        TextView TxtNumber  = convertView.findViewById(R.id.TxtNumber);

        if (kisi != null) {
            TxtName.setText(kisi.getAd());
            TxtNumber.setText(kisi.getNumara());
        }

        return convertView;
    }
}
