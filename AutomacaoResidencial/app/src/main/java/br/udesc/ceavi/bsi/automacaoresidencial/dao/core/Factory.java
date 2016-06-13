package br.udesc.ceavi.bsi.automacaoresidencial.dao.core;

import android.content.Context;

import br.udesc.ceavi.bsi.automacaoresidencial.dao.aparelho.AparelhoDAO;
import br.udesc.ceavi.bsi.automacaoresidencial.dao.sqlite.SQLiteFactory;

public abstract class Factory {

    protected static Context context;

    public static Factory getInstance(Context context) {
        Factory.context = context;
        return new SQLiteFactory();
    }
    public abstract AparelhoDAO getAparelhoDAO();
}