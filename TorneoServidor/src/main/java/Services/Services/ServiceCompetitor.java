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
    
    
    
    @Override
    public Competitor searchCompetitorById(long id) throws RemoteException{
             
        
        Competitor searched = null;
     
        try {
           
            
            CompletableFuture <Competitor> future1 = CompletableFuture.supplyAsync(() -> {
                try {
                    return mysql.searchCompetitorById(id);
                } catch (Exception e) {
                    throw new RuntimeException("Error al buscar en mysql ");
                }
            });
            CompletableFuture <Competitor> future2 = CompletableFuture.supplyAsync(() -> {
                try {
                    return oracle.searchCompetitorById(id);
                } catch (Exception e) {
                    throw new RuntimeException("Error al buscar en Oracle ");
                }
            });

            if(future1.get().equals(future2.get()))
            {
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
                
                throw new RuntimeException();
               
            }
        });

        CompletableFuture <Void> future2 = CompletableFuture.runAsync(() -> {
            try {
                oracle.updateCompetitor(competitor);;
            } catch (Exception e) {
                
                throw new RuntimeException();
               
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



    try {

                CompletableFuture <Void> future1 = CompletableFuture.runAsync(() -> {
                    try {
                        mysql.deleteCompetitor(id);
                    } catch (Exception e) {

                        throw new RuntimeException("Eliminacion fallida en MySQL");


                    }
                });

                CompletableFuture <Void> future2 = CompletableFuture.runAsync(() -> {
                    try {
                        oracle.deleteCompetitor(id);
                    } catch (Exception e) {

                        throw new RuntimeException("Eliminacion fallida en Oracle");



                    }
                });
            future1.get();
            future2.get();
            }  catch (Exception e)
            {
                System.out.println(e.getMessage());
                System.out.println("Eliminacion Fallida");
                mysql.rollBack();
                oracle.rollBack();
            }

            mysql.commit();
            oracle.commit();



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
            
            //List<Competitor> competitorsMySql = null;            
            //List<Competitor> competitorsOracle = null;
            
            CompletableFuture <List<Competitor>> future1 = CompletableFuture.supplyAsync
            (() -> {
                try {
                    
                    //System.out.println("En ejecución de forma paralela mysql");
                    return mysql.searchAll();
                    //Thread.sleep(3000);
                    //System.out.println("finalizó MySql");
                } catch (Exception ex) {
                throw new RuntimeException("Fallo en ejecutar en MySql");
                }
            });
            
            CompletableFuture <List<Competitor>> future2 = CompletableFuture.supplyAsync
            (() -> {
                try {
                    //System.out.println("En ejecución de forma paralela oracle");
                    return oracle.searchAll();
                    //Thread.sleep(5000);
                    //System.out.println("finalizó Oracle");
                } catch (Exception ex) {
                throw new RuntimeException("Fallo en ejecutar en Oracle");
                }
            });
            //competitorsMySql = future1.get();
            //competitorsOracle = future2.get();
            
            //if (competitorsMySql.containsAll(competitorsOracle)) {
            //    return competitorsMySql;
            //}
            //else{
            //    throw new Exception("LISTAS NO COMPATIBLES");
            //}
            CompletableFuture<Object> cualquiera  = CompletableFuture.anyOf(future1,future2);
            return (List<Competitor>) cualquiera.get();
            
        } catch (Exception ex) {
            throw new RuntimeException();
        }
        
    }

    @Override
    public void addGUICompetitorUpgradable(IUpgradeableCompetitor guiA) throws RemoteException {
        guisCompetitors.add(guiA);
    }
    
    
    @Override
    public HashMap<String,Integer> searchStatistics() {
        
    try {
            
            CompletableFuture <HashMap <String, Integer>> future1 = CompletableFuture.supplyAsync
            (() -> {
                try {
                    
                    //System.out.println("En ejecución de forma paralela mysql");
                    return mysql.searchStatistics();
                    //Thread.sleep(3000);
                    //System.out.println("finalizó MySql");
                } catch (Exception ex) {
                throw new RuntimeException("Fallo en ejecutar en MySql");
                }
            });
            
            CompletableFuture <HashMap <String, Integer>> future2 = CompletableFuture.supplyAsync
            (() -> {
                try {
                    //System.out.println("En ejecución de forma paralela oracle");
                    return oracle.searchStatistics();
                    //Thread.sleep(5000);
                    //System.out.println("finalizó Oracle");
                } catch (Exception ex) {
                throw new RuntimeException("Fallo en ejecutar en Oracle");
                }
            });
            //competitorsMySql = future1.get();
            //competitorsOracle = future2.get();
            
            //if (competitorsMySql.containsAll(competitorsOracle)) {
            //    return competitorsMySql;
            //}
            //else{
            //    throw new Exception("LISTAS NO COMPATIBLES");
            //}
            CompletableFuture<Object> cualquiera  = CompletableFuture.anyOf(future1,future2);
            return (HashMap <String, Integer>) cualquiera.get();
            
        } 
          
        catch (Exception ex) {
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
