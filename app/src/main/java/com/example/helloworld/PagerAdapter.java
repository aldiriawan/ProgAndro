package com.example.helloworld;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class PagerAdapter extends FragmentStatePagerAdapter {
    int NofTabs;

    public PagerAdapter(FragmentManager fm, int NumOfTabs){
        super(fm);
        this.NofTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                Chat chat = new Chat();
                return chat;

            case 1:
                Status status = new Status();
                return status;

            case 2:
                Calls calls = new Calls();
                return calls;
            default:
            return null;
        }
    }

    @Override
    public int getCount() {
        return 3;
    }
}
