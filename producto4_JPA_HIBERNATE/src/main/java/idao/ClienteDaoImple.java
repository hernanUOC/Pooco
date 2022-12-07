package idao;

import dao.DaoCliente;
import dao.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import modelo.*;
import modeloEntity.Clienteestandard;
import modeloEntity.Clientepremium;

import java.sql.*;
import java.util.List;
import java.util.ArrayList;

public class ClienteDaoImple implements DaoCliente<Cliente> {


    @Override
    public List<Cliente> listarSTD() throws Exception {
        try
        {
            ConexionJPA jpaService =ConexionJPA.getInstance();
            EntityManagerFactory entityManagerFactory= jpaService.getEntityManagerFactory();
            EntityManager entityManager=entityManagerFactory.createEntityManager();
            EntityTransaction transaction = entityManager.getTransaction();
            transaction.begin();
            List<modeloEntity.Cliente> todosClientesEstandard;
            Query nativeQuery= entityManager.createNativeQuery("SELECT * FROM clienteestandard");
            transaction.commit();
            System.out.println("Bueno");

        }
        catch (Exception e)
        {

        }
        return null;
        /*List<Cliente> lista = new ArrayList<>();
        Cliente cliente;
        Connection con=null;

        try{
            con= Conexion.conectar();
            CallableStatement sp= con.prepareCall("{CALL mostrarClientesEstandard}");
            ResultSet rs = sp.executeQuery();
            while(rs.next()){
                cliente = new ClienteEstandard(rs.getString("id_eMail"), rs.getString("Nombre"),rs.getString("Domicilio"), rs.getString("Nif"));
                lista.add(cliente);
            }
            rs.close();
        }catch (Exception e){
            return null;
        }
        return lista;*/
    }

    @Override
    public List<Cliente> listarPRM() throws Exception {
        List<Cliente> lista = new ArrayList<>();
        Cliente cliente;
        Connection con=null;
        try{
            con= Conexion.conectar();
            CallableStatement sp= con.prepareCall("{CALL mostrarClientesPremium}");
            ResultSet rs = sp.executeQuery();
            while(rs.next()){
                cliente = new ClientePremium(rs.getString("id_eMail"), rs.getString("Nombre"),rs.getString("Domicilio"), rs.getString("Nif"));
                lista.add(cliente);
            }
            rs.close();
        }catch (Exception e){
            return null;
        }
        return lista;
    }

    @Override
    public boolean registrarClienteEstandard(Clienteestandard cliente, modeloEntity.Cliente clientePadre) throws Exception {
        try
        {
            //Registramos Cliente Estandard
            ConexionJPA jpaService =ConexionJPA.getInstance();
            EntityManagerFactory entityManagerFactory= jpaService.getEntityManagerFactory();
            EntityManager entityManager=entityManagerFactory.createEntityManager();
            EntityTransaction transaction = entityManager.getTransaction();
            transaction.begin();

            entityManager.persist(new modeloEntity.Clienteestandard(cliente.getId(),clientePadre,cliente.getDescuento()
                    ,cliente.getTarifaAnual()));

            transaction.commit();
            System.out.println("Guardado");
            return true;


        }
        catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }
    }



    @Override
    public boolean registrarClientePremium(Clientepremium cliente, modeloEntity.Cliente clientePadre) throws Exception {
        try
        {
            //Registramos Cliente Premium
            ConexionJPA jpaService =ConexionJPA.getInstance();
            EntityManagerFactory entityManagerFactory= jpaService.getEntityManagerFactory();
            EntityManager entityManager=entityManagerFactory.createEntityManager();
            EntityTransaction transaction = entityManager.getTransaction();
            transaction.begin();

            entityManager.persist(new modeloEntity.Clientepremium(cliente.getId(),clientePadre,cliente.getDescuento()
                    ,cliente.getTarifaAnual()));

            transaction.commit();
            System.out.println("Guardado");
            return true;


        }
        catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }

    }

    @Override
    public boolean getClienteEmail(String email) throws Exception {
        try
        {
            ConexionJPA jpaService =ConexionJPA.getInstance();
            EntityManagerFactory entityManagerFactory= jpaService.getEntityManagerFactory();
            EntityManager entityManager=entityManagerFactory.createEntityManager();
            EntityTransaction transaction = entityManager.getTransaction();
            transaction.begin();

            modeloEntity.Cliente cliente= entityManager.find(modeloEntity.Cliente.class,email);

            transaction.commit();

            if (cliente!=null)
                return true;
            else return false;


        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("NoEncontrado");
            return false;
        }



    }


   /*
   @Override
    public boolean registrar(modeloEntity.Cliente cliente) throws Exception {

        try
        {
            //Registramos Cliente
            ConexionJPA jpaService =ConexionJPA.getInstance();
            EntityManagerFactory entityManagerFactory= jpaService.getEntityManagerFactory();
            EntityManager entityManager=entityManagerFactory.createEntityManager();
            EntityTransaction transaction = entityManager.getTransaction();
            transaction.begin();

            entityManager.persist(new modeloEntity.Cliente(cliente.getId(),cliente.getNombre(), cliente.getDomicilio(),
                    cliente.getNif()));
            transaction.commit();
            System.out.println("Guardado");
            return true;

        }
        catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }
    }*/
}
