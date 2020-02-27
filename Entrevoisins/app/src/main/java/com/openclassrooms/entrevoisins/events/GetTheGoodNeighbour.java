package com.openclassrooms.entrevoisins.events;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.openclassrooms.entrevoisins.model.Neighbour;

public class GetTheGoodNeighbour  {

    private static final String KEY_POSITION = "position" ;
    public int position;
    public Neighbour neighbour;
    /**
     * Constructor.
     * @param neighbour
     */

    public GetTheGoodNeighbour (Neighbour neighbour, int position) {

       this.neighbour = neighbour;
       this.position = position;


    }

}
