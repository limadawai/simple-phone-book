package com.limadawai.bukutelpon;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import java.util.List;

public class KontakAdapter extends BaseAdapter {

    Activity activity;
    LayoutInflater inflater;
    List<KontakDataset> list;

    public KontakAdapter(Activity activity, List<KontakDataset> list) {
        this.activity = activity;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (inflater == null)
            inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null)
            convertView = inflater.inflate(R.layout.activity_main, null);

        ImageView foto = convertView.findViewById(R.id.imgFoto);
        TextView nama = convertView.findViewById(R.id.tvNama);
        TextView telp = convertView.findViewById(R.id.tvTelpon);
        TextView email = convertView.findViewById(R.id.tvEmail);

        final KontakDataset kds = list.get(position);

        String img = kds.getImage();
        byte[] decodedBytes = Base64.decode(img, 0);
        Bitmap ft = BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.length);

        foto.setImageBitmap(ft);
        nama.setText(kds.getNama());
        telp.setText(kds.getTelpon());
        email.setText(kds.getEmail());

        return convertView;
    }
}
