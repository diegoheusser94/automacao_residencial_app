package br.udesc.ceavi.bsi.automacaoresidencial.dao.standard;

import java.util.List;

public interface StandardDAO<T> {

    void inserir(T t);
    void remover(T t);
    void atualizar(T t);
    List<T> listar();

}
