package org.justyand.cuttingtool;

import android.content.Context;
import android.content.res.Resources;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.app.Activity;

/**
 * Created by justy on 16.10.2016.
 */



public class TabsPagerAdapter extends FragmentStatePagerAdapter {
    public TabsPagerAdapter(FragmentManager fm) {
        super(fm);


    }

    private Context mContext = null;

    @Override
    public Fragment getItem(int position) {

        Fragment frag=null;
        switch (position){
            case 0:
                frag=new calc_class();
                break;
            case 1:
                frag=new predefined_class();
                break;
        }
        return frag;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        String title=" ";
        switch (position){
            case 0:
                title= "Calc";
                break;
            case 1:
                title= "Predef";
                break;
        }

        return title;
    }

}
