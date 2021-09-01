/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services.Interfaces;

import Models.Competitor;
import Services.Interfaces.graficInterfaces.IUpgradeableCompetitor;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author Luis
 */
public interface IServiceCompetitor extends Remote {
    
    Competitor searchCompetitorById(long id) throws RemoteException;
    
    void updateCompetitor(Competitor competitor)throws RemoteException;
    
    void deleteCompetitor(long id)throws RemoteException;
    
    void createCompetitor(Competitor competitor)throws RemoteException;
    
    List<Competitor> searchAll()throws RemoteException;
    
    public void addGUICompetitorUpgradable(IUpgradeableCompetitor guiA) throws RemoteException;

}
