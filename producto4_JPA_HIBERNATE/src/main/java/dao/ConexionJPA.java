package dao;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class ConexionJPA {

    private static ConexionJPA instance;
    private EntityManagerFactory entityManagerFactory;

    private ConexionJPA(){
        entityManagerFactory = Persistence.createEntityManagerFactory("jpa-hibernate-demo");
    }
    public static synchronized ConexionJPA getInstance(){
        return instance == null ? instance = new ConexionJPA() : instance;
    }

    public void shutdown()
    {
        if (entityManagerFactory!=null)
        {
            entityManagerFactory.close();
        }

    }

    public EntityManagerFactory getEntityManagerFactory() {
        return entityManagerFactory;
    }

}
