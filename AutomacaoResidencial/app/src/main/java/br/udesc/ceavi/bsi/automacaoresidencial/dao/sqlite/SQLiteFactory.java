package br.udesc.ceavi.bsi.automacaoresidencial.dao.sqlite;

import br.udesc.ceavi.bsi.automacaoresidencial.dao.aparelho.AparelhoDAO;
import br.udesc.ceavi.bsi.automacaoresidencial.dao.aparelho.SQLiteAparelhoDAO;
import br.udesc.ceavi.bsi.automacaoresidencial.dao.core.Factory;

public class SQLiteFactory extends Factory {

    @Override
    public AparelhoDAO getAparelhoDAO() {
        return new SQLiteAparelhoDAO(context);
    }

}
