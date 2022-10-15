package Controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import Model.Data;
import Utils.Util;


public class DatabaseHalper extends SQLiteOpenHelper {
    public DatabaseHalper( Context context) {
        super(context, Util.DATABASE_NAME, null, Util.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+Util.TABLE_NAME+" ("+Util.COLOUMN_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+Util.COLOUMN_NAME+" TEXT,"+Util.COLOUMN_LNAME+" TEXT,"+Util.COLOUMN_DESCRIPTION
                +" TEXT,"+Util.COLOUMN_AGE+" TEXT,"+Util.COLOUMN_TIME_STAMP+" DATETIME DEFAULT CURRENT_TIMESTAMP"+" )");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+Util.TABLE_NAME);
        onCreate(db);


    }

    public long insertData(Data data){
        SQLiteDatabase database=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(Util.COLOUMN_NAME,data.getName());
        contentValues.put(Util.COLOUMN_LNAME,data.getLname());
        contentValues.put(Util.COLOUMN_DESCRIPTION,data.getDescription());
        contentValues.put(Util.COLOUMN_AGE,data.getAge());
        long id=database.insert(Util.TABLE_NAME,null,contentValues);
        database.close();
        return id;
    }


    public int updateData(Data data){
        SQLiteDatabase database=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(Util.COLOUMN_NAME,data.getName());
        contentValues.put(Util.COLOUMN_LNAME,data.getLname());
        contentValues.put(Util.COLOUMN_DESCRIPTION,data.getDescription());
        contentValues.put(Util.COLOUMN_AGE,data.getAge());
        return database.update(Util.TABLE_NAME,contentValues,"id"+" =?",new String[]{String.valueOf(data.getId())});

    }

    public void deleteData(Data data){
        SQLiteDatabase database=this.getWritableDatabase();
        database.delete(Util.TABLE_NAME,Util.COLOUMN_ID+" =?",new String[]{String.valueOf(data.getId())});
        database.close();
    }


    public Data getData(int id){
        SQLiteDatabase database=this.getReadableDatabase();
        Cursor cursor=database.query(Util.TABLE_NAME,new String[]{Util.COLOUMN_ID,Util.COLOUMN_NAME,Util.COLOUMN_LNAME,Util.COLOUMN_DESCRIPTION,Util.COLOUMN_AGE,Util.COLOUMN_TIME_STAMP},
                Util.COLOUMN_ID+" =?",new String[]{String.valueOf(id)},null,null,null,null);
        if(cursor!=null)
            cursor.moveToFirst();
            Data data=new Data(cursor.getInt(cursor.getColumnIndex(Util.COLOUMN_ID)),
                    cursor.getString(cursor.getColumnIndex(Util.COLOUMN_NAME)),
                    cursor.getString(cursor.getColumnIndex(Util.COLOUMN_LNAME)),
                    cursor.getString(cursor.getColumnIndex(Util.COLOUMN_DESCRIPTION)),
                    cursor.getString(cursor.getColumnIndex(Util.COLOUMN_AGE)),
                    cursor.getString(cursor.getColumnIndex(Util.COLOUMN_TIME_STAMP)));

        cursor.close();
        return data;
    }

    public List<Data> getAllData(){
        List<Data>dataList=new ArrayList<>();
        String quer="SELECT * FROM "+Util.TABLE_NAME+" ORDER BY "+Util.COLOUMN_TIME_STAMP+" DESC";
        SQLiteDatabase database=this.getReadableDatabase();
        Cursor cursor=database.rawQuery(quer,null);
        if(cursor.moveToFirst())
            do {
                Data data=new Data();
                data.setId(cursor.getInt(cursor.getColumnIndex(Util.COLOUMN_ID)));
                data.setName(cursor.getString(cursor.getColumnIndex(Util.COLOUMN_NAME)));
                data.setLname(cursor.getString(cursor.getColumnIndex(Util.COLOUMN_LNAME)));
                data.setAge(cursor.getString(cursor.getColumnIndex(Util.COLOUMN_AGE)));
                data.setDescription(cursor.getString(cursor.getColumnIndex(Util.COLOUMN_DESCRIPTION)));
                data.setTimeStamp(cursor.getString(cursor.getColumnIndex(Util.COLOUMN_TIME_STAMP)));

                dataList.add(data);
            }while (cursor.moveToNext());

        database.close();
        return dataList;

    }

    public int getDataCount(){
        String quer="SELECT * FROM "+Util.TABLE_NAME;
        SQLiteDatabase database=this.getReadableDatabase();
        Cursor cursor=database.rawQuery(quer,null);
        return cursor.getCount();
    }


}
