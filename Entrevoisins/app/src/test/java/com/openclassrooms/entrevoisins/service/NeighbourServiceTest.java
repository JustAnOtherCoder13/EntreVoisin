package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.model.Neighbour;

import org.hamcrest.collection.IsIterableContainingInAnyOrder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.List;
import java.util.Objects;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * Unit test on Neighbour service
 */
@RunWith(JUnit4.class)
public class NeighbourServiceTest {

    private NeighbourApiService service;

    @Before
    public void setup() {
        List<Neighbour> neighbours = DummyNeighbourGenerator.generateNeighbours();
        List<Neighbour> favorites = DummyNeighbourGenerator.generateFavorites();

          service = new NeighbourApiService() {
            @Override
            public List<Neighbour> getNeighbours() { return neighbours; }
            @Override
            public List<Neighbour> getFavorites() { return favorites; }
            @Override
            public void deleteNeighbour(Neighbour neighbour) { neighbours.remove(neighbour); }
            @Override
            public void deleteFavorite(Neighbour neighbour) { favorites.remove(neighbour); }
            @Override
            public void addFavorite(Neighbour neighbour) { favorites.add(neighbour); }
        };
    }


    @Test
    public void getNeighboursWithSuccess() {
        List<Neighbour> neighbours = service.getNeighbours();
        List<Neighbour> expectedNeighbours = DummyNeighbourGenerator.DUMMY_NEIGHBOURS;
        assertThat(neighbours, IsIterableContainingInAnyOrder.containsInAnyOrder(Objects.requireNonNull(expectedNeighbours.toArray())));
    }

    @Test
    public void getFavoritesWithSuccess() {
        List<Neighbour> favorites = service.getFavorites();
        List<Neighbour> expectedFavorites = DummyNeighbourGenerator.DUMMY_FAVORITE_NEIGHBOURS;
        assertThat(favorites, IsIterableContainingInAnyOrder.containsInAnyOrder(Objects.requireNonNull(expectedFavorites.toArray())));
    }

    @Test
    public void deleteNeighbourWithSuccess() {

        Neighbour neighbourToDelete = service.getNeighbours().get(0);
        service.deleteNeighbour(neighbourToDelete);
        assertFalse(service.getNeighbours().contains(neighbourToDelete));
    }

    @Test
    public void deleteFavoriteWithSuccess() {
        Neighbour favoriteToDelete = service.getFavorites().get(0);
        service.deleteFavorite(favoriteToDelete);
        assertFalse(service.getFavorites().contains(favoriteToDelete));
    }

    @Test
    public void addFavoriteWithSuccess() {
        Neighbour neighbourToAddInFavorite = service.getNeighbours().get(2);
        service.addFavorite(neighbourToAddInFavorite);
        assertTrue(service.getFavorites().contains(neighbourToAddInFavorite));

    }
}
