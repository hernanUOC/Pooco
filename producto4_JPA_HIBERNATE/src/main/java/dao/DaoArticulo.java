package dao;

import modeloEntity.Articulo;

public interface DaoArticulo<T> {



    public boolean registrar(T t) throws Exception;
    public modeloEntity.Articulo getArticuloDAOById(String idArticulo);
}
