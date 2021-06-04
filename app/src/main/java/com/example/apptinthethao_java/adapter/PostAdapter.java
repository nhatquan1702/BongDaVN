package com.example.apptinthethao_java.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.apptinthethao_java.R;
import com.example.apptinthethao_java.model.Post;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class PostAdapter extends BaseAdapter {
    private ArrayList<Post> arrayList;
    private Context context;
    public PostAdapter(Context context, ArrayList<Post> postArrayList){
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
        return arrayList.get(position).getPost_id();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View viewitem;
        if(position == 0)
            viewitem = View.inflate(parent.getContext(), R.layout.item_list_view_post_first,null);
        else
            viewitem = View.inflate(parent.getContext(), R.layout.item_list_view_post,null);
        Post post= arrayList.get(position);
        ((TextView)viewitem.findViewById(R.id.txtTittle)).setText(post.getPost_title());
        ((TextView)viewitem.findViewById(R.id.txtTime)).setText(post.getPost_create_time());
        ImageView imageView = (ImageView)viewitem.findViewById(R.id.img);
        Picasso.get()
                .load(post.getPost_img())
                .placeholder(R.drawable.gallery)
                .error(R.drawable.gallery)
                .into(imageView);
        return viewitem;
    }
}
