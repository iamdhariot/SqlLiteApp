package in.blogspot.iamdhariot.sqlliteapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dhariot on 07-Feb-18.
 */

public class DatabaseHandler extends SQLiteOpenHelper {
    // static variables
    // database version
    private static final int DATABASE_VERSION = 1;
    // database name
    private static final String DATABASE_NAME = "contactManager.db";

    // table name contacts
    private static final String TABLE_CONTACTS = "contacts";

    // contacts table Column names
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_MOBILE_NO="mobileNumber";


    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // creating the contacts table
    @Override
    public void onCreate(SQLiteDatabase db) {
        // query for create table

        String CREATE_CONTACTS_TABLE="CREATE TABLE "+TABLE_CONTACTS+"("+KEY_ID+" INTEGER PRIMARY KEY  AUTOINCREMENT, "+KEY_NAME+" TEXT,"+KEY_MOBILE_NO+" TEXT "+")";
        db.execSQL(CREATE_CONTACTS_TABLE);

    }

    // upgrading the database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_CONTACTS);
        //creating table again
        onCreate(db);


    }

    /**
     * Create, Read, Update, Delete Operations
     */

    //adding a new contact
    public void addContact(Contact contact){
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME,contact.getmName());
        values.put(KEY_MOBILE_NO,contact.getmMobileNo());
        // inserting row
        db.insert(TABLE_CONTACTS,null,values);
        db.close();

    }
    //getting a single contact
    Contact getContact(int id){
      SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_CONTACTS,new String[]{KEY_ID,KEY_NAME,KEY_MOBILE_NO},KEY_ID+"=?",new String[]{String.valueOf(id)},null,null,null,null);

        if(cursor!=null){
            cursor.moveToFirst();
        }
        Contact contact = new Contact(Integer.parseInt(cursor.getString(0)),cursor.getString(1),cursor.getString(2));
        return contact;
    }

    // getting all contacts
    public List<Contact>getAllContacts(){
        List<Contact> contactList=new ArrayList<Contact>();
        // select all query
        String selectQuery="SELECT * FROM "+TABLE_CONTACTS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery,null);
        // looping to all rows for adding it to list
        if(cursor.moveToFirst()){
            do{
                Contact contact = new Contact();
                contact.setmId(Integer.parseInt(cursor.getString(0)));
                contact.setmName((cursor.getString(1)));
                contact.setmMobileNo(cursor.getString(2));
                // add contacts to list
                contactList.add(contact);



            }while (cursor.moveToNext());
        }
        return contactList;
    }

    // update single contact
   public int updateContact(Contact  contact){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME,contact.getmName());
        values.put(KEY_MOBILE_NO,contact.getmMobileNo());
        //updating row
       return db.update(TABLE_CONTACTS,values,KEY_ID+"= ?",new String[]{String.valueOf(contact.getmId())});


    }

    //delete the single contact
    public void deleteContact(Contact contact){
       SQLiteDatabase db = this.getWritableDatabase();
       db.delete(TABLE_CONTACTS,KEY_ID+"= ?",new String[]{String.valueOf(contact.getmId())});
       db.close();

    }

    // getting contacts count
    public int getContactCount(){
        String countQuery = "SELECT * FROM "+TABLE_CONTACTS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor  cursor = db.rawQuery(countQuery,null);
        cursor.close();
        return  cursor.getCount();

    }

}
