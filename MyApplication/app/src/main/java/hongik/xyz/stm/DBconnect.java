package hongik.xyz.stm;

import android.app.DownloadManager;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by hara on 2016-12-04.
 */

public class DBconnect extends SQLiteOpenHelper {

    public static String TABLE_NAME_MAP = "Place";

    public DBconnect(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE Place" +
                "(" +
                "place_ID             INT PRIMARY KEY NOT NULL," +
                "place_name           CHAR(20) NULL," +
                "l_lat                LONG NULL," +
                "l_long               LONG NULL," +
                "charge               CHAR(20) NULL," +
                "type                 CHAR(20) NULL," +
                "place_info           VARCHAR(200) NULL" +
                ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public boolean updateAll(String table, JSONArray js){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM"+table+";");
        for(int i=0;i<js.length();i++) {
            try {
                db.execSQL("INSERT INTO " + table + " (place_ID, place_name, l_lat, l_long, charge, type, place_info) VALUES (\""
                        + js.getJSONObject(i).getString("place_ID") + "\" ,\""
                        + js.getJSONObject(i).getString("place_name") +"\",\""
                        + js.getJSONObject(i).getString("l_lat") +"\",\""
                        + js.getJSONObject(i).getString("l_long") +"\",\""
                        + js.getJSONObject(i).getString("charge") +"\",\""
                        + js.getJSONObject(i).getString("type") +"\",\""
                        + js.getJSONObject(i).getString("place_info") +"\");");
            }catch (Exception e){
                e.printStackTrace();
                return false;
            }
        }
        db.close();
        return true;
    }

    public JSONArray getAll(String table){
        SQLiteDatabase db = getReadableDatabase();
        JSONArray out = new JSONArray();
        Cursor getCursor = db.rawQuery("SELECT * FROM "+table+";",null);
        while (getCursor.moveToNext()){
            try{
                JSONObject jo = new JSONObject("{\"place_ID\":\""+getCursor.getString(0)
                        +"\",\"place_name\":\""+getCursor.getString(1)
                        +"\",\"l_lat\":\""+getCursor.getString(2)
                        +"\",\"l_long\":\""+getCursor.getString(3)
                        +"\",\"charge\":\""+getCursor.getString(4)
                        +"\",\"type\":\""+getCursor.getString(5)+"\"}" );
                Log.d("test", jo.toString());
                out.put(jo);
            }catch (JSONException e){
                e.getStackTrace();
                return null;
            }
        }
        getCursor.close();
        db.close();
        return out;
    }
    public  JSONObject getPlace(String table, int placeID){
        SQLiteDatabase db = getReadableDatabase();
        JSONObject out;
        Cursor getCursor = db.rawQuery("SELECT * FROM "+table+" WHERE place_ID = "+placeID, null);

        getCursor.moveToNext();
            try {
                out = new JSONObject("{\"place_ID\":\"" + getCursor.getString(0)
                        + "\",\"place_name\":\"" + getCursor.getString(1)
                        + "\",\"l_lat\":\"" + getCursor.getString(2)
                        + "\",\"l_long\":\"" + getCursor.getString(3)
                        + "\",\"charge\":\"" + getCursor.getString(4)
                        + "\",\"type\":\"" + getCursor.getString(5)
                        + "\",\"place_info\":\"" + getCursor.getString(6) +"\"}");
            }catch (JSONException e){
                e.getStackTrace();
                return null;
            }

        return out;

    }
}
