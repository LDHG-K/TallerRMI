/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services.Services;


import Models.Competitor;
import Repository.ConnectionOracleDB;
import Services.Interfaces.graficInterfaces.IUpgradeableCompetitor;


import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Date;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Luis
 */
public class ServiceCompetitorOracle{

    private ArrayList<IUpgradeableCompetitor> guisCompetitors;
    private ConnectionOracleDB connectionOracle;
    
    public ConnectionOracleDB getConnectionOracle() {
        return connectionOracle;
    }





    public ServiceCompetitorOracle(ConnectionOracleDB connection)throws RemoteException{
        guisCompetitors = new ArrayList<IUpgradeableCompetitor>();
        this.connectionOracle = connection;
    }
    
    
    

   
    public Competitor searchCompetitorById(long id)throws Exception{

        
        String cad = "SELECT * FROM participantes WHERE id ="+id;
        ResultSet res = null;
        Competitor searched = null;        
        try {
            res = connectionOracle.executeQueryStatement(cad);
            while(res.next())
            {
                searched = new Competitor(id, res.getString(2), res.getObject(3,Date.class), res.getObject(4,Date.class));
            }
        } 
         catch (Exception ex) {
             System.out.println("======================================");    
             System.out.println("Error procedimiento, Detalles:");
             ex.printStackTrace();
             System.out.println("======================================");
        }
        
        return searched;
        
    }
    
    public void updateCompetitor(Competitor competitor)throws Exception {
        
        String cad = "UPDATE participantes SET apodo ='"+competitor.getApodo()+
                "', fecha_inscripcion =TO_DATE('" + competitor.getFechaInscripcion().toString()+"','YYYY-MM-DD')"+
                ", fecha_caducidad =TO_DATE('"+competitor.getFechaCaducidad().toString()+"','YYYY-MM-DD')"+
                " WHERE id = "+competitor.getId();
        try {
            if (!connectionOracle.executeUpdateStatement(cad)) {
                throw new Exception();
            }
        } catch (Exception e) {
             System.out.println("======================================");    
             System.out.println("Error procedimiento, Detalles:");
             e.printStackTrace();
             System.out.println("======================================");
        }
        
    }

   
    public void deleteCompetitor(long id)throws Exception {
        
        String cad = "DELETE FROM participantes WHERE id="+id;
        try {
            if (!connectionOracle.executeUpdateStatement(cad)) {
                throw new Exception();
            }
        } catch (Exception e) {
             System.out.println("======================================");    
             System.out.println("Error procedimiento, Detalles:");
             e.printStackTrace();
             System.out.println("======================================");
        }
    }

    public void createCompetitor(Competitor competitor)throws Exception {
        
        String cad = "INSERT INTO participantes VALUES(seq_Participantes.nextval,'"
                                                + competitor.getApodo()+"',TO_DATE('"                                           
                                                + competitor.getFechaInscripcion().toString()+"','YYYY-MM-DD'),TO_DATE('"
                                                + competitor.getFechaCaducidad().toString()+ "','YYYY-MM-DD'))";

        try {
            if (!connectionOracle.executeUpdateStatement(cad)) {
                throw new Exception();
               }
        } catch (Exception e) {
        
             System.out.println("======================================");    
             System.out.println("Error procedimiento, Detalles:");
             e.printStackTrace();
             System.out.println("======================================");
             throw new Exception();
        }
            connectionOracle.aceptar();    
    }

    public List<Competitor> searchAll() throws Exception {
        
        try {
            String cad = "SELECT * FROM participante";
            ResultSet res;
            ArrayList<Competitor> competitors = new ArrayList<>();

            int id;
            String apodo;
            Date fechaInscripcion;
            Date fechaCaducidad;

            res = connectionOracle.executeQueryStatement(cad);
            while(res.next()){

                id = Integer.parseInt(res.getString(1));
                apodo = res.getString(2);
                fechaInscripcion = res.getObject(3, Date.class);
                fechaCaducidad = res.getObject(4, Date.class);

                competitors.add(new Competitor(id, apodo, fechaInscripcion, fechaCaducidad));
                              
            }
            return competitors;
        } catch (SQLException ex) {
             System.out.println("======================================");    
             System.out.println("Error procedimiento, Detalles:");
             ex.printStackTrace();
             System.out.println("======================================");
        }
        return null;
    }

    
    public void addGUICompetitorUpgradable(IUpgradeableCompetitor guiA) throws Exception {
        guisCompetitors.add(guiA);
    }
    
    
    public HashMap<String,Integer> searchStatistics() {
        
    try {
            String cad = "select MonthName(fecha_caducidad), count(*)\n" +
                    "  from participante\n" +
                    " where fecha_caducidad >= makedate(year(curdate()), 1)\n" +
                    "   and fecha_caducidad < makedate(year(curdate()) + 1, 1)\n" +
                    " group by MonthName(fecha_caducidad)";
            
            ResultSet res;
            
            HashMap <String, Integer> map = new HashMap <String, Integer> ();
            
            
            Integer valor;
            String llave;

            res = connectionOracle.executeQueryStatement(cad);
            while(res.next()){

                llave = res.getString(1);
                valor = Integer.parseInt(res.getString(2));
                
                map.put(llave, valor);
            }
            return map;
        } catch (SQLException ex) {
            throw new RuntimeException("Error al extraer las estadisticas");
        }    
        
    }
    
    public void commit(){
        connectionOracle.aceptar();
    }
    public void rollBack(){
        connectionOracle.devolver();
    }
    
    
    
    
    //GUI TRICKS
    private void cambio(){
        for(IUpgradeableCompetitor gui : guisCompetitors){
            try {
                gui.change();
            } catch (RemoteException ex) {
                Logger.getLogger(ServiceCompetitor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    
    
    
    
}
