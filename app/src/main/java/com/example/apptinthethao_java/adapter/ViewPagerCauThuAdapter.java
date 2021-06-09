package com.example.apptinthethao_java.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.apptinthethao_java.fragment.ListCauThuFragment;
import com.example.apptinthethao_java.fragment.PhongDoCauThuFragment;
import com.example.apptinthethao_java.fragment.TongQuanCLBFragment;
import com.example.apptinthethao_java.fragment.TongQuanCauThuFragment;

public class ViewPagerCauThuAdapter extends FragmentStateAdapter {
    public ViewPagerCauThuAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }
    @NonNull
    @Override
    public Fragment createFragment(int position) {
        Fragment fragment = null;
        if (position == 0)
        {
            fragment = new TongQuanCauThuFragment();
        }
        else if (position == 1)
        {
            fragment = new PhongDoCauThuFragment();
        }
        return fragment;
    }

    @Override
    public int getItemCount() {
        return 2;
    }

}
