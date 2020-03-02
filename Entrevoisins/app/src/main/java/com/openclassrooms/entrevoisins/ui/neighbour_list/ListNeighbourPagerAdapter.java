package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.DummyNeighbourApiService;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;

import java.util.List;


public  class ListNeighbourPagerAdapter extends FragmentPagerAdapter  {

   NeighbourApiService mApiService = DI.getNeighbourApiService();
    List<Neighbour> mNeighbours;


    ListNeighbourPagerAdapter(FragmentManager fm) {
        super(fm);
    }
   // DummyNeighbourApiService switchPosition = DummyNeighbourApiService.class.getN

    /**
     * getItem is called to instantiate the fragment for the given page.
     * @param position
     * @return
     */
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0 :
                Log.i("test", "getItem: ");
                 return NeighbourFragment.newInstance();

            case 1 :
                Log.i("test", "getItem: ");

                return FavoriteFragment.newInstance();

            default:
                return null;
        }
    }

    /**
     * get the number of pages
     * @return
     */
    @Override
    public int getCount() {
        return 2;
    }
}