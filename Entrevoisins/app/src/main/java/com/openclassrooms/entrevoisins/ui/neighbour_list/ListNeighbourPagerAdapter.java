package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;


public  class ListNeighbourPagerAdapter extends FragmentPagerAdapter  {

    private Fragment frag;

    private int position;
    private Bundle extras;






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


        return mFragToUse(frag);
    }

    /**
     * get the number of pages
     * @return
     */
    @Override
    public int getCount() {
        return 1;
    }


 private Fragment mFragToUse (Fragment fragment){
    // extras = NeighbourFragment.newInstance().getArguments();

     /*if (extras != null){
         position = extras.getInt(NeighbourFragment.KEY_POSITION, -1);
     }*/
     if (position>0) {

         Log.e(getClass().getSimpleName(), "onCreateView called for fragment number "+extras);

         frag = NeighbourFragmentDetail.newInstance();
     }
     else {
         frag = NeighbourFragment.newInstance();
         Log.e(getClass().getSimpleName(), "onCreateView called for fragment number "+extras);

     }
        return frag;
 }



}