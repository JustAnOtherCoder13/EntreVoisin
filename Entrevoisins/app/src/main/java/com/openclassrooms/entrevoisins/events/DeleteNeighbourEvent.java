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
    public int neighbourId;
    /**
     * Constructor.
     * @param neighbour
     */
    public DeleteNeighbourEvent(Neighbour neighbour, int position, int neighbourId) {

        this.position = position;
        this.neighbour = neighbour;
        this.neighbourId = neighbourId;
    }
}
