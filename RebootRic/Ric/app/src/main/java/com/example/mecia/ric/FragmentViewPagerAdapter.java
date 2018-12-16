package com.example.mecia.ric;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class FragmentViewPagerAdapter extends FragmentStatePagerAdapter {
    int mNoOfTabs;

    public FragmentViewPagerAdapter(FragmentManager fragmentManager, int NumberOfTabs){
        super(fragmentManager);
        this.mNoOfTabs = NumberOfTabs;
    }

    public FragmentViewPagerAdapter(FragmentManager supportFragmentManager) {
        super(supportFragmentManager);
    }

    @Override
    public Fragment getItem(int i) {
        switch(i) {

            case 0:
                return new ProfissaoFragment();
            case 1:
                return new AcademicoFragment();
            case 2:
                return new ProjetoFragment();

        }
        return null;
    }

    @Override
    public int getCount() {
        return mNoOfTabs;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return "Profissão";
            case 1:
                return "Acadêmico";
            case 2:
                return "Projetos";
        }

        return super.getPageTitle(position);
    }
}
