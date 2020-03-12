package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class ListNeighbourPagerAdapter extends FragmentPagerAdapter {

    ListNeighbourPagerAdapter(FragmentManager fm) {
        super(fm);
    }
    /**
     * getItem is called to instantiate the fragment for the given page.
     *
     * @param position
     * @return
     */
    @Override
    public Fragment getItem(int position) {
        //switch the position parameter and return an int to identifying the page.
        switch (position) {
            case 0: return NeighbourFragment.newInstance(0);
            case 1: return NeighbourFragment.newInstance(1);
            default: return null;
        }
    }
    /**
     * get the number of pages
     *
     * @return
     */
    @Override
    //change the number of page to show.
    public int getCount() {
        return 2;
    }
}