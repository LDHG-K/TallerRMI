/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services.Services;


import Models.Competitor;
import Repository.ConnectionMySqlDB;
import Services.Interfaces.graficInterfaces.IUpgradeableCompetitor;
import java.rmi.RemoteException;

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
public class ServiceCompetitorMySql{

    
    private ArrayList<IUpgradeableCompetitor> guisCompetitors;
    private ConnectionMySqlDB connectionMySql;
    
    
    public ServiceCompetitorMySql(ConnectionMySqlDB connection){
        guisCompetitors = new ArrayList<IUpgradeableCompetitor>();
        this.connectionMySql = connection;
    }
    
    
    public Competitor searchCompetitorById(long id){
        
        String cad = "SELECT * FROM participante WHERE id ="+id;
        ResultSet res = null;
        Competitor searched = null;
        
        try {
            res = connectionMySql.executeQueryStatement(cad);   
            while(res.next())
            {
                searched = new Competitor(id, res.getString(2), res.getObject(3,Date.class), res.getObject(4,Date.class));
            }
        } catch (SQLException ex) {
             System.out.println("======================================");    
             System.out.println("Error procedimiento, Detalles:");
             ex.printStackTrace();
             System.out.println("======================================");
        }

        return searched;
    }


    public void updateCompetitor(Competitor competitor) {
        
        String cad = "UPDATE participante SET apodo ='"+competitor.getApodo()+
                "', fecha_inscripcion ='" + competitor.getFechaInscripcion()+
                "', fecha_caducidad ='"+competitor.getFechaCaducidad()+
                "' WHERE id = "+competitor.getId();
        try {
            if (!connectionMySql.executeUpdateStatement(cad)) {
                throw new Exception();
            }
        } catch (Exception e) {
            System.out.println("======================================");    
             System.out.println("Error procedimiento, Detalles:");
             e.printStackTrace();
             System.out.println("======================================");
            
        }
        
    }


    public void deleteCompetitor(long id){
        
        String cad = "DELETE FROM participante WHERE id="+id;
        
        try {
            if (!connectionMySql.executeUpdateStatement(cad)) 
            {
                throw new Exception();
            }
        } catch (Exception e) {
             System.out.println("======================================");    
             System.out.println("Error procedimiento, Detalles:");
             e.printStackTrace();
             System.out.println("======================================");
        }
    }


    public void createCompetitor(Competitor competitor) {
        
        
        String cad = "INSERT INTO participante VALUES((SELECT Id FROM participante t ORDER BY t.Id DESC LIMIT 1)+1,'"
                                                + competitor.getApodo()+"','"
                                                + competitor.getFechaInscripcion()+"','"
                                                + competitor.getFechaCaducidad()+ "')";

        try {
            if (!connectionMySql.executeUpdateStatement(cad)) {
                throw new Exception();
            }
            
        } catch (Exception e) {
            System.out.println("======================================");    
            System.out.println("Error procedimiento, Detalles:");
            e.printStackTrace();
            System.out.println("======================================");
        }
            connectionMySql.aceptar();
        
    }


    public List<Competitor> searchAll() {
        
        try {
            String cad = "SELECT * FROM participante";
            ResultSet res;
            ArrayList<Competitor> competitors = new ArrayList<>();

            int id;
            String apodo;
            Date fechaInscripcion;
            Date fechaCaducidad;

            res = connectionMySql.executeQueryStatement(cad);
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

 
    public void addGUICompetitorUpgradable(IUpgradeableCompetitor guiA){
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

            res = connectionMySql.executeQueryStatement(cad);
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
        connectionMySql.aceptar();
    }
    
    public void rollBack(){
        connectionMySql.devolver();
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
