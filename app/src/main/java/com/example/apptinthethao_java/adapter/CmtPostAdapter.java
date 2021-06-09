package com.example.apptinthethao_java.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.apptinthethao_java.R;
import com.example.apptinthethao_java.activity.DetailPostActivity;
import com.example.apptinthethao_java.api.SimpleAPI;
import com.example.apptinthethao_java.model.Cmt;
import com.example.apptinthethao_java.model.Status;
import com.example.apptinthethao_java.util.Constants;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CmtPostAdapter extends BaseAdapter {
    private ArrayList<Cmt> arrayList;
    private Context context;
    private SimpleAPI simpleAPI;
    private ItemClickInterface itemClickInterface;
    public void setOnClickItemListViewCmt(ItemClickInterface itemListViewCmt){
        itemClickInterface = itemListViewCmt;
    }
    public CmtPostAdapter(Context context, ArrayList<Cmt> postArrayList){
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
        View viewitem;
        viewitem = View.inflate(parent.getContext(), R.layout.item_cmt,null);
        ((TextView)viewitem.findViewById(R.id.tvUserNameCmt)).setText(arrayList.get(position).getUserName());
        ((TextView)viewitem.findViewById(R.id.tvNgayCmt)).setText(arrayList.get(position).getNgayCmt());
        ((TextView)viewitem.findViewById(R.id.tvNoiDungCmt)).setText(arrayList.get(position).getNoiDungCmt());
        ((TextView)viewitem.findViewById(R.id.tvXoaComment)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemClickInterface.onClick(viewitem, position);
            }
        });
        return viewitem;
    }
}
