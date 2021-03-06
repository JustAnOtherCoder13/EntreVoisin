package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.model.Neighbour;

import java.util.List;
/**
 * Neighbour API client
 */
public interface NeighbourApiService {
    /**
     * Get all my Neighbours
     * @return {@link List}
     */
    List<Neighbour> getNeighbours();
            //add getFavorite method in the api
    /**
     * Get all my Favorite
     * @return {@link List}
     */
    List<Neighbour> getFavorites();
    /**
     * Deletes a neighbour
     * @param neighbour
     */
    void deleteNeighbour(Neighbour neighbour);
    /**
     * Deletes a favorite
     * @param neighbour
     */
    void deleteFavorite(Neighbour neighbour);
    /**
     * Add a neighbour in favorite
     * @param neighbour
     */
    void addFavorite(Neighbour neighbour);
}
