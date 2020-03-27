package com.openclassrooms.entrevoisins.events;

import android.support.design.widget.FloatingActionButton;

import com.openclassrooms.entrevoisins.model.Neighbour;

public class AddFavoriteEvent {
        public Neighbour neighbour;
        public FloatingActionButton favoriteFab;

        public AddFavoriteEvent(Neighbour neighbour, FloatingActionButton favoriteFab) { this.neighbour = neighbour; this.favoriteFab = favoriteFab; }
}
