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
import oracle.net.aso.o;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.sql.Date;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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

    
    
    public ServiceCompetitor(ConnectionMySqlDB mySqlDB, ConnectionOracleDB oracleDB)throws RemoteException{
        guisCompetitors = new ArrayList<IUpgradeableCompetitor>();
        
        
        this.mysql = new ServiceCompetitorMySql(mySqlDB);
        this.oracle = new ServiceCompetitorOracle(oracleDB);

    }
    
    
    // Hacer tristancho
    @Override
    public Competitor searchCompetitorById(long id) throws RemoteException{
             
        
        Competitor searched = null;
     
        try {
           
            
            CompletableFuture <Competitor> future1 = CompletableFuture.supplyAsync(() -> {
                try {
                    return mysql.searchCompetitorById(id);
                } catch (Exception e) {
                    
                    e.printStackTrace();
                    return null;
                }
            });
            CompletableFuture <Competitor> future2 = CompletableFuture.supplyAsync(() -> {
                try {
                    return oracle.searchCompetitorById(id);
                } catch (Exception e) {
                    
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
        
     
    }

    // Hacer actualizar;
    @Override
    public void updateCompetitor(Competitor competitor)throws RemoteException {
        
       try {

        CompletableFuture <Void> future1 = CompletableFuture.runAsync(() -> {
            try {
                mysql.updateCompetitor(competitor);;
            } catch (Exception e) {
                
                e.printStackTrace();
               
            }
        });

        CompletableFuture <Void> future2 = CompletableFuture.runAsync(() -> {
            try {
                oracle.updateCompetitor(competitor);;
            } catch (Exception e) {
                
                e.printStackTrace();
               
            }
        });
        future1.join();
        future2.join();
           
       } catch (Exception e) {
        mysql.rollBack();
        oracle.rollBack();
       }

       mysql.commit();
       oracle.commit();
        
        





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
        
        //================================================================
//        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:SSS");
//        LocalDateTime inicio;
//        LocalDateTime fin;
//        Duration duracion;
//        
//        inicio = LocalDateTime.now();
//        System.out.println("Agregar Participante - Inicio : " + dtf.format(inicio));
        //================================================================
        
        try {
            CompletableFuture <Void> future1 = CompletableFuture.runAsync
            (() -> {
                try {
                    
                    //System.out.println("En ejecución de forma paralela mysql");
                    mysql.createCompetitor(competitor);
                    //Thread.sleep(3000);
                    System.out.println("finalizó MySql");
                } catch (Exception ex) {
                throw new RuntimeException("Fallo en agregar en MySql");
                }
            });
            CompletableFuture <Void> future2 = CompletableFuture.runAsync
            (() -> {
                try {
                    //System.out.println("En ejecución de forma paralela oracle");
                    oracle.createCompetitor(competitor);
                    //Thread.sleep(5000);
                    System.out.println("finalizó Oracle");
                } catch (Exception ex) {
                throw new RuntimeException("Fallo en agregar en Oracle");
                }
            });
            
            System.out.println("Inicio de la ejecución");
            //esperando
            future1.get();
            future2.get();
            }  
         catch (Exception e) {
            
            mysql.rollBack();
            oracle.rollBack();
            try {
                oracle.restablecerSeq("SEQ_PARTICIPANTES");
            } catch (InterruptedException ex) {
            }
            System.out.println(e.getMessage());
             System.out.println("RollBack Finalizado exitosamente");
        }
            mysql.commit();
            oracle.commit();
            //fin = LocalDateTime.now();
            //System.out.println("Agregar participante - Fin : " + dtf.format(fin));
            //duracion = Duration.between(fin, inicio);
            //System.out.println("Agregar participante  - Duración : " + duracion);
        
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

           /* res = connectionMySql.executeQueryStatement(cad);
            while(res.next()){

                id = Integer.parseInt(res.getString(1));
                apodo = res.getString(2);
                fechaInscripcion = res.getObject(3, Date.class);
                fechaCaducidad = res.getObject(4, Date.class);

                competitors.add(new Competitor(id, apodo, fechaInscripcion, fechaCaducidad));
                              
            }*/
            return competitors;
        } catch (Exception ex) {
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

            /*res = connectionMySql.executeQueryStatement(cad);
            while(res.next()){

                llave = res.getString(1);
                valor = Integer.parseInt(res.getString(2));
                
                map.put(llave, valor);
            }*/
            return map;
        } catch (Exception ex) {
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
