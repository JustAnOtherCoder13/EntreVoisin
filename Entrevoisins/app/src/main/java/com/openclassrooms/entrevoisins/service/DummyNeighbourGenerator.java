package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.model.Neighbour;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

abstract class DummyNeighbourGenerator {

    static List<Neighbour> DUMMY_NEIGHBOURS = Arrays.asList(
            new Neighbour(1, "Caroline", "http://i.pravatar.cc/150?u=a042581f4e29026704d", "13800 Istres", "+33 6 ", "www.facebook.fr/", "",true),
            new Neighbour(2, "Jack", "http://i.pravatar.cc/150?u=a042581f4e29026704e", "13580 La Fare-les-Oliviers", "+33 6 ", "www.facebook.fr/", "",true),
            new Neighbour(3, "Chloé", "http://i.pravatar.cc/150?u=a042581f4e29026704f", "13250 Saint-Chamas", "+33 6 ", "www.facebook.fr/", "",false),
            new Neighbour(4, "Vincent", "http://i.pravatar.cc/150?u=a042581f4e29026704a", "13250 Saint-Chamas", "+33 6 ", "www.facebook.fr/", "",false),
            new Neighbour(5, "Elodie", "http://i.pravatar.cc/150?u=a042581f4e29026704b", "13300 Salon-de-Provence", "+33 6 ", "www.facebook.fr/", "",false),
            new Neighbour(6, "Sylvain", "http://i.pravatar.cc/150?u=a042581f4e29026704c", "13500 Martigues", "+33 6 ", "www.facebook.fr/", "",false),
            new Neighbour(7, "Laetitia", "http://i.pravatar.cc/150?u=a042581f4e29026703d", "13340 Rognac", "+33 6 ", "www.facebook.fr/", "",false),
            new Neighbour(8, "Dan", "http://i.pravatar.cc/150?u=a042581f4e29026703b", "13030 Berre-l'Étang", "+33 6 ", "www.facebook.fr/", "",false),
            new Neighbour(9, "Joseph", "http://i.pravatar.cc/150?u=a042581f4e29026704d", "13800 Istres", "+33 6 ", "www.facebook.fr/", "",false),
            new Neighbour(10, "Emma", "http://i.pravatar.cc/150?u=a042581f4e29026706d", "13140 Miramas", "+33 6 ", "www.facebook.fr/", "",false),
            new Neighbour(11, "Patrick", "http://i.pravatar.cc/150?u=a042581f4e29026702d", "13100 Aix-en-Provence", "+33 6 ", "www.facebook.fr/", "",false),
            new Neighbour(12, "Ludovic", "http://i.pravatar.cc/150?u=a042581f3e39026702d", "13800 Istres", "+33 6 ", "www.facebook.fr/", "",false)
    );

    static List<Neighbour> generateNeighbours() {
        return new ArrayList<>(DUMMY_NEIGHBOURS);
    }

    //add an empty neighbour's list.
    static List<Neighbour> DUMMY_FAVORITE_NEIGHBOURS = Arrays.asList(
            new Neighbour(1, "Caroline", "http://i.pravatar.cc/150?u=a042581f4e29026704d", "13800 Istres", "+33 6 ", "www.facebook.fr/", "",true),
            new Neighbour(2, "Jack", "http://i.pravatar.cc/150?u=a042581f4e29026704e", "13580 La Fare-les-Oliviers", "+33 6 ", "www.facebook.fr/", "",true)
    );

    //return the list in ArrayList to perform changes.
    static List<Neighbour> generateFavorites() { return new ArrayList<>(DUMMY_FAVORITE_NEIGHBOURS); }
}
