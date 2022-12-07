package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

    public static Connection conectar() {
        Connection con = null;

        String password = "1111";
        String usuario = "root";
        String url = "jdbc:mysql://localhost:3306/producto3?user=" + usuario
                + "&password=" + password;
        try {
            con = DriverManager.getConnection(url);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return con;
    }
}
