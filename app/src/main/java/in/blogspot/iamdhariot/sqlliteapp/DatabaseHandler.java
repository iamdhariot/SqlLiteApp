package in.blogspot.iamdhariot.sqlliteapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Dhariot on 07-Feb-18.
 */

public class DatabaseHandler extends SQLiteOpenHelper {
    // static variables
    // database version
    private static final int DATABASE_VERSION = 1;
    // database name
    private static final String DATABASE_NAME = "contactManager";

    // table name contacts
    private static final String TABLE_CONTACTS = "contacts";

    // contacts table Column names
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_MOBILE_NO="mobile_number";


    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // creating the contacts table
    @Override
    public void onCreate(SQLiteDatabase db) {
        // query for create table

        String CREATE_CONTACTS_TABLE="CREATE TABLE "+TABLE_CONTACTS+"("+KEY_ID+"INTEGER PRIMARY KEY,"+KEY_NAME+"TEXT,"+KEY_MOBILE_NO+"TEXT"+")";
        db.execSQL(CREATE_CONTACTS_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
