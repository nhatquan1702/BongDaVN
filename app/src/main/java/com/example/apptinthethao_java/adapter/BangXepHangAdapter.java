package com.example.apptinthethao_java.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.apptinthethao_java.R;
import com.example.apptinthethao_java.model.BXH_DoiBong;

import java.util.ArrayList;

public class BangXepHangAdapter extends RecyclerView.Adapter<BangXepHangAdapter.ViewHolder> {

    Context context;
    ArrayList<BXH_DoiBong> BXH;

    public BangXepHangAdapter(Context context, ArrayList<BXH_DoiBong> BXH) {
        this.context = context;
        this.BXH = BXH;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public ArrayList<BXH_DoiBong> getBXH() {
        return BXH;
    }

    public void setBXH(ArrayList<BXH_DoiBong> BXH) {
        this.BXH = BXH;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_bxh,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BangXepHangAdapter.ViewHolder holder, int position) {
        int i=1;
        if (BXH!=null && BXH.size()>0){
            BXH_DoiBong doibong = BXH.get(position);
            holder.STT.setText(i);
            holder.TenDoiBong.setText(doibong.getTenDoiBong());
            holder.SoTran.setText(doibong.getSotran());
            holder.Thang.setText(doibong.getThang());
            holder.Hoa.setText(doibong.getHoa());
            holder.Bai.setText(doibong.getThua());
            holder.HieuSo.setText(doibong.getHieuso());
            holder.Diem.setText(doibong.getDiem());
        }else{
            return;
        }
    }

    @Override
    public int getItemCount() {
        return BXH.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView TenDoiBong,STT,SoTran, Thang, Hoa, Bai, HieuSo, Diem;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            STT=itemView.findViewById(R.id.STT);
            TenDoiBong=itemView.findViewById(R.id.TenCLB);
            SoTran=itemView.findViewById(R.id.SoTran);
            Thang=itemView.findViewById(R.id.Thang);
            Hoa=itemView.findViewById(R.id.Hoa);
            Bai=itemView.findViewById(R.id.Bai);
            HieuSo=itemView.findViewById(R.id.HieuSo);
            Diem=itemView.findViewById(R.id.Diem);
        }
    }
}
