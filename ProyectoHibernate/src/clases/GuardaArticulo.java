package clases;

import java.sql.Connection;
import java.sql.DriverManager;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class GuardaArticulo {
	
	public static void main(String[] args) {
		
		System.out.println("funciona?");
		SessionFactory miFactory=new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Articulo.class).buildSessionFactory();
		
		System.out.println("funciona?");
		Session miSession=miFactory.openSession();
		
		System.out.println("funciona?");
		
		
		try {
			Articulo articulo1=new Articulo("codigo1", "funciona", 3, 3, 3);
			
			miSession.beginTransaction();
			
			miSession.save(articulo1);
			
			miSession.getTransaction().commit();
			
			System.out.println("Registro insertado correctamente en la BBDD");
			
			miSession.close();
			
		}finally {
			miFactory.close();
		}
		
		
	}


}
