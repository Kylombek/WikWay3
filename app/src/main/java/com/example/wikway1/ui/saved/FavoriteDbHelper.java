package com.example.wikway1.ui.saved;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.wikway1.JobAd;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by delaroy on 5/27/17.
 */
public class FavoriteDbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "favorite.db";

    private static final int DATABASE_VERSION = 1;

    public static final String LOGTAG = "FAVORITE";

    SQLiteOpenHelper dbhandler;
    SQLiteDatabase db;

    public FavoriteDbHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void open(){
        Log.i(LOGTAG, "Database Opened");
        db = dbhandler.getWritableDatabase();
    }

    public void close(){
        Log.i(LOGTAG, "Database Closed");
        dbhandler.close();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        final String SQL_CREATE_FAVORITE_TABLE = "CREATE TABLE " + FavoriteContract.FavoriteEntry.TABLE_NAME + " (" +
                FavoriteContract.FavoriteEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                FavoriteContract.FavoriteEntry.COLUMN_JOBNAME + " TEXT, " +
                FavoriteContract.FavoriteEntry.COLUMN_IMAGEURL + " TEXT, " +
                FavoriteContract.FavoriteEntry.COLUMN_BUNDESLAND + " TEXT" + "); ";

        sqLiteDatabase.execSQL(SQL_CREATE_FAVORITE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + FavoriteContract.FavoriteEntry.TABLE_NAME);
        onCreate(sqLiteDatabase);

    }

    public void addFavorite(JobAd job){

            SQLiteDatabase db = this.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put(FavoriteContract.FavoriteEntry.COLUMN_JOBNAME, job.getTitle());
            values.put(FavoriteContract.FavoriteEntry.COLUMN_IMAGEURL, job.getImageLink());
            values.put(FavoriteContract.FavoriteEntry.COLUMN_BUNDESLAND, job.getBundesland());


            db.insert(FavoriteContract.FavoriteEntry.TABLE_NAME, null, values);
            db.close();
        }





    public void deleteFavorite(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(FavoriteContract.FavoriteEntry.TABLE_NAME, FavoriteContract.FavoriteEntry.COLUMN_JOBNAME+ "=?", new String[]{id});
    }

    public List<JobAd> getAllFavorite(){
        String[] columns = {
              FavoriteContract.FavoriteEntry._ID,
                FavoriteContract.FavoriteEntry.COLUMN_JOBNAME,
                FavoriteContract.FavoriteEntry.COLUMN_IMAGEURL,
                FavoriteContract.FavoriteEntry.COLUMN_BUNDESLAND


        };
        String sortOrder =
                FavoriteContract.FavoriteEntry._ID + " ASC";
        List<JobAd> favoriteList = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(FavoriteContract.FavoriteEntry.TABLE_NAME,
                columns,
                null,
                null,
                null,
                null,
                sortOrder);

        if (cursor.moveToFirst()){
            do {
                JobAd jobAd = new JobAd();
                jobAd.setTitle(cursor.getString(cursor.getColumnIndex(FavoriteContract.FavoriteEntry.COLUMN_JOBNAME)));
                jobAd.setImageLink(cursor.getString(cursor.getColumnIndex(FavoriteContract.FavoriteEntry.COLUMN_IMAGEURL)));
                jobAd.setBundesland(cursor.getString(cursor.getColumnIndex(FavoriteContract.FavoriteEntry.COLUMN_BUNDESLAND)));
                jobAd.setFav(true);
                favoriteList.add(jobAd);

            }while(cursor.moveToNext());
        }
        cursor.close();
        db.close();

        return favoriteList;
    }

}
