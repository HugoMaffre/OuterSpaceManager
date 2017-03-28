package maffre.com.outerspacemanager.outerspacemanager.models;

import java.util.ArrayList;

import maffre.com.outerspacemanager.outerspacemanager.models.Building;

/**
 * Created by mac2 on 07/03/2017.
 */

public class Buildings {

    public ArrayList<Building> getBuildings() {
        return buildings;
    }

    public Buildings(ArrayList<Building> buildings) {
        this.buildings = buildings;
    }

    private ArrayList<Building> buildings;

    public int arraySize() {
        return buildings.size();
    }

}
