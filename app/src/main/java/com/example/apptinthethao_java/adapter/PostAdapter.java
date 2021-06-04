package com.example.apptinthethao_java.adapter;

import android.content.Context;
import android.text.format.DateFormat;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.apptinthethao_java.R;
import com.example.apptinthethao_java.model.Post;
import com.squareup.picasso.Picasso;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

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

        String tgTao = post.getPost_create_time();
        SimpleDateFormat simpleDateFormat  = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String tgHienTai = simpleDateFormat.format(Calendar.getInstance().getTime());
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date1 = null;
        Date date2 = null;
        try {
             date1 = format.parse(tgTao);
             date2 = format.parse(tgHienTai);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Long diff = date2.getTime() - date1.getTime();

        Long diffSeconds  = diff / 1000;
        Long diffMinutes = diff / (60 * 1000);
        Long diffHours = diff / (60 * 60 * 1000);
        Long ngay = diff / (60 * 60 * 1000*24);

        if(diffSeconds<60){
            ((TextView)viewitem.findViewById(R.id.txtTime)).setText(diffSeconds.toString() + " giây trước");
        }
        if(diffSeconds>60 && diffMinutes<60){
            ((TextView)viewitem.findViewById(R.id.txtTime)).setText(diffMinutes.toString() +" phút trước");
        }
        if(diffMinutes>60 && diffHours<24){
            ((TextView)viewitem.findViewById(R.id.txtTime)).setText(diffHours.toString() +" giờ trước");
        }
        if(diffHours>24 && ngay<30){
            ((TextView)viewitem.findViewById(R.id.txtTime)).setText(ngay.toString() +" ngày trước");
        }
        if(ngay>30){
            ((TextView)viewitem.findViewById(R.id.txtTime)).setText(post.getPost_create_time().substring(0,18));
        }

        ImageView imageView = (ImageView)viewitem.findViewById(R.id.img);
        Picasso.get()
                .load(post.getPost_img())
                .placeholder(R.drawable.gallery)
                .error(R.drawable.gallery)
                .into(imageView);
        return viewitem;
    }
}
