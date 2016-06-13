package br.udesc.ceavi.bsi.automacaoresidencial.dao.aparelho;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import br.udesc.ceavi.bsi.automacaoresidencial.dao.standard.SQLiteStandardDAO;
import br.udesc.ceavi.bsi.automacaoresidencial.modelo.Aparelho;

public class SQLiteAparelhoDAO extends SQLiteStandardDAO<Aparelho> implements AparelhoDAO {

    public SQLiteAparelhoDAO(Context context) {
        super(context);
    }

    @Override
    protected String getTableName() {
        return "aparelho";
    }

    @Override
    protected String[] getColumnsNames() {
        return new String[]{"rele","descricao","status"};
    }

    @Override
    protected List<Aparelho> getList(Cursor c) {
        List<Aparelho>list = new ArrayList<>();
        if(c != null && c.moveToFirst()){
            do{
                Aparelho a = new Aparelho();
                a.setRele(c.getInt(0));
                a.setDescricao(c.getString(1));
                a.setStatus(c.getInt(2)==1?true:false);
                list.add(a);
            }while(c.moveToNext());
        }
        return list;
    }

    @Override
    protected ContentValues getContentValues(Aparelho aparelho) {
        ContentValues values = new ContentValues();
        values.put("rele",aparelho.getRele());
        values.put("descricao", aparelho.getDescricao());
        values.put("status", aparelho.isStatus()?1:0);
        return values;
    }

    @Override
    protected int getId(Aparelho aparelho) {
        return aparelho.getRele();
    }
}
