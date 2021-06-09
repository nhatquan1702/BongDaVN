package com.example.apptinthethao_java.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.apptinthethao_java.R;
import com.example.apptinthethao_java.model.CauLacBo;
import com.example.apptinthethao_java.model.CauThuSimple;
import com.example.apptinthethao_java.model.CauThu_DoiHinh;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CauThuAdapter extends BaseAdapter {
    private ArrayList<CauThuSimple> cauThuSimpleArrayList;
    public CauThuAdapter(ArrayList<CauThuSimple> arrayList){
        this.cauThuSimpleArrayList = arrayList;
    }

    @Override
    public int getCount() {
        return cauThuSimpleArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return cauThuSimpleArrayList.get(position);
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
                .load(cauThuSimpleArrayList.get(position).getImgCauThu())
                .resize(50, 50)
                .centerCrop()
                .placeholder(R.drawable.galleryoo)
                .error(R.drawable.galleryoo)
                .into(imageView);
        ((TextView)view.findViewById(R.id.tvTenCauThu)).setText(cauThuSimpleArrayList.get(position).getTenCauThu());
        ((TextView)view.findViewById(R.id.tvSoAoCauThu)).setText(String.valueOf(cauThuSimpleArrayList.get(position).getSoAo()));
        return view;
    }
}
