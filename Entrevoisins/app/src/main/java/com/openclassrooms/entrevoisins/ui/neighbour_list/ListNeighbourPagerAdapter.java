package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;


public  class ListNeighbourPagerAdapter extends FragmentPagerAdapter  {

    private int position;

    public ListNeighbourPagerAdapter(FragmentManager fm, int position) {

        super(fm);
        this.position = position;
    }

    /**
     * getItem is called to instantiate the fragment for the given page.
     * @param position
     * @return
     */

    //here to chose witch fragment is used
    @Override
    public Fragment getItem(int position) {
        return NeighbourFragment.newInstance();
    }

    /**
     * get the number of pages
     * @return
     */
    @Override
    public int getCount() {
        return 1;
    }
}