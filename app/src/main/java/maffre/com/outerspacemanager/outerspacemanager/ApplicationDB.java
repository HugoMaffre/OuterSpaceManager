package maffre.com.outerspacemanager.outerspacemanager;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;

/**
 * Created by mac2 on 20/03/2017.
 */

public class ApplicationDB extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 4;
    private static final String DATABASE_NAME = "BuildingsDb";
    public static final String BUILDING_TABLE = "Building";
    public static final String KEY_ID = "id";
    public static final String KEY_NAME = "name";
    public static final String KEY_LEVEL = "level";
    public static final String KEY_timeToBuildByLevel = "timeToBuildByLevel";
    public static final String KEY_timeToBuildLevel0 = "timeToBuildLevel0";
    public static final String KEY_currentDate = "currentDate";


    private static final String BUILDING_TABLE_CREATE = "CREATE TABLE " + BUILDING_TABLE + " (" + KEY_ID + " INT, " + KEY_NAME + " TEXT, " + KEY_LEVEL + " INT, " + KEY_timeToBuildByLevel + " REAL, "+ KEY_timeToBuildLevel0 + " REAL, " + KEY_currentDate + " REAL );" ;




    public ApplicationDB(Context context) {
        super(context, Environment.getExternalStorageDirectory()+"/"+DATABASE_NAME, null, DATABASE_VERSION);
    }



    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(BUILDING_TABLE_CREATE);
    }



    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {db.execSQL("DROP TABLE IF EXISTS " +
            BUILDING_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + BUILDING_TABLE);
        onCreate(db);
    }
}