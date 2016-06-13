package br.udesc.ceavi.bsi.automacaoresidencial.modelo;

import android.content.Context;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

import br.udesc.ceavi.bsi.automacaoresidencial.dao.aparelho.AparelhoDAO;
import br.udesc.ceavi.bsi.automacaoresidencial.dao.core.Factory;

public class Aparelho {

    private int rele;
    private String descricao;
    private boolean status;

    public Aparelho() {
        this.status = false;
    }

    public Aparelho(int rele, String descricao, boolean status) {
        this.rele = rele;
        this.descricao = descricao;
        this.status = status;
    }

    public int getRele() {
        return rele;
    }

    public void setRele(int rele) {
        this.rele = rele;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public static List<Aparelho> listar(Context c) {
        AparelhoDAO dao = Factory.getInstance(c).getAparelhoDAO();
        return dao.listar();
    }

    public void inserir(Context c){
        AparelhoDAO dao = Factory.getInstance(c).getAparelhoDAO();
        dao.inserir(this);
    }

    public void alterar(Context c){
        AparelhoDAO dao = Factory.getInstance(c).getAparelhoDAO();
        dao.atualizar(this);
    }

    public void remover(Context c){
        AparelhoDAO dao = Factory.getInstance(c).getAparelhoDAO();
        dao.remover(this);
    }

    public JSONObject getJSON(){
        JSONObject j = new JSONObject();
        JSONObject json = new JSONObject();
        try {
            j.put("rele",this.rele);
            j.put("status",this.status);
            json.put("Aparelho",j);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return json;
    }
}