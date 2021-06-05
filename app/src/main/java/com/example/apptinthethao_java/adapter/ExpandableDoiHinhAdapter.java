package com.example.apptinthethao_java.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.example.apptinthethao_java.R;
import com.example.apptinthethao_java.model.CauThu_DoiHinh;

import java.util.ArrayList;
import java.util.HashMap;

public class ExpandableDoiHinhAdapter extends BaseExpandableListAdapter {
    private Context context;
    private ArrayList<String> expandleTieuDeDoiHinh;
    private HashMap<String, ArrayList<CauThu_DoiHinh>> expandelListChiTietDoiHinh;
    public ExpandableDoiHinhAdapter(Context context, ArrayList<String> expandleTitle, HashMap<String, ArrayList<CauThu_DoiHinh>> expandelListDetail) {
        this.context = context;
        this.expandleTieuDeDoiHinh = expandleTitle;
        this.expandelListChiTietDoiHinh = expandelListDetail;
    }
    @Override
    public int getGroupCount() {
        return expandleTieuDeDoiHinh.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return expandelListChiTietDoiHinh.get(expandleTieuDeDoiHinh.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return expandleTieuDeDoiHinh.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return expandelListChiTietDoiHinh.get(expandleTieuDeDoiHinh.get(groupPosition)).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        String expandleListTitleText = (String) getGroup(groupPosition);
        if(convertView == null){
            LayoutInflater layoutInflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=layoutInflater.inflate(R.layout.item_title_doi_hinh, null);
        }
        TextView tvTitleDoiHinh = (TextView) convertView.findViewById(R.id.tvTitleDoiHinh);
        tvTitleDoiHinh.setText(String.valueOf(expandleListTitleText));
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        final CauThu_DoiHinh expandleListText = (CauThu_DoiHinh) getChild(groupPosition, childPosition);
        if(convertView == null){
            LayoutInflater layoutInflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=layoutInflater.inflate(R.layout.item_doi_hinh, null);
        }
        TextView tvSoAo, tvTenCauThu, tvViTri;
        tvSoAo = (TextView) convertView.findViewById(R.id.tvSoAoDoiHinh);
        tvTenCauThu = (TextView) convertView.findViewById(R.id.tvTenCCauThu);
        tvViTri = (TextView) convertView.findViewById(R.id.tvViTri);
        tvSoAo.setText(String.valueOf(expandleListText.getSoAo()));
        tvTenCauThu.setText(String.valueOf(expandleListText.getTenCauThu()));
        tvViTri.setText(String.valueOf(expandleListText.getViTri()));
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
