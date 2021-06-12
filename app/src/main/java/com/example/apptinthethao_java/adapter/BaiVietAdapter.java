package com.example.apptinthethao_java.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.apptinthethao_java.R;
import com.example.apptinthethao_java.model.Post;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class BaiVietAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener{

    private Context mContext;
    private ArrayList<Post> mObjects;
    public static ItemClickInterface itemClickListener;

    public BaiVietAdapter(Context context, ArrayList<Post> objects) {
        mContext = context;
        mObjects = objects;
    }

    @NonNull
    @NotNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        View itemView = layoutInflater.inflate(R.layout.item_list_view_post,parent,false);
        return new BaiVietViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull RecyclerView.ViewHolder holder, int position) {
        Post baiViet = (Post) mObjects.get(position);
        BaiVietViewHolder baiVietViewHolder = (BaiVietViewHolder) holder;
        baiVietViewHolder.mTitle.setText(baiViet.getPost_title());
        baiVietViewHolder.mTime.setText(baiViet.getPost_create_time());
        Picasso.get()
                .load(baiViet.getPost_img())
                .resize(100, 100)
                .centerCrop()
                .placeholder(R.drawable.galleryoo)
                .error(R.drawable.galleryoo)
                .into(baiVietViewHolder.mImg);
    }

    @Override
    public int getItemCount() {
        if (mObjects != null)
            return mObjects.size();
        else
            return 0;
    }

    public void updateChange(ArrayList<Post> data) {
        mObjects = data;
        notifyDataSetChanged();
    }

    @Override
    public void onClick(View v) {

    }

    public Post getAtPosition(int position){
        return (Post) mObjects.get(position);
    }

    public void setOnItemClickListener(ItemClickInterface clickListener) {
        BaiVietAdapter.itemClickListener = clickListener;
    }

    public class BaiVietViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView mImg;
        private TextView mTitle;
        private TextView mTime;


        public BaiVietViewHolder(View itemView) {
            super(itemView);
            mImg = itemView.findViewById(R.id.img);
            mTitle = itemView.findViewById(R.id.txtTittle);
            mTime = itemView.findViewById(R.id.txtTime);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            itemClickListener.onClick(v,getAdapterPosition());
        }
    }
}
