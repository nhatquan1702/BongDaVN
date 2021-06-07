package com.example.apptinthethao_java.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.apptinthethao_java.R;
import com.example.apptinthethao_java.model.NgayThiDau;
import com.example.apptinthethao_java.model.TranDau;
import com.thoughtbot.expandablerecyclerview.ExpandableRecyclerViewAdapter;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;
import com.thoughtbot.expandablerecyclerview.viewholders.ChildViewHolder;
import com.thoughtbot.expandablerecyclerview.viewholders.GroupViewHolder;

import java.util.List;

// chứa list các trận đấu (2 đội, giờ đấu, tỉ số)
public class TranDauAdapter extends ExpandableRecyclerViewAdapter<NgayDauViewHolder, TranDauViewHolder> {

    public TranDauAdapter(List<? extends ExpandableGroup> groups) {
        super(groups);
    }

    @Override
    public NgayDauViewHolder onCreateGroupViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_ngaydau, parent,false);
        return new NgayDauViewHolder(v);
    }

    @Override
    public TranDauViewHolder onCreateChildViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_lichdau, parent,false);
        return new TranDauViewHolder(v);
    }

    @Override
    public void onBindChildViewHolder(TranDauViewHolder holder, int flatPosition, ExpandableGroup group, int childIndex) {
        final TranDau tranDau = (TranDau) group.getItems().get(childIndex);
        holder.bind(tranDau);
    }

    @Override
    public void onBindGroupViewHolder(NgayDauViewHolder holder, int flatPosition, ExpandableGroup group) {
        final NgayThiDau ngayThiDau = (NgayThiDau) group;
        holder.bind(ngayThiDau);
    }
}
