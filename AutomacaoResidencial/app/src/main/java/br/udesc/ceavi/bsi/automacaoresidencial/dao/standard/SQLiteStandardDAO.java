package br.udesc.ceavi.bsi.automacaoresidencial.dao.standard;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.List;

import br.udesc.ceavi.bsi.automacaoresidencial.dao.sqlite.DatabaseHelper;


public abstract class SQLiteStandardDAO<T extends Object> implements StandardDAO<T> {

    protected Context context;

    public SQLiteStandardDAO(Context context) {
        this.context = context;
    }

    protected abstract String getTableName();
    protected abstract String[] getColumnsNames();
    protected abstract List<T> getList(Cursor c);
    protected abstract ContentValues getContentValues(T t);
    protected abstract int getId(T t);

    @Override
    public void inserir(T t){
        DatabaseHelper dbHelper = new DatabaseHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = getContentValues(t);

        db.insert(getTableName(),null,values);

        db.close();
        dbHelper.close();

    }

    @Override
    public void remover(T t) {
        DatabaseHelper dbHelper = new DatabaseHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        db.delete(getTableName(), getColumnsNames()[0]+" = ?", new String[]{String.valueOf(getId(t))});

        db.close();
        dbHelper.close();
    }

    @Override
    public void atualizar(T t) {
        DatabaseHelper dbHelper = new DatabaseHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = getContentValues(t);

        db.update(getTableName(),values,getColumnsNames()[0] + " = ?",new String[]{String.valueOf(getId(t))});

        db.close();
        dbHelper.close();
    }

    @Override
    public List<T> listar(){

        DatabaseHelper dbHelper = new DatabaseHelper(context);
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String[] columnsNames = getColumnsNames();
        Cursor cursor = db.query(getTableName(), columnsNames, null, null, null, null, null);

        List<T> list = getList(cursor);

        cursor.close();
        db.close();
        dbHelper.close();

        return list;
    }
}

