package com.example.apptinthethao_java.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.apptinthethao_java.R;
import com.example.apptinthethao_java.model.Post;
import com.example.apptinthethao_java.model.SuKienTrongTran;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class DienBienSuKienAdapter extends BaseAdapter {
    private ArrayList<SuKienTrongTran> arrayList;
    private Context context;
    public DienBienSuKienAdapter(Context context, ArrayList<SuKienTrongTran> postArrayList){
        this.context = context;
        this.arrayList = postArrayList;
    }
    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        if (convertView == null) {
            view = View.inflate(parent.getContext(), R.layout.item_dien_bien, null);
        } else view = convertView;
        ((TextView)view.findViewById(R.id.tvSoAoDB)).setText(String.valueOf(arrayList.get(position).getSoAo()));
        ((TextView)view.findViewById(R.id.tvTenCauThuDB)).setText(arrayList.get(position).getTenCauThu());
        ((TextView)view.findViewById(R.id.tvTenCLBDB)).setText(arrayList.get(position).getTenCLB());
        ((TextView)view.findViewById(R.id.tvPhutThu)).setText("'"+String.valueOf(arrayList.get(position).getPhutThu()));
        ((TextView)view.findViewById(R.id.tvSuKien)).setText("Sự kiện: "+arrayList.get(position).getNoiDung());
        return view;
    }
}
