/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AppServidor;

import Repository.ConnectionDB;
import Services.Services.ServiceCompetitor;
import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

/**
 *
 * @author Luis
 */
public class App {
    
    public static void main(String[] args) {
        
        
        try 
        { 
            //ServicioHola model = new ServicioHola();
            ConnectionDB connection = new ConnectionDB(); 
            Services.Services.ServiceCompetitor model = new ServiceCompetitor(connection);
            LocateRegistry.createRegistry(1099);
            Naming.rebind("//192.168.1.7/ServidorHolaMundo", model);
            System.out.println("Servidor operando");
        } 
        catch (Exception e) 
        { 
            System.out.println("Error : " + e.getMessage()); 
            e.printStackTrace(); 
        } 
        
        
    }
    
    
}
