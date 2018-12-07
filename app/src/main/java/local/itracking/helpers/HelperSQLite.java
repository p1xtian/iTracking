package local.itracking.helpers;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import local.itracking.repositories.RepositoryLogsSecurity;

public class HelperSQLite extends SQLiteOpenHelper {


    RepositoryLogsSecurity repository = new RepositoryLogsSecurity();

    public HelperSQLite(
            Context context,
            String name,
            SQLiteDatabase.CursorFactory factory,
            int version) {
        super(context, name, factory, version);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(repository.DropTable);
        db.execSQL(repository.CreateTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(repository.DropTable);
        onCreate(db);
    }


}
