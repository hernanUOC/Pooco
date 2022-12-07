package dao;

import modeloEntity.Cliente;
import modeloEntity.Clienteestandard;
import modeloEntity.Clientepremium;

import java.util.List;

public interface DaoCliente<T> {
    public List<T> listarSTD() throws Exception;
    public List<T> listarPRM() throws Exception;
  //  public boolean registrar(T t) throws Exception;

    // public boolean registrar(Cliente cliente) throws Exception {
    boolean registrarClienteEstandard(Clienteestandard cliente, Cliente clientePadre) throws Exception;
    boolean registrarClientePremium(Clientepremium cliente, Cliente clientePadre) throws Exception;

    boolean getClienteEmail(String email) throws Exception;

   // boolean registrarClienteEstandard(Clienteestandard cliente Cliente clientePadre) throws Exception;



   // boolean registrar(Cliente cliente) throws Exception;
    // public Cliente getClientes(String e_Mail, float c_alculo, float d_escuento);
}