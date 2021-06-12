package com.example.apptinthethao_java.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.apptinthethao_java.R;
import com.example.apptinthethao_java.model.CauLacBo;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CauLacBoAdapter extends BaseAdapter {
    private ArrayList<CauLacBo> cauLacBoArrayList;
    public CauLacBoAdapter(ArrayList<CauLacBo> arrayList){
        this.cauLacBoArrayList = arrayList;
    }
    @Override
    public int getCount() {
        if(cauLacBoArrayList != null){
            return cauLacBoArrayList.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return cauLacBoArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        if (convertView == null) {
            view = View.inflate(parent.getContext(), R.layout.item_ds_clb, null);
        } else view = convertView;
        ((TextView)view.findViewById(R.id.tvTenCLB)).setText(cauLacBoArrayList.get(position).getTenCLB());
        ImageView imageView = (ImageView)view.findViewById(R.id.imgCLB);
        Picasso.get()
                .load(cauLacBoArrayList.get(position).getLink())
                .resize(50, 50)
                .centerCrop()
                .placeholder(R.drawable.galleryoo)
                .error(R.drawable.galleryoo)
                .into(imageView);
        return view;
    }
}
