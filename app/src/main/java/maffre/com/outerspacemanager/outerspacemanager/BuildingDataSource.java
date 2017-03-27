package maffre.com.outerspacemanager.outerspacemanager;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

/**
 * Created by mac2 on 20/03/2017.
 */

public class BuildingDataSource {
    public String test;
    // Database fields
    private SQLiteDatabase database;
    private ApplicationDB dbHelper;
    private String[] allColumns = { ApplicationDB.KEY_ID,ApplicationDB.KEY_NAME, ApplicationDB.KEY_LEVEL, ApplicationDB.KEY_timeToBuildByLevel, ApplicationDB.KEY_timeToBuildLevel0, ApplicationDB.KEY_currentDate};


    public BuildingDataSource(Context context) {
        dbHelper = new ApplicationDB(context);
    }
    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }
    public void close() {
        dbHelper.close();
    }

    public void deleteBuilding(BuildingDB building) {
        int id = building.getId();
        database.delete(ApplicationDB.BUILDING_TABLE, ApplicationDB.KEY_ID
                + " = \"" + id+"\"", null);
    }




    public HashMap<Integer, BuildingDB> getBuildings() {
        HashMap<Integer, BuildingDB> buildingList = new HashMap<>();
        Cursor cursor = database.query(ApplicationDB.BUILDING_TABLE,
                allColumns, null,null, null, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            BuildingDB myBuilding = cursorToBuilding(cursor);
            buildingList.put(myBuilding.getId(), myBuilding);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return buildingList;
    }

    private BuildingDB cursorToBuilding(Cursor cursor) {
        BuildingDB current = new BuildingDB();


        current.setName(cursor.getString(1));
        current.setLevel(cursor.getInt(2));
        current.setTimeToBuildByLevel(cursor.getInt(3));
        current.setTimeToBuildLevel0(cursor.getInt(4));
        current.setCurrentDate(cursor.getLong(5));

        return current;
    }


    public BuildingDB createBuilding(String name, int level, long timeToBuildByLevel, long timeToBuildLevel0, long currentDate, int buildingId) {
        ContentValues values = new ContentValues();

        values.put(ApplicationDB.KEY_NAME, name);
        values.put(ApplicationDB.KEY_LEVEL, level);
        values.put(ApplicationDB.KEY_timeToBuildByLevel, timeToBuildByLevel);
        values.put(ApplicationDB.KEY_timeToBuildLevel0, timeToBuildLevel0);
        values.put(ApplicationDB.KEY_currentDate, currentDate);

        values.put(ApplicationDB.KEY_ID, buildingId);


        database.insert(ApplicationDB.BUILDING_TABLE, null,
                values);
        Cursor cursor = database.query(ApplicationDB.BUILDING_TABLE,
                allColumns, ApplicationDB.KEY_ID + " = \"" + buildingId+"\"", null,
                null, null, null);
        cursor.moveToFirst();
        BuildingDB newBuilding = cursorToBuilding(cursor);
        cursor.close();
        return newBuilding;
    }

}
