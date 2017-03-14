package maffre.com.outerspacemanager.outerspacemanager;

import java.util.ArrayList;

/**
 * Created by mac2 on 14/03/2017.
 */

public class Ships {



    public ArrayList<Ship> getShips() {
        return ships;
    }

    public Ships(ArrayList<Ship> ships) {
        this.ships = ships;
    }

    private ArrayList<Ship> ships;

    public int arraySize() {
        return ships.size();
    }



}
