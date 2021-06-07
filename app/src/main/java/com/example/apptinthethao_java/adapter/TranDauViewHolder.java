package com.example.apptinthethao_java.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.apptinthethao_java.R;
import com.example.apptinthethao_java.model.TranDau;
import com.squareup.picasso.Picasso;
import com.thoughtbot.expandablerecyclerview.viewholders.ChildViewHolder;

public class TranDauViewHolder extends ChildViewHolder {

    private TextView leftTeam;
    private TextView rightTeam;
    private TextView matchTime;
    private TextView leftScore;
    private TextView rightScore;

    private ImageView imgLeftTeam;
    private ImageView imgRightTeam;

    public TranDauViewHolder(View itemView) {
        super(itemView);
        leftTeam = itemView.findViewById(R.id.tv_left_team);
        rightTeam = itemView.findViewById(R.id.tv_right_team);
        matchTime = itemView.findViewById(R.id.tv_time);
        leftScore = itemView.findViewById(R.id.tv_left_score);
        rightScore = itemView.findViewById(R.id.tv_right_score);
        imgLeftTeam = itemView.findViewById(R.id.img_left_team);
        imgRightTeam = itemView.findViewById(R.id.img_right_team);
    }

    public void bind(TranDau tranDau) {
        leftTeam.setText(tranDau.getDoiNha().getTenCLB());
        rightTeam.setText(tranDau.getDoiKhach().getTenCLB());
        matchTime.setText(tranDau.getThoiGian());
        if(!tranDau.getKetQua().equals("-:-")){
            //set kq
            leftScore.setText("0");
            rightScore.setText("0");
        }
        Picasso.get()
                .load(tranDau.getDoiNha().getUrlLogo())
                .resize(50, 50)
                .centerCrop()
                .placeholder(R.drawable.galleryoo)
                .error(R.drawable.galleryoo)
                .into(imgLeftTeam);
        Picasso.get()
                .load(tranDau.getDoiKhach().getUrlLogo())
                .resize(50, 50)
                .centerCrop()
                .placeholder(R.drawable.galleryoo)
                .error(R.drawable.galleryoo)
                .into(imgRightTeam);
    }
}
