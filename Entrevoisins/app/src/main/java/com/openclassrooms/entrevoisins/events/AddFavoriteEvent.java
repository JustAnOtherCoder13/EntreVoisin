package com.openclassrooms.entrevoisins.events;

import com.openclassrooms.entrevoisins.model.Neighbour;

public class AddFavoriteEvent {

        public Neighbour neighbour;
        public int position;

        public AddFavoriteEvent(Neighbour neighbour, int position) {
            this.neighbour = neighbour;
            this.position = position;

        }

}
