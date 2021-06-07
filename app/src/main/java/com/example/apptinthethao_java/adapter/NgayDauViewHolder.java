package com.example.apptinthethao_java.adapter;

import android.view.View;
import android.widget.TextView;

import com.example.apptinthethao_java.R;
import com.example.apptinthethao_java.model.NgayThiDau;
import com.thoughtbot.expandablerecyclerview.viewholders.GroupViewHolder;

public class NgayDauViewHolder extends GroupViewHolder {
    private TextView mDate;
    public NgayDauViewHolder(View itemView) {
        super(itemView);
        mDate = itemView.findViewById(R.id.tv_ngaydau);
    }

    public void bind(NgayThiDau ngayThiDau) {
        mDate.setText(ngayThiDau.getTitle());
    }
}
