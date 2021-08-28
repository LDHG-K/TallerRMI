/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services.Services;


import Models.Competitor;
import Repository.ConnectionDB;
import Services.Interfaces.IServiceCompetitor;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

/**
 *
 * @author Luis
 */
public class ServiceCompetitor  extends UnicastRemoteObject implements IServiceCompetitor{

    private ConnectionDB connection;
    
    public ServiceCompetitor(ConnectionDB connection)throws RemoteException{
       this.connection = connection;
    }
    
    
    @Override
    public Competitor searchCompetitorById(long id) throws RemoteException{
        
        String cad = "SELECT * FROM Participantes WHERE id ="+id;
        ResultSet res = null;
        Competitor searched = null;
        
        try {
            res = connection.executeQueryStatement(cad);
            while(res.next()){
                
                searched.setId(Integer.parseInt(res.getString(1)));
                searched.setApodo(res.getString(2));
                searched.setFechaInscripcion(res.getObject(3, LocalDate.class));
                searched.setFechaCaducidad(res.getObject(4, LocalDate.class));
                        
            }
        } catch (SQLException ex) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
        
        return searched;
        
    }

    @Override
    public void updateCompetitor(Competitor competitor)throws RemoteException {
        
        
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteCompetitor(long id)throws RemoteException {
        
        
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void createCompetitor(Competitor competitor)throws RemoteException {
        
        
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    
    
    
    
}
