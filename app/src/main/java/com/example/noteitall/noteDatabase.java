package com.example.noteitall;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class noteDatabase extends SQLiteOpenHelper {
//declares database details
    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NAME = "notedb";
    private static final String DATABASE_TABLE = "notestable";

    //collumn names for database table
    private static final String KEY_ID = "id";
    private static final String KEY_TITLE = "title";
    private static final String KEY_IMPORTANCE = "importance";
    private static final String KEY_TYPE = "type";
    private static final String KEY_CONTENT = "content";
    private static final String KEY_FAVOURITE = "favourite";
    private static final String KEY_DATE = "date";
    private static final String KEY_TIME = "time";
        //starts database connection
    noteDatabase(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {   //creates the table.
        String Query = "CREATE TABLE "+ DATABASE_TABLE + "(" + KEY_ID+" INT PRIMARY KEY,"+
                KEY_TITLE + " TEXT,"+
                KEY_IMPORTANCE + " INT,"+
                KEY_TYPE + " INT,"+
                KEY_CONTENT + "TEXT,"+
                KEY_FAVOURITE + "TEXT, "+
                KEY_DATE +"DATE " +
                KEY_TIME + "TIME"+")";
        db.execSQL(Query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion >= newVersion)   //drops and updates table if a new version exists
            return;
        db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
        onCreate(db);
    }
    public long addNote(Note note){
        //gets database connection
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues c = new ContentValues();
        //stores table data
        c.put(KEY_TITLE,note.getTitle());
        c.put(KEY_CONTENT,note.getContent());
        c.put(KEY_IMPORTANCE,note.getImportance());
        c.put(KEY_TYPE,note.getType());
        c.put(KEY_FAVOURITE,note.getFavourite());
        c.put(KEY_DATE,note.getDate());
        c.put(KEY_TIME,note.getTime());

        long id = db.insert(DATABASE_TABLE, null,c);
        Log.d("inserted","ID -> " + id);
        return id;  //returns ID for referencing
    }

    public Note getNote(long id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(DATABASE_TABLE, new String[]{KEY_ID,KEY_TITLE,KEY_CONTENT,KEY_IMPORTANCE,KEY_TYPE,KEY_FAVOURITE,KEY_DATE,KEY_TIME}, KEY_ID+"=?",
                new String[]{String.valueOf(id)},null,null,null);
                //makes string array with id at beginning
        if (cursor != null){    //if cursor is empty take first result
            cursor.moveToFirst();
        }
        //gets all data from cursors ID.
        return new Note(cursor.getLong(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),
                cursor.getString(4),cursor.getString(5),cursor.getString(6), cursor.getString(7));
    }
   public List<Note> getNotes(){
       SQLiteDatabase db = this.getReadableDatabase();
       List<Note> allNotes = new ArrayList<>();

       String query = "SELECT * FROM " + DATABASE_TABLE;
       Cursor cursor = db.rawQuery(query,null);
       if(cursor.moveToFirst()){
           do{  //calls note activity for all notes data
                Note note = new Note();
                note.setID(cursor.getLong(0));
                note.setTitle(cursor.getString(1));
                note.setContent(cursor.getString(2));
                note.setImportance(cursor.getString(3));
                note.setType(cursor.getString(4));
                note.setFavourite(cursor.getString(5));
                note.setDate(cursor.getString(6));
                note.setTime(cursor.getString(7));

                allNotes.add(note);
           }while(cursor.moveToNext());
       }

       return allNotes;
    }
}
