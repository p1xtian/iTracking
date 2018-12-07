package local.itracking.repositories;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

import local.itracking.helpers.HelperSQLite;
import local.itracking.models.LogSecurity;

public class RepositoryLogsSecurity {

    public String CreateTable = "CREATE TABLE Logs (" +
            "DateTime Long, " +
            "Type TEXT," +
            "Value TEXT)";

    public String DropTable = "DROP TABLE IF EXISTS Logs";

    public ArrayList<LogSecurity> Select(HelperSQLite helper) {
        SQLiteDatabase db = helper.getWritableDatabase();
        ArrayList<LogSecurity> logs = new ArrayList<LogSecurity>();

        try {
            String query = "SELECT * FROM Logs ORDER BY DateTime DESC";
            Cursor cursor = db.rawQuery(query, null);

            if (cursor.getCount() > 0) {
                while (cursor.moveToNext())
                {
                    logs
                            .add(new LogSecurity(
                                    cursor.getLong(cursor.getColumnIndex("DateTime")),
                                    cursor.getString(cursor.getColumnIndex("Type")),
                                    cursor.getString(cursor.getColumnIndex("Value"))));
                }
            }
            cursor.close();
            Log.d("=iTracking=>","Logs Readed : " +Integer.toString(logs.size()));
            return logs;
        } catch (Exception ex) {
            Log.d("=iTracking=>",ex.toString());
            return null;
        } finally {
            if (db.isOpen())
                db.close();

        }
    }

    public Boolean Insert(HelperSQLite helper, LogSecurity log)
    {
        SQLiteDatabase db = helper.getWritableDatabase();
        try{


            String query = "INSERT INTO Logs VALUES(" +
                    log.getDateTime() + ",'" +
                    log.getType() + "','" +
                    log.getValue() + "')";
            Log.d("=iTracking=>",query);
            db.execSQL(query);
            return true;
        }
        catch (Exception ex)
        {
            Log.d("=iTracking=>",ex.toString());
            return false;
        }
        finally {
            if(db.isOpen())
                db.close();
        }
    }

    public Boolean Delete(HelperSQLite helper)
    {
        SQLiteDatabase db = helper.getWritableDatabase();
        try{


            String query = "DELETE FROM Logs ";
            db.execSQL(query);
            return true;
        }
        catch (Exception ex)
        {
            return false;
        }
        finally {
            if(db.isOpen())
                db.close();
        }
    }
}
