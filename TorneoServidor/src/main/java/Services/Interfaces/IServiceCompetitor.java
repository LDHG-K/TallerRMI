/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services.Interfaces;

import Models.Competitor;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author Luis
 */
public interface IServiceCompetitor extends Remote {
    
    Competitor searchCompetitorById(long id) throws RemoteException;
    
    void updateCompetitor(Competitor competitor)throws RemoteException;
    
    void deleteCompetitor(long id)throws RemoteException;
    
    void createCompetitor(Competitor competitor)throws RemoteException;

}
