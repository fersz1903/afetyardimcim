package com.furkan.mblproje;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;

class AfetlerAdapter extends ArrayAdapter<Afet> {

    public AfetlerAdapter(Context context, ArrayList<Afet> afetListesi) {
        super(context, 0, afetListesi);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.afet_list_item, parent, false);
        }

        Afet afet = getItem(position);

        ImageView resimImageView = convertView.findViewById(R.id.resimImageView);
        TextView adTextView = convertView.findViewById(R.id.adTextView);
        TextView aciklamaTextView = convertView.findViewById(R.id.aciklamaTextView);

        if (afet != null) {
            resimImageView.setImageResource(afet.getResimId());
            adTextView.setText(afet.getAd());
            aciklamaTextView.setText(afet.getAciklama());
        }

        return convertView;
    }
}