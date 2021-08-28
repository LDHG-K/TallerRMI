package Models;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;

public class DbConecction {

    private Connection con;
        private String user;
        private String password;
        private Statement stmt;
        private String url;
        private String nombreBD;

        public DbConecction( String us, String pass){
           user = us;
           password = pass;
           nombreBD  = "taller_rmi_db";
           conectar();
        }

        public DbConecction() {
            this("root","akira");
        }

        //Metodo para conectarce a una base de datos
        private void conectar(){
            try{

                Class.forName("com.mysql.cj.jdbc.Driver");
            }
            catch(ClassNotFoundException e){
                System.err.println("'conectarAccess()' Error al intentar cargar Driver. "+e.getMessage());
                e.printStackTrace();
            }

            try{
                url="jdbc:mysql://localhost:3306/"+nombreBD;
                con = DriverManager.getConnection(url,user,password);

                con.setAutoCommit(true);
                System.out.println("Conexion exitosa...");
            }catch(Exception e){
                System.out.println("Error al conectarce: "+e);
            }
        }
    
}
