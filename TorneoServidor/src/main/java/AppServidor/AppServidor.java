/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AppServidor;

import Repository.ConnectionMySqlDB;
import Repository.ConnectionOracleDB;
import Services.Interfaces.IServiceCompetitor;
import Services.Services.ServiceCompetitor;
import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

/**
 *
 * @author Luis
 */
public class AppServidor {
    
    public static void main(String[] args) {
        
        
        try 
        { 
            ConnectionMySqlDB connectionMySql = new ConnectionMySqlDB();
            ConnectionOracleDB connectionOracle = new ConnectionOracleDB();
            //Services.Services.ServiceCompetitor model = new ServiceCompetitor(connection);
            IServiceCompetitor model = new ServiceCompetitor(connectionMySql,connectionOracle);
            LocateRegistry.createRegistry(1099);
            //Naming.rebind("//192.168.16.13/ServidorGrupo4", model); //Tristancho Server Sala
            Naming.rebind("//127.0.0.1/ServidorGrupo4", model); //Trabajo local
            System.out.println("Servidor operando");
        } 
        catch (Exception e) 
        { 
            System.out.println("Error : " + e.getMessage()); 
            e.printStackTrace(); 
        } 
        
        
    }
    
    
}
