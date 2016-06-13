package br.udesc.ceavi.bsi.automacaoresidencial.dao.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "automacao_residencial";

    private static final String SQL_CREATE_TABLE_APARELHO = "CREATE TABLE aparelho (rele INTEGER NOT NULL, descricao TEXT NOT NULL, status INTEGER NOT NULL ) ";
    private static final String SQL_DROP_TABLE_APARELHO = "DROP TABLE IF EXISTS aparelho";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TABLE_APARELHO);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DROP_TABLE_APARELHO);
        onCreate(db);
    }
}
