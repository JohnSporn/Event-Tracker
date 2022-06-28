package com.example.eventtrackingapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class UserDatabase extends SQLiteOpenHelper {

    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "user.db";

    private static UserDatabase userDB;

    public static UserDatabase getInstance(Context context) {
        if (userDB == null) {
            userDB = new UserDatabase(context);
        }
        return userDB;
    }

    public UserDatabase(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    private static final class UserTable {
        private static final String TABLE = "Users";
        private static final String COL_ID = "_id";
        private static final String COL_NAME = "name";
        private static final String COL_EMAIL = "email";
        private static final String COL_PHONE_NUMBER = "phone_number";
        private static final String COL_USERNAME = "username";
        private static final String COL_PASSWORD = "password";
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        // Create User table
        sqLiteDatabase.execSQL("create table " + UserTable.TABLE + " (" +
                UserTable.COL_ID + " integer primary key autoincrement, " + UserTable.COL_NAME + ", "
                + UserTable.COL_EMAIL + ", " + UserTable.COL_PHONE_NUMBER + ", "
                + UserTable.COL_USERNAME + ", " + UserTable.COL_PASSWORD + ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    // Create user to database
    public boolean addUser(User user) {
        SQLiteDatabase mydb = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(UserTable.COL_NAME, user.getName());
        contentValues.put(UserTable.COL_EMAIL, user.getEmail());
        contentValues.put(UserTable.COL_PHONE_NUMBER, user.getPhoneNumber());
        contentValues.put(UserTable.COL_USERNAME, user.getUserName());
        contentValues.put(UserTable.COL_PASSWORD, user.getPassword());

        long insert = mydb.insert(UserTable.TABLE, null, contentValues);

        if(insert == -1) {
            return false;
        }
        else {
            return true;
        }
    }

    public boolean checkUserExists(User user) {
        SQLiteDatabase mydb = this.getWritableDatabase();
        Cursor cursor = mydb.rawQuery("select * from Users where username = ?", new String[] {user.getUserName(), user.getEmail()});

        if(cursor.getCount() > 0) {
            return true;
        } else {
            return false;
        }
    }

    // Reading all users from database
    public List<User> getAll() {
        List<User> userList = new ArrayList<>();

        // Getting data from database
        String query = "SELECT * FROM " + UserTable.TABLE;

        SQLiteDatabase mydb = this.getReadableDatabase();
        Cursor cursor = mydb.rawQuery(query, null);

        if(cursor.moveToFirst()) {
            //loop through cursor
            do {
                long userID = cursor.getLong(0);
                String name = cursor.getString(1);
                String email = cursor.getString(2);
                String phoneNumber = cursor.getString(3);
                String username = cursor.getString(4);
                String password = cursor.getString(5);

                User newUser = new User(userID, name, email, phoneNumber, username, password);
                userList.add(newUser);

            } while(cursor.moveToNext());
        }
        // close both cursor and database
        cursor.close();
        mydb.close();
        return userList;
    }

    // Delete user from database
    public void deleteUser(User user) {
        SQLiteDatabase mydb = this.getWritableDatabase();
        mydb.delete(UserTable.TABLE, UserTable.COL_ID + " = " + user.getuID(), null);
    }

}
