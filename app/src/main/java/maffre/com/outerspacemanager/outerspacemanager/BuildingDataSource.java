package maffre.com.outerspacemanager.outerspacemanager;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by mac2 on 20/03/2017.
 */

public class BuildingDataSource {

    // Database fields
    private SQLiteDatabase database;
    private ApplicationDB dbHelper;
    private String[] allColumns = { ApplicationDB.KEY_ID,ApplicationDB.KEY_NAME };


    public BuildingDataSource(Context context) {
        dbHelper = new ApplicationDB(context);
    }
    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }
    public void close() {
        dbHelper.close();
    }

}
