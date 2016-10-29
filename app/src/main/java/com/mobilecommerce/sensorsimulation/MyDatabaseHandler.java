/*
Authors: Venus Pathak - 7972526
         Shivjot Baidwan - 8028412
*/

package com.mobilecommerce.sensorsimulation;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class MyDatabaseHandler extends SQLiteOpenHelper {

    private static final int databaseVersion = 1;
    private static final String databaseName = "databaseForUserMovement.db";
    private static final String tableForUserMovement = "movement";

    public static final String columnId = "Id";
    public static final String columnStartTime = "startTime";
    public static final String columnActivityType = "activityType";

    private static String records[][]  = new String[100][2];


    public MyDatabaseHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
        // Constructor made for calling the constructor of super class SQLiteOpenHelper
        super(context,databaseName, factory, databaseVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase database){
        String CREATE_USER_MOVEMENT_TABLE_QUERY = "CREATE TABLE"+tableForUserMovement+"("+columnId+" TEXT PRIMARY KEY AUTOINCREMENT NOT NULL "+columnStartTime+" TEXT "+columnActivityType+" TEXT "+")";
        database.execSQL(CREATE_USER_MOVEMENT_TABLE_QUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion){
        database.execSQL("DROP TABLE IF EXISTS "+tableForUserMovement);
        onCreate(database);
    }

    public void addUserMovement(UserMovementDatabase userMovementDatabase, Context context)
    {
        try {
            //SQLiteDatabase database = this.getWritableDatabase();

            SQLiteDatabase database = context.openOrCreateDatabase(databaseName, Context.MODE_PRIVATE, null);
            ContentValues values = new ContentValues();
            //values.put(columnId, UserMovementDatabase.getId());
            values.put(columnStartTime, UserMovementDatabase.getStartTime());
            values.put(columnActivityType, UserMovementDatabase.getActivityType());

            database.insert(tableForUserMovement, null, values);
            database.close();
        }
        catch(Exception exception){
            Log.d("EXCEPTION IS: ",exception.toString());
        }


    }

    public void viewAllRecords()
    {
        int identifierToTrackRecordsArray=0;
        String select_query = "SELECT * FROM "+tableForUserMovement;
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery(select_query, null);
        UserMovementDatabase userMovementDatabase = new UserMovementDatabase();
        if(cursor.moveToFirst()){
            while(cursor.isAfterLast()==false){
                String startTime = cursor.getString(cursor.getColumnIndex(columnStartTime));
                String activityType = cursor.getString(cursor.getColumnIndex(columnActivityType));
                records[identifierToTrackRecordsArray][0] = startTime;
                records[identifierToTrackRecordsArray][1] = activityType;
                identifierToTrackRecordsArray++;
                cursor.moveToNext();

            }
        }
        database.close();
    }


    public void printUserMovementRecords(){
        for(int idForTrackingRecords =0; idForTrackingRecords<records.length; idForTrackingRecords++){
            Log.d("START TIME", "Start Time "+idForTrackingRecords+1+" : "+ records[idForTrackingRecords][0]);
            Log.d("ACTIVITY TYPE", "Activity Type "+idForTrackingRecords+1+" : "+ records[idForTrackingRecords][1]);
        }

    }

}
