package com.example.apptinthethao_java.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.apptinthethao_java.R;
import com.example.apptinthethao_java.model.CauThu_DoiHinh;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ChiTietCLBAdapter extends BaseAdapter {
    private ArrayList<CauThu_DoiHinh> cauThuDoiHinhsArrayList;
    public ChiTietCLBAdapter(ArrayList<CauThu_DoiHinh> arrayList){
        this.cauThuDoiHinhsArrayList = arrayList;
    }

    @Override
    public int getCount() {
        return cauThuDoiHinhsArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return cauThuDoiHinhsArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        if (convertView == null) {
            view = View.inflate(parent.getContext(), R.layout.item_cau_thu, null);
        } else view = convertView;
        ImageView imageView = (ImageView)view.findViewById(R.id.imgCauThu);
        Picasso.get()
                .load(cauThuDoiHinhsArrayList.get(position).getImgCauThu())
                .resize(50, 50)
                .centerCrop()
                .placeholder(R.drawable.gallery)
                .error(R.drawable.gallery)
                .into(imageView);
        ((TextView)view.findViewById(R.id.tvTenCauThu)).setText(cauThuDoiHinhsArrayList.get(position).getTenCauThu());
        ((TextView)view.findViewById(R.id.tvSoAoCauThu)).setText(String.valueOf(cauThuDoiHinhsArrayList.get(position).getSoAo()));
        return view;
    }
}
