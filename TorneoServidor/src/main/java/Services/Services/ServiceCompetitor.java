/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services.Services;


import Models.Competitor;
import Repository.ConnectionMySqlDB;
import Repository.ConnectionOracleDB;
import Services.Interfaces.IServiceCompetitor;

import Services.Interfaces.graficInterfaces.IUpgradeableCompetitor;


import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.sql.Date;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Luis
 */
public class ServiceCompetitor  extends UnicastRemoteObject implements IServiceCompetitor{

     private ArrayList<IUpgradeableCompetitor> guisCompetitors;
    private ServiceCompetitorMySql mysql;
    private ServiceCompetitorOracle oracle ;

    
    
    public ServiceCompetitor(ServiceCompetitorMySql mysql, ServiceCompetitorOracle oracle)throws RemoteException{
        guisCompetitors = new ArrayList<IUpgradeableCompetitor>();
        
        this.mysql = mysql;
        this.oracle = oracle;
        
    }
    
    
    // Hacer tristancho
    @Override
    public Competitor searchCompetitorById(long id) throws RemoteException{
             
        /*
        Competitor searched = null;
     
        try {
           
            
            CompletableFuture <Competitor> future1 = CompletableFuture.supplyAsync(() -> {
                try {
                    return mysql.searchCompetitorById(id);
                } catch (RemoteException e) {
                    
                    e.printStackTrace();
                    return null;
                }
            });
            CompletableFuture <Competitor> future2 = CompletableFuture.supplyAsync(() -> {
                try {
                    return oracle.searchCompetitorById(id);
                } catch (RemoteException e) {
                    
                    e.printStackTrace();
                    return null;
                }
            });

            if(future1.get().equals(future2.get()))
            {
                //throw new Exception();

                System.out.println("son iguales");
            }

            CompletableFuture<Object> cualquiera  = CompletableFuture.anyOf(future1,future2);

            searched = (Competitor) cualquiera.get(); 

        }  catch (Exception ex) {
            System.out.println("elementos encontrados pero no coinciden");
        }
        
        return searched;
        */
        return null;
    }

    // Hacer actualizar;
    @Override
    public void updateCompetitor(Competitor competitor)throws RemoteException {
        
       /* String cad = "UPDATE participante SET apodo ='"+competitor.getApodo()+
                "', fecha_inscripcion ='" + competitor.getFechaInscripcion()+
                "', fecha_caducidad ='"+competitor.getFechaCaducidad()+
                "' WHERE id = "+competitor.getId();
        try {
            if (!connectionMySql.executeUpdateStatement(cad)) {
                throw new Exception("Operacion no ejecutada");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
        */
    }

    // Hacer Brayan
    @Override
    public void deleteCompetitor(long id)throws RemoteException {
        
        /*
        String cad = "DELETE FROM participante WHERE id="+id;
        try {
            if (!connectionMySql.executeUpdateStatement(cad)) {
                throw new Exception("Operacion no ejecutada");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
        */
    }

    @Override
    public void createCompetitor(Competitor competitor)throws RemoteException {
        
        
        String cad = "INSERT INTO participante VALUES((SELECT Id FROM participante t ORDER BY t.Id DESC LIMIT 1)+1,'"
                                                + competitor.getApodo()+"','"
                                                + competitor.getFechaInscripcion()+"','"
                                                + competitor.getFechaCaducidad()+ "')";
        String cad2 = "INSERT INTO participantes VALUES(seq_Participantes.nextval,'"
                                                + competitor.getApodo()+"',TO_DATE('"                                           
                                                + competitor.getFechaInscripcion().toString()+"','YYYY-MM-DD'),TO_DATE('"
                                                + competitor.getFechaCaducidad().toString()+ "','YYYY-MM-DD'))";

        try {
            if (!connectionMySql.executeUpdateStatement(cad)) {
                throw new Exception("Operacion no ejecutada en MySQL");
            }
            
         
            if (!connectionOracle.executeUpdateStatement(cad2)) {
                throw new Exception("Operacion no ejecutada en ORACLE");
               }
        } catch (Exception e) {
            
            connectionMySql.devolver();
            connectionOracle.devolver();
            connectionOracle.restablecerSecuencia("SEQ_PARTICIPANTES");
            
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
            connectionMySql.aceptar();
            connectionOracle.aceptar();
        
    }

    @Override
    public List<Competitor> searchAll() throws RemoteException {
        
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
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
        
    }

    @Override
    public void addGUICompetitorUpgradable(IUpgradeableCompetitor guiA) throws RemoteException {
        guisCompetitors.add(guiA);
    }
    
    
    @Override
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
