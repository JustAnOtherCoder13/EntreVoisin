package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.model.Neighbour;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

abstract class DummyNeighbourGenerator {

    static List<Neighbour> DUMMY_NEIGHBOURS = Arrays.asList(
            new Neighbour(101, "Caroline", "http://i.pravatar.cc/150?u=a042581f4e29026704d", "13800 Istres", "+33 6 ", "www.facebook.fr/", "",false),
            new Neighbour(102, "Jack", "http://i.pravatar.cc/150?u=a042581f4e29026704e", "13580 La Fare-les-Oliviers", "+33 6 ", "www.facebook.fr/", "",false),
            new Neighbour(103, "Chloé", "http://i.pravatar.cc/150?u=a042581f4e29026704f", "13250 Saint-Chamas", "+33 6 ", "www.facebook.fr/", "",false),
            new Neighbour(104, "Vincent", "http://i.pravatar.cc/150?u=a042581f4e29026704a", "13250 Saint-Chamas", "+33 6 ", "www.facebook.fr/", "",false),
            new Neighbour(105, "Elodie", "http://i.pravatar.cc/150?u=a042581f4e29026704b", "13300 Salon-de-Provence", "+33 6 ", "www.facebook.fr/", "",false),
            new Neighbour(106, "Sylvain", "http://i.pravatar.cc/150?u=a042581f4e29026704c", "13500 Martigues", "+33 6 ", "www.facebook.fr/", "",false),
            new Neighbour(107, "Laetitia", "http://i.pravatar.cc/150?u=a042581f4e29026703d", "13340 Rognac", "+33 6 ", "www.facebook.fr/", "",false),
            new Neighbour(108, "Dan", "http://i.pravatar.cc/150?u=a042581f4e29026703b", "13030 Berre-l'Étang", "+33 6 ", "www.facebook.fr/", "",false),
            new Neighbour(109, "Joseph", "http://i.pravatar.cc/150?u=a042581f4e29026704d", "13800 Istres", "+33 6 ", "www.facebook.fr/", "",false),
            new Neighbour(110, "Emma", "http://i.pravatar.cc/150?u=a042581f4e29026706d", "13140 Miramas", "+33 6 ", "www.facebook.fr/", "",false),
            new Neighbour(111, "Patrick", "http://i.pravatar.cc/150?u=a042581f4e29026702d", "13100 Aix-en-Provence", "+33 6 ", "www.facebook.fr/", "",false),
            new Neighbour(112, "Ludovic", "http://i.pravatar.cc/150?u=a042581f3e39026702d", "13800 Istres", "+33 6 ", "www.facebook.fr/", "",false)
    );

    static List<Neighbour> generateNeighbours() {
        return new ArrayList<>(DUMMY_NEIGHBOURS);
    }

    //add an empty neighbour's list.
    static List<Neighbour> DUMMY_FAVORITE_NEIGHBOURS = Arrays.asList();

    //return the list in ArrayList to perform changes.
    static List<Neighbour> generateFavorite() {
        return new ArrayList<>(DUMMY_FAVORITE_NEIGHBOURS);
    }
}
