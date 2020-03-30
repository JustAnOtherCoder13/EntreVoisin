package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.model.Neighbour;

import java.util.List;

/**
 * Dummy mock for the Api
 */
public class DummyNeighbourApiService implements NeighbourApiService {


    private List<Neighbour> neighbours = DummyNeighbourGenerator.generateNeighbours();
    //declare favorites and initiate it to get the favorite list in the dummy generator
    private List<Neighbour> favorites = DummyNeighbourGenerator.generateFavorites();

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Neighbour> getNeighbours() {
        return neighbours;
    }

    //implement getFavorite to return favorites list
    @Override
    public List<Neighbour> getFavorites() {
        return favorites;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteNeighbour(Neighbour neighbour) { neighbours.remove(neighbour); }

    @Override
    public void deleteFavorite(Neighbour neighbour) {
        favorites.remove(neighbour);
    }

    @Override
    public void addFavorite(Neighbour neighbour) {
        favorites.add(neighbour);
    }
}
