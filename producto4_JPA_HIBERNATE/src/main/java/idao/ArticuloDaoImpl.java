package idao;

import dao.ConexionJPA;
import dao.DaoArticulo;
import modeloEntity.Articulo;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;


public class ArticuloDaoImpl implements DaoArticulo<Articulo> {

   // private static ConexionJPA jpaService=ConexionJPA.getInstance();


    @Override
    public boolean registrar(modeloEntity.Articulo articulo) throws Exception {
        try
        {
        ConexionJPA jpaService =ConexionJPA.getInstance();
        EntityManagerFactory entityManagerFactory= jpaService.getEntityManagerFactory();
        EntityManager entityManager=entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        entityManager.persist(new modeloEntity.Articulo(articulo.getId(), articulo.getDescripcion(), articulo.getPvpVenta(), articulo.getGastosEnvio(),
                articulo.getTiempoPreparacion()) );

        transaction.commit();
        System.out.println("Guardado");

        return true;

    } catch (Exception e) {
            e.printStackTrace();
            System.out.println("No guardado");
            return false;
        }

    }


    @Override
    public modeloEntity.Articulo getArticuloDAOById(String idArticulo) {

        try
        {
            ConexionJPA jpaService =ConexionJPA.getInstance();
            EntityManagerFactory entityManagerFactory= jpaService.getEntityManagerFactory();
            EntityManager entityManager=entityManagerFactory.createEntityManager();
            EntityTransaction transaction = entityManager.getTransaction();
            transaction.begin();

            modeloEntity.Articulo articulo= entityManager.find(modeloEntity.Articulo.class,idArticulo);

            transaction.commit();

            if (articulo!=null)
                return articulo;
            else return null;


        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("NoEncontrado");
            return null;
        }







        /*Statement stm= null;
        Connection con=null;
        boolean encontradoArticulo=false;
        Articulo art;
        try {
            con=Conexion.conectar();
            CallableStatement sp= con.prepareCall("CALL mostrarArticulo(?,?,?,?,?,?)");
            sp.setString("id_Articulo",id);
            sp.registerOutParameter("Descripcion_Articulo", Types.VARCHAR);
            sp.registerOutParameter("PvpVenta_Articulo",Types.FLOAT);
            sp.registerOutParameter("GastosEnvio_Articulo",Types.FLOAT);
            sp.registerOutParameter("TiempoPreparacion_Articulo", Types.INTEGER);
            sp.registerOutParameter("encontrado", Types.BOOLEAN);
            sp.execute();

            if (sp.getBoolean("encontrado")==false)
            {
                art=null;
            }
            else
            {
                art=new Articulo(id,sp.getString("Descripcion_Articulo"), sp.getFloat("PvpVenta_Articulo"),
                        sp.getFloat("GastosEnvio_Articulo"), sp.getInt("TiempoPreparacion_Articulo"));
            }

            sp.close();
            con.close();
            return art;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
*/
    }
}
