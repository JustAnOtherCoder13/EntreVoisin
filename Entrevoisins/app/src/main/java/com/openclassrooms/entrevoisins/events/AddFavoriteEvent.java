package com.openclassrooms.entrevoisins.events;

import com.openclassrooms.entrevoisins.model.Neighbour;

public class AddFavoriteEvent {
        public Neighbour neighbour;

        public AddFavoriteEvent(Neighbour neighbour) { this.neighbour = neighbour; }
}
