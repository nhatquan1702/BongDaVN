package com.example.apptinthethao_java.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.apptinthethao_java.R;
import com.example.apptinthethao_java.model.CauThuSimple;
import com.example.apptinthethao_java.model.TranDau;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class TranDauAdapter extends BaseAdapter{
    private ArrayList<TranDau> tranDauArrayList;

    public TranDauAdapter(ArrayList<TranDau> tranDauArrayList) {
        this.tranDauArrayList = tranDauArrayList;
    }

    @Override
    public int getCount() {
        if(tranDauArrayList != null){
            return tranDauArrayList.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return tranDauArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        if (convertView == null) {
            view = View.inflate(parent.getContext(), R.layout.item_lichdau, null);
        } else view = convertView;

        ((TextView)view.findViewById(R.id.tv_left_team)).setText(tranDauArrayList.get(position).getClb_home_name());
        ((TextView)view.findViewById(R.id.tv_right_team)).setText(tranDauArrayList.get(position).getClb_guess_name());
        ((TextView)view.findViewById(R.id.tv_time)).setText(tranDauArrayList.get(position).getMatch_result());

        return view;
    }
}
