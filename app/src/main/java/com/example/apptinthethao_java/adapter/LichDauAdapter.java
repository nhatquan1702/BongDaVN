package com.example.apptinthethao_java.adapter;

import android.content.Context;
import android.telephony.AccessNetworkConstants;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.apptinthethao_java.R;
import com.example.apptinthethao_java.model.TranDau;
import com.squareup.picasso.Picasso;
//import com.thoughtbot.expandablerecyclerview.viewholders.ChildViewHolder;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class LichDauAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener {
    private Context mContext;
    private List<Object> mObjects;

    public static final int TEXT = 0;
    public static final int TRAN_DAU = 1;

    public static ItemClickInterface itemClickListener;

    public LichDauAdapter(Context context, List<Object> objects) {
        mContext = context;
        mObjects = objects;
    }

    @NonNull
    @NotNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        switch (viewType) {
            case TEXT: {
                View view0 = layoutInflater.inflate(R.layout.item_ngaydau, parent, false);
                return new NgayDauViewHolder(view0);
            }
            case TRAN_DAU: {
                View view1 = layoutInflater.inflate(R.layout.item_lichdau, parent, false);
                view1.setOnClickListener(this);
                return new TranDauViewHolder(view1);
            }
            default:
                break;
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull RecyclerView.ViewHolder holder, int position) {
        switch (getItemViewType(position)) {
            case TEXT: {
                NgayDauViewHolder ngayDauViewHolder = (NgayDauViewHolder) holder;
                ngayDauViewHolder.mDate.setText(mObjects.get(position).toString());
                break;
            }
            case TRAN_DAU: {
                TranDau tranDau = (TranDau) mObjects.get(position);
                TranDauViewHolder tranDauViewHolder = (TranDauViewHolder) holder;
                tranDauViewHolder.leftTeam.setText(tranDau.getClb_home_name());
                tranDauViewHolder.rightTeam.setText(tranDau.getClb_guess_name());
                if (tranDau.getMatch_result().equals("-:-"))
                    tranDauViewHolder.matchTime.setText(tranDau.getMatch_time());
                else
                    tranDauViewHolder.matchTime.setText(tranDau.getMatch_result());
                Picasso.get()
                        .load(tranDau.getLogo_home_url())
                        .resize(50, 50)
                        .centerCrop()
                        .placeholder(R.drawable.galleryoo)
                        .error(R.drawable.galleryoo)
                        .into(tranDauViewHolder.imgLeftTeam);
                Picasso.get()
                        .load(tranDau.getLogo_guess_url())
                        .resize(50, 50)
                        .centerCrop()
                        .placeholder(R.drawable.galleryoo)
                        .error(R.drawable.galleryoo)
                        .into(tranDauViewHolder.imgRightTeam);
                break;
            }
        }
    }

    @Override
    public int getItemCount() {
        if (mObjects != null)
            return mObjects.size();
        else
            return 0;
    }

    public void updateChange(ArrayList<Object> data) {
        mObjects = data;
        notifyDataSetChanged();
    }

    public TranDau getAtPosition(int position){
        return (TranDau) mObjects.get(position);
    }

    public void setOnItemClickListener(ItemClickInterface clickListener) {
        LichDauAdapter.itemClickListener = clickListener;
    }

    @Override
    public int getItemViewType(int position) {
        if (mObjects.get(position) instanceof String) // nếu là ngày đấu
            return TEXT;
        else if (mObjects.get(position) instanceof TranDau) // nếu là các trận đấu
            return TRAN_DAU;
        return -1;
    }

    @Override
    public void onClick(View v) {

    }

    // view holder ( ngaydau , trandau)
    public class NgayDauViewHolder extends RecyclerView.ViewHolder {
        private TextView mDate;

        public NgayDauViewHolder(View itemView) {
            super(itemView);
            mDate = itemView.findViewById(R.id.tv_ngaydau);
        }
    }

    public class TranDauViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView leftTeam;
        private TextView rightTeam;
        private TextView matchTime;

        private ImageView imgLeftTeam;
        private ImageView imgRightTeam;

        public TranDauViewHolder(View itemView) {
            super(itemView);
            leftTeam = itemView.findViewById(R.id.tv_left_team);
            rightTeam = itemView.findViewById(R.id.tv_right_team);
            matchTime = itemView.findViewById(R.id.tv_time);
            imgLeftTeam = itemView.findViewById(R.id.img_left_team);
            imgRightTeam = itemView.findViewById(R.id.img_right_team);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            itemClickListener.onClick(v,getAdapterPosition());
        }
    }
}


