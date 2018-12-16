package io.github.mobileteacher.imagestoragesample;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class FragmentsAdapter extends FragmentPagerAdapter {
    final int numFragments = 3;

    public FragmentsAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new ImageListFragment();
            case 1:
                return new TextFragment();
            case 2:
                return new ImageFragment();
        }
        return null;
    }

    @Override
    public int getCount() {
        return numFragments;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return "Lista de fotos";
            case 1:
                return "Texto";
            case 2:
                return "Imagem";
        }
        return "XABU";
    }
}
