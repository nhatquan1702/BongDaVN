package com.example.apptinthethao_java.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.apptinthethao_java.R;
import com.example.apptinthethao_java.model.TranDau;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

// chứa list thời gian (ngày thi đấu)
public class NgayThiDauAdapter extends BaseAdapter {
    private ArrayList<TranDau> ngayThiDauArrayList;
    public NgayThiDauAdapter(ArrayList<TranDau> arrayList){
        this.ngayThiDauArrayList = arrayList;
    }

    @Override
    public int getCount() {
        return ngayThiDauArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return ngayThiDauArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        if (convertView == null) {
            view = View.inflate(parent.getContext(), R.layout.item_ngaydau, null);
        } else view = convertView;
        ((TextView)view.findViewById(R.id.tv_ngaydau)).setText(ngayThiDauArrayList.get(position).getThoiDiem().toString());
        return view;
    }
}
