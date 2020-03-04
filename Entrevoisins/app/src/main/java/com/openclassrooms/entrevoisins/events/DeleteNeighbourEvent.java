package com.openclassrooms.entrevoisins.events;

import com.openclassrooms.entrevoisins.model.Neighbour;

/**
 * Event fired when a user deletes a Neighbour
 */
public class DeleteNeighbourEvent {

    /**
     * Neighbour to delete
     */
    public Neighbour neighbour;
    public int position;

    /**
     * Constructor.
     * @param neighbour
     */
    public DeleteNeighbourEvent(Neighbour neighbour, int position) {

        this.position = position;
        this.neighbour = neighbour;
    }


}
