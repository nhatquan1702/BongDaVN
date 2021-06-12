package com.example.apptinthethao_java.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.apptinthethao_java.R;
import com.example.apptinthethao_java.model.BXH_DoiBong;

import java.util.ArrayList;

public class BangXepHangAdapter extends BaseAdapter {
    Context context;
    private ArrayList<BXH_DoiBong> arrBXH;

    public BangXepHangAdapter(Context context, ArrayList<BXH_DoiBong> arrBXH) {
        this.context = context;
        this.arrBXH = arrBXH;
    }

    @Override
    public int getCount() {
        if(arrBXH != null){
            return arrBXH.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return arrBXH.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    public static class DoiBongViewHolder{
        public TextView STT;
        public TextView TenCLB;
        public TextView SoTran;
        public TextView Thang;
        public TextView Hoa;
        public TextView Bai;
        public TextView BanThang;
        public TextView BanThua;
        public TextView HieuSo;
        public TextView Diem;

    }
    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        DoiBongViewHolder holder = null;
        if(view==null){
            LayoutInflater inflater=((Activity)context).getLayoutInflater();
            view=inflater.inflate(R.layout.item_bxh,null);
            holder= new DoiBongViewHolder();
            holder.STT=view.findViewById(R.id.tvSTT);
            holder.TenCLB=view.findViewById(R.id.tvTenCLB);
            holder.SoTran=view.findViewById(R.id.tvSoTran);
            holder.Thang=view.findViewById(R.id.tvThang);
            holder.Hoa=view.findViewById(R.id.tvHoa);
            holder.Bai=view.findViewById(R.id.tvBai);
            holder.BanThang=view.findViewById(R.id.tvBanThang);
            holder.BanThua=view.findViewById(R.id.tvBanThua);
            holder.HieuSo=view.findViewById(R.id.tvHieuSo);
            holder.Diem=view.findViewById(R.id.tvDiem);
            view.setTag(holder);
        }else{
            holder=(DoiBongViewHolder) view.getTag();
        }
        holder.STT.setText(String.valueOf(position+1));
        holder.TenCLB.setText(String.valueOf(arrBXH.get(position).getTenDoiBong()));
        holder.SoTran.setText(String.valueOf(arrBXH.get(position).getSotran()));
        holder.Thang.setText(String.valueOf(arrBXH.get(position).getThang()));
        holder.Hoa.setText(String.valueOf(arrBXH.get(position).getHoa()));
        holder.Bai.setText(String.valueOf(arrBXH.get(position).getThua()));
        holder.BanThang.setText(String.valueOf(arrBXH.get(position).getTongBanThang()));
        holder.BanThua.setText(String.valueOf(arrBXH.get(position).getTongBanThua()));
        holder.HieuSo.setText(String.valueOf(arrBXH.get(position).getHieuso()));
        holder.Diem.setText(String.valueOf(arrBXH.get(position).getDiem()));
        return view;
    }
}
