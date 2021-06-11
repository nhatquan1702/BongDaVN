package com.example.apptinthethao_java.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.apptinthethao_java.R;
import com.example.apptinthethao_java.model.User;

import java.util.ArrayList;

public class UserAdapter extends BaseAdapter {
    private ArrayList<User> listUser;

    public UserAdapter(ArrayList<User> listUser) {
        this.listUser = listUser;
    }

    @Override
    public int getCount() {
        return listUser.size();
    }

    @Override
    public Object getItem(int position) {
        return listUser.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        if (convertView == null) {
            view = View.inflate(parent.getContext(), R.layout.item_list_user, null);
        } else view = convertView;

        ((TextView)view.findViewById(R.id.tvEmail)).setText(listUser.get(position).getAccount_email());
        String role = "";
        if(listUser.get(position).getRole() == 1){
            role = "Quản Trị Viên";
        }
        else{
            role = "Người Dùng";
        }
        ((TextView)view.findViewById(R.id.tvRole)).setText(role);

        return view;
    }
}
