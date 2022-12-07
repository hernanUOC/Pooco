package modelo;

import dao.DaoArticulo;
import dao.DaoCliente;
import dao.DaoPedido;
import idao.ArticuloDaoImpl;
import idao.ClienteDaoImple;
import idao.PedidoDaoImpl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Datos {
    private modeloEntity.Articulo articulo;
    private modeloEntity.Cliente cliente;
    private modeloEntity.Clienteestandard clienteStd;
    private modeloEntity.Clientepremium clientePrm;
    private Pedido pedido;

    public Datos (){
    }

    public boolean setArticulo(String codigo, String descripcion, float pvpVenta, float gastosEnviom, int tiempoPreparacion)
    {
        articulo=new modeloEntity.Articulo(codigo,descripcion,pvpVenta,gastosEnviom,tiempoPreparacion);
        DaoArticulo dao= new ArticuloDaoImpl();
        try {
            if (dao.registrar(articulo)==false)
            {
                return false;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return true;
    }
    public boolean clienteByEmail(String eMail) {
        DaoCliente dao= new ClienteDaoImple();
        try {
            if (dao.getClienteEmail(eMail)==true)

                return true;
            else return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }
    public boolean setCliente(String eMail, String nombre, String domicilio, String nif, String tipo){
        boolean success=false;

        DaoCliente dao= new ClienteDaoImple();
        try {
            if (tipo.equals("1")) {

                cliente= new modeloEntity.Cliente(eMail,nombre,domicilio,nif);
                clienteStd= new modeloEntity.Clienteestandard(eMail,cliente,0.0f,0.0f);
                dao.registrarClienteEstandard(clienteStd,cliente);

            }
            else if(tipo.equals("2")) {
                cliente= new modeloEntity.Cliente(eMail,nombre,domicilio,nif);
                clientePrm= new modeloEntity.Clientepremium(eMail,cliente,20f,30f);
                dao.registrarClientePremium(clientePrm,cliente);

            }

        } catch (Exception e) {
            success = false;
            throw new RuntimeException(e);
        }
        return success;
    }

    public boolean setPedido(int numPedido, Articulo articulo,int cantidad, Cliente cliente)
    {
        boolean success=false;
        pedido=new Pedido(numPedido,articulo,cantidad,cliente);
        DaoPedido dao= new PedidoDaoImpl();
        try {
            success = dao.registrar(pedido);
        } catch (Exception e) {
            success = false;
            throw new RuntimeException(e);
        }
        return success;
    }

    /*public Cliente clienteByEmail(String eMail){
        List<Cliente> lista = getListaClientes();
        if(lista!=null) {
            for(int item=0; item<(lista.size()); item++) {
                if (eMail.equals(lista.get(item).geteMail())){
                    return lista.get(item);
                }
            }
        }
        return null;
    }*/






    public modeloEntity.Articulo  getArticuloByCodigo (String codigo)
    {
        DaoArticulo dao= new ArticuloDaoImpl();
        articulo = dao.getArticuloDAOById(codigo);
        return articulo;
    }

    public List<Cliente> getListaClientes(){
        Cliente cliente;
        DaoCliente dao= new ClienteDaoImple();
        try {
            List lista = dao.listarSTD();
            lista.addAll(dao.listarPRM());
            return lista;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<Cliente> getListaClientesSTD(){
        List<Cliente> lista = new ArrayList<>();
        DaoCliente dao= new ClienteDaoImple();
        try {
            lista = dao.listarSTD();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return lista;
    }

    public List<Cliente> getListaClientesPRM(){
        List<Cliente> lista = new ArrayList<>();
        DaoCliente dao= new ClienteDaoImple();
        try {
            lista = dao.listarPRM();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return lista;
    }

    public List<Pedido> getListaPedidos() {
        List<Pedido> lista = new ArrayList<>();
        DaoPedido dao = new PedidoDaoImpl();
        try {
            lista = dao.listarPedidos();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return lista;
    }

    public List<Pedido> getEnviadosByCliente(String eMail) {
        List<Pedido> listaCompleta = new ArrayList<>();
        List<Pedido> listaByCliente = new ArrayList<>();
        listaCompleta = getListaPedidos();
        for(int item=0; item<(listaCompleta.size()); item++){
            if(pedidoEnviado(listaCompleta, item)){
                if(listaCompleta.get(item).getCliente().geteMail().equals(eMail)){
                    listaByCliente.add(listaCompleta.get(item));
                }
            }
        }
        return listaByCliente;
    }

    public List<Pedido> getPendienteByCliente(String eMail) {
        List<Pedido> listaCompleta = new ArrayList<>();
        List<Pedido> listaByCliente = new ArrayList<>();
        listaCompleta = getListaPedidos();
        for(int item=0; item<(listaCompleta.size()); item++){
            if(!pedidoEnviado(listaCompleta, item)){
                if(listaCompleta.get(item).getCliente().geteMail().equals(eMail)){
                    listaByCliente.add(listaCompleta.get(item));
                }
            }
        }
        return listaByCliente;
    }

    public int getNumeroPedido(){
        int numPedido=0;
        DaoPedido dao= new PedidoDaoImpl();
        try {
            numPedido=dao.getNumPedido();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return numPedido;
    }

    public int pedidoByNum(int numPedido){
        List<Pedido> lista = new ArrayList<>();
        lista = getListaPedidos();
        for(int item=0; item<(lista.size()); item++) {
            if (numPedido==(lista.get(item).getNumPedido())){
                return item;
            }
        }
        return -1;
    }


    public modeloEntity.Articulo getArticulo()
    {
        return articulo;
    }

    public boolean pedidoEnviado(List<Pedido> lista,int item){
        LocalDateTime fechahoraPedido;
        LocalDateTime fechahoraAhora= LocalDateTime.now();
        int tiempoPrepara;
        fechahoraPedido=lista.get(item).getFechaYhora();
        tiempoPrepara=lista.get(item).getArticulo().getTiempoPreparacion();
        if (fechahoraPedido.plusMinutes(tiempoPrepara).isBefore(fechahoraAhora)) {
            return true;
        }
        return false;
    }

    public void borrarPedido(int id_Pedido) {
        DaoPedido dao= new PedidoDaoImpl();
        try
        {
            dao.borrarPedido(id_Pedido);
        } catch (Exception e) {
            throw  new RuntimeException(e);
        }
    }



}
