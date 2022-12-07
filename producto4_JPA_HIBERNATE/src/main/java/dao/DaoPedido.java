package dao;

import java.util.List;

public interface DaoPedido<T> {

    public boolean registrar(T t) throws Exception;
    public int getNumPedido();
    public void borrarPedido(int id_Pedido);
    public List<T> listarPedidos() throws Exception;



}
