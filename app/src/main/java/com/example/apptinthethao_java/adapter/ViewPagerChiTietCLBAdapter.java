package com.example.apptinthethao_java.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.apptinthethao_java.fragment.ListCauThuFragment;
import com.example.apptinthethao_java.fragment.TongQuanCLBFragment;

public class ViewPagerChiTietCLBAdapter extends FragmentStateAdapter {
    public ViewPagerChiTietCLBAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }
    @NonNull
    @Override
    public Fragment createFragment(int position) {
        Fragment fragment = null;
        if (position == 0)
        {
            fragment = new TongQuanCLBFragment();
        }
        else if (position == 1)
        {
            fragment = new ListCauThuFragment();
        }
        return fragment;
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
