package com.example.apptinthethao_java.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.apptinthethao_java.R;
import com.example.apptinthethao_java.model.Cmt;

import java.util.ArrayList;

public class RecyclerViewCmtAdapter extends RecyclerView.Adapter<RecyclerViewCmtAdapter.ViewHolder>{
    private Context acontext;
    private ArrayList<Cmt> cmtArrayList;
    private ItemClickInterface itemClickInterface;
    private String setText;
    public void setOnClickItemRecyclerView(ItemClickInterface itemRecyclerView){
        this.itemClickInterface = itemRecyclerView;
    }
    public RecyclerViewCmtAdapter(String setText, ArrayList<Cmt> arrayList, Context mContext) {
        this.cmtArrayList = arrayList;
        this.acontext = mContext;
        this.setText = setText;
    }

    @NonNull
    @Override
    public RecyclerViewCmtAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View heroView = inflater.inflate(R.layout.item_cmt, parent, false);
        RecyclerViewCmtAdapter.ViewHolder viewHolder = new RecyclerViewCmtAdapter.ViewHolder(heroView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewCmtAdapter.ViewHolder holder, int position) {
        holder.bind();
    }

    @Override
    public int getItemCount() {
        return cmtArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView tvUserCmt, tvNgayCmt, tvXoaCmt, tvNoiDungCmt;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvUserCmt = itemView.findViewById(R.id.tvUserNameCmt);
            tvNgayCmt = itemView.findViewById(R.id.tvNgayCmt);
            tvXoaCmt = itemView.findViewById(R.id.tvXoaComment);
            tvNoiDungCmt = itemView.findViewById(R.id.tvNoiDungCmt);
        }
        public void bind(){
            tvUserCmt.setText(cmtArrayList.get(getAdapterPosition()).getUserName());
            tvNgayCmt.setText(cmtArrayList.get(getAdapterPosition()).getNgayCmt());
            tvNoiDungCmt.setText(cmtArrayList.get(getAdapterPosition()).getNoiDungCmt());
            tvXoaCmt.setText(setText);
            tvXoaCmt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    itemClickInterface.onClick(v, getAdapterPosition());
                }
            });
        }
    }
}
