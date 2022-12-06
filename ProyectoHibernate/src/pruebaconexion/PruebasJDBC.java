package pruebaconexion;

import java.sql.*;

public class PruebasJDBC {

	public static void main(String[] args) {


	        String password = "Perryman43";
	        String usuario = "root";
	        String url = "jdbc:mysql://localhost:3306/productotres?user=" + usuario
	                + "&password=" + password;
	        try {
	            Connection con = DriverManager.getConnection(url); 
	            System.out.println("conexion exitosa");
	            } catch (Exception e) {          
	            e.printStackTrace();
	            }


	}

}
