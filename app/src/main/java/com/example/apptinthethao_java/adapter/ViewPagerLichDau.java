package com.example.apptinthethao_java.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.apptinthethao_java.fragment.LichDaDauFragment;
import com.example.apptinthethao_java.fragment.LichSapDauFragment;

import org.jetbrains.annotations.NotNull;

public class ViewPagerLichDau  extends FragmentStateAdapter {
    public ViewPagerLichDau(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }
    @NotNull
    @Override
    public Fragment createFragment(int position) {
        Fragment fragment = null;
        if(position == 0) {
            fragment = new LichDaDauFragment();
        }
        else if(position == 1) {
            fragment = new LichSapDauFragment();
        }
        return fragment;
    }

    @Override
    public int getItemCount() {
        return 2;
    }

}
