package com.example.apptinthethao_java.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class CLBViewPagerAdapter extends FragmentStateAdapter {
    public CLBViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if(position == 0){
            return null;
        }
        else {
            return null;
        }
    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
