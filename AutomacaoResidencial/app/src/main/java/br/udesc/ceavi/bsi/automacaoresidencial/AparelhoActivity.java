package br.udesc.ceavi.bsi.automacaoresidencial;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.List;

import br.udesc.ceavi.bsi.automacaoresidencial.adapter.AparelhoAdapter;
import br.udesc.ceavi.bsi.automacaoresidencial.modelo.Aparelho;

public class AparelhoActivity extends ListActivity {

    private List<Aparelho> aparelhos;
    private ListView listView;
    private AparelhoAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aparelho);
        aparelhos = Aparelho.listar(AparelhoActivity.this);
        listView = getListView();
        adapter = new AparelhoAdapter(AparelhoActivity.this, aparelhos);
        listView.setAdapter(adapter);
    }
}
