package com.example.eventtrackingapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class EventDatabase extends SQLiteOpenHelper {

    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "events.db";

    private static EventDatabase eventsDB;

    public static EventDatabase getInstance(Context context) {
        if (eventsDB == null) {
            eventsDB = new EventDatabase(context);
        }
        return eventsDB;
    }

    public EventDatabase(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    private static final class EventTable {
        private static final String TABLE = "Event";
        private static final String COL_ID = "_id";
        private static final String COL_EVENT = "event";
        private static final String COL_DATE = "date";
        private static final String COL_INFO = "info";
    }

    // Called first time db is accessed
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table " + EventTable.TABLE + " (" +
                EventTable.COL_ID + " integer primary key autoincrement, " + EventTable.COL_EVENT + ", "
                + EventTable.COL_DATE + ", " + EventTable.COL_INFO + ")");

    }

    // Called when db version changes
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public boolean addEvent(Event event) {
        SQLiteDatabase mydb = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(EventTable.COL_EVENT, event.getName());
        contentValues.put(EventTable.COL_DATE, event.getDate());
        contentValues.put(EventTable.COL_INFO, event.getDescription());

        long insert = mydb.insert(EventTable.TABLE, null, contentValues);

        if(insert == -1) {
            return false;
        }
        else {
            return true;
        }
    }

    public List<Event> getAllEvents() {
        List<Event> returnList = new ArrayList<>();

        // Getting data from the database
        String queryString = "SELECT * FROM " + EventTable.TABLE;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(queryString, null);

        //Loop throug cursor (result set) and create new event object for each row.
        if(cursor.moveToFirst()) {

            do {
                long eventId = cursor.getLong(0);
                String eventName = cursor.getString(1);
                String eventDate = cursor.getString(2);
                String eventInfo = cursor.getString(3);

                Event event = new Event(eventId, eventName, eventDate, eventInfo);

                returnList.add(event);
            } while(cursor.moveToNext());

        } else {
            // Failed
        }
        cursor.close();
        db.close();
        return returnList;
    }

    public boolean deleteOne(Event event) {
        // Find event in db. delete and return true
        SQLiteDatabase db = this.getWritableDatabase();
        String queryString = "DELETE FROM " + EventTable.TABLE + " WHERE " + EventTable.COL_ID + " = " + event.getId();

        Cursor cursor = db.rawQuery(queryString, null);

        if(cursor.moveToFirst()) {
            return true;
        } else {
            return false;
        }

    }

}
