package com.example.apptinthethao_java.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.apptinthethao_java.R;
import com.example.apptinthethao_java.model.TranDauSapDienRa;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ListViewTranSapDauAdapter extends BaseAdapter {
    private ArrayList<TranDauSapDienRa> arrayList;
    private Context context;
    public ListViewTranSapDauAdapter(Context context, ArrayList<TranDauSapDienRa> postArrayList){
        this.context = context;
        this.arrayList = postArrayList;
    }
    @Override
    public int getCount() {
        if(arrayList != null){
            return arrayList.size();
        }
        return 0;
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
            view = View.inflate(parent.getContext(), R.layout.item_tran_sap, null);
        } else view = convertView;
        ImageView imageView = (ImageView)view.findViewById(R.id.imgDoiNha);
        Picasso.get()
                .load(arrayList.get(position).getAvtDoiNha())
                .placeholder(R.drawable.gallery)
                .error(R.drawable.gallery)
                .into(imageView);
        ImageView imageView2 = (ImageView)view.findViewById(R.id.imgDoiKhach);
        Picasso.get()
                .load(arrayList.get(position).getAvtDoiKhach())
                .placeholder(R.drawable.gallery)
                .error(R.drawable.gallery)
                .into(imageView2);
        ((TextView)view.findViewById(R.id.tvTenDoiNha)).setText(String.valueOf(arrayList.get(position).getDoiNha()));
        ((TextView)view.findViewById(R.id.tvTenDoiKhach)).setText(arrayList.get(position).getDoiKhach());
        ((TextView)view.findViewById(R.id.tvTiSo)).setText(arrayList.get(position).getKetQua());
        ((TextView)view.findViewById(R.id.tvNgayThiDau)).setText(arrayList.get(position).getThoiGian());
        return view;
    }

}
