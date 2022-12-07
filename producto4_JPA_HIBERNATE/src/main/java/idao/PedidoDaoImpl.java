package idao;

import dao.DaoPedido;
import dao.*;
import modelo.*;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


public class PedidoDaoImpl implements DaoPedido<Pedido> {

    @Override
    public boolean registrar(Pedido pedido) throws Exception {
        Connection con=null;
        boolean altaPedido=false;
        LocalDateTime localDateTime;
        Timestamp timestamp;

        try {
            con=Conexion.conectar();
            CallableStatement sp= con.prepareCall("CALL añadirPedido(?,?,?,?,?)");
            sp.setInt("Cantidad_ArticuloPedido",pedido.getCantidad());
            localDateTime=pedido.getFechaYhora();
            timestamp = Timestamp.valueOf(localDateTime);
            sp.setTimestamp("FechaHora_Pedido",timestamp);
            sp.setString("id_ArticuloPedido",pedido.getArticulo().getCodigo());
            sp.setString("id_eMailClientePedido",pedido.getCliente().geteMail());
            sp.registerOutParameter("guardado", Types.BOOLEAN);
            sp.execute();

            if (sp.getBoolean("guardado")==true)
            {
                altaPedido=true;
            } else  altaPedido=false;

            sp.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return altaPedido;

    }

    @Override
    public int getNumPedido() {
        int numPedido=0;
        Connection con=null;
        try {
            con=Conexion.conectar();
            CallableStatement sp= con.prepareCall("CALL getNumPedido(?)");
            sp.registerOutParameter("numPedido", Types.INTEGER);
            sp.execute();
            numPedido = sp.getInt("numPedido");
            sp.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return numPedido;
    }

    @Override
    public void borrarPedido(int id_Pedido) {
        Connection con=null;
        boolean existe=false;
        try {
            con=Conexion.conectar();
            CallableStatement sp=con.prepareCall("CALL eliminarPedido(?)");
            sp.setInt("id_Pedido",id_Pedido);
            sp.execute();
            sp.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Pedido> listarPedidos() throws Exception {
        List<Pedido> lista = new ArrayList<>();
        Connection con = null;
        try{
            con= Conexion.conectar();
            CallableStatement sp= con.prepareCall("{CALL listarPedidos}");
            ResultSet rs = sp.executeQuery();
            while(rs.next()){
                Cliente cliente;
                Articulo articulo = new Articulo(rs.getString("idArticuloPedido"), rs.getString("Descripcion"),rs.getFloat("PvpVenta"), rs.getFloat("GastosEnvio"), rs.getInt("TiempoPreparacion"));
                if(rs.getFloat("TarifaAnual")==0) {
                    cliente = new ClienteEstandard(rs.getString("id_eMail"), rs.getString("Nombre"), rs.getString("Domicilio"), rs.getString("Nif"));
                } else {
                    cliente = new ClientePremium(rs.getString("id_eMail"), rs.getString("Nombre"), rs.getString("Domicilio"), rs.getString("Nif"));
                }
                Pedido pedido = new Pedido(rs.getInt("idPedido"), articulo, rs.getInt("Cantidad"), cliente);
                pedido.setFechaYhora(rs.getTimestamp("FechaHora").toLocalDateTime());
                lista.add(pedido);

            }
        } catch (SQLException e){
            e.printStackTrace();
            return null;
        }
        return lista;
    }
}
