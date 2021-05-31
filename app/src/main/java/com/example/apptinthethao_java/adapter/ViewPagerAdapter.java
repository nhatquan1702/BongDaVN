package com.example.apptinthethao_java.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.apptinthethao_java.fragment.TinChuyenNhuongFragment;
import com.example.apptinthethao_java.fragment.TinMoiFragment;
import com.example.apptinthethao_java.fragment.TinNongFragment;
import com.example.apptinthethao_java.fragment.TinPhoBienFragment;

public class ViewPagerAdapter extends FragmentStateAdapter {
    public ViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }
    @NonNull
    @Override
    public Fragment createFragment(int position) {
        Fragment fragment = null;
        if (position == 0)
        {
            fragment = new TinMoiFragment();
        }
        else if (position == 1)
        {
            fragment = new TinNongFragment();
        }
        else if (position == 2)
        {
            fragment = new TinPhoBienFragment();
        }
        else if (position == 3)
        {
            fragment = new TinChuyenNhuongFragment();
        }
        return fragment;
    }

    @Override
    public int getItemCount() {
        return 4;
    }

}
