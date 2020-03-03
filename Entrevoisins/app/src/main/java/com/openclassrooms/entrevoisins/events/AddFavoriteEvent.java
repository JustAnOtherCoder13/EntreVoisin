package com.openclassrooms.entrevoisins.events;

import com.openclassrooms.entrevoisins.model.Neighbour;
/**
 * Event fired when a user add a Favorite
 */

public class AddFavoriteEvent {




        /**
         * Favorite to add
         */
        public Neighbour neighbour;


        /**
         * Constructor.
         * @param neighbour
         */
        public AddFavoriteEvent(Neighbour neighbour) {
            this.neighbour = neighbour;

        }




}
