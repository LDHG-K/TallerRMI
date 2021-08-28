/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services.Services;


import Models.Competitor;
import Services.Interfaces.IServiceCompetitor;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.ResultSet;

/**
 *
 * @author Luis
 */
public class ServiceCompetitor  extends UnicastRemoteObject implements IServiceCompetitor{

    
    private Connection connection;
    private ResultSet resulset;
    
    
    public ServiceCompetitor(){
       
    }
    
    public ServiceCompetitor(Connection connection){
        this.connection = connection;
    }
    
    
    
    @Override
    public Competitor searchCompetitorById(long id) {
        
        
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updateCompetitor(Competitor competitor) {
        
        
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteCompetitor(long id) {
        
        
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void createCompetitor(Competitor competitor) {
        
        
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    
    
    
    
}
