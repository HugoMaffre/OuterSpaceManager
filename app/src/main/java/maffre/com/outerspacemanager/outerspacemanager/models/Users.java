package maffre.com.outerspacemanager.outerspacemanager.models;

import java.util.ArrayList;

/**
 * Created by mac2 on 14/03/2017.
 */

public class Users {

    public ArrayList<User> getUsers() {
        return users;
    }

    public Users(ArrayList<Building> buildings) {
        this.users = users;
    }

    private ArrayList<User> users;

    public int arraySize() {
        return users.size();
    }

}
