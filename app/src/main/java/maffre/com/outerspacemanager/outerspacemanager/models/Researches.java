package maffre.com.outerspacemanager.outerspacemanager.models;

import java.util.ArrayList;

import maffre.com.outerspacemanager.outerspacemanager.models.Research;

/**
 * Created by mac2 on 20/03/2017.
 */

public class Researches {

    public ArrayList<Research> getResearches() {
        return searches;
    }

    public Researches(ArrayList<Research> researches) {
        this.searches = searches;
    }

    private ArrayList<Research> searches;

    public int arraySize() {
        return searches.size();
    }

}
