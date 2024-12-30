package com.example.gplxhanga.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.gplxhanga.Tab1Fragment;
import com.example.gplxhanga.Tab2Fragment;
import com.example.gplxhanga.Tab3Fragment;
import com.example.gplxhanga.Tab4Fragment;
import com.example.gplxhanga.Tab5Fragment;
import android.os.Bundle;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    private final Bundle[] bundles;

    public ViewPagerAdapter(@NonNull FragmentManager fm, int behavior, Bundle[] bundles) {
        super(fm, behavior);
        this.bundles = bundles;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        Fragment fragment;

        switch (position) {
            case 1:
                fragment = new Tab2Fragment();
                break;
            case 2:
                fragment = new Tab3Fragment();
                break;
            case 3:
                fragment = new Tab4Fragment();
                break;
            case 4:
                fragment = new Tab5Fragment();
                break;
            default:
                fragment = new Tab1Fragment();
                break;
        }

        // Truyền Bundle vào Fragment
        if (bundles != null && bundles.length > position && bundles[position] != null) {
            fragment.setArguments(bundles[position]);
        }

        return fragment;
    }

    @Override
    public int getCount() {
        return 5;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String title = "";
        switch (position) {
            case 0:
                title = "Biển báo cấm";
                break;
            case 1:
                title = "Biển hiệu lệnh";
                break;
            case 2:
                title = "Biển chỉ dẫn";
                break;
            case 3:
                title = "Biển báo nguy hiểm và cảnh báo";
                break;
            case 4:
                title = "Biển phụ";
                break;
        }
        return title;
    }
}
