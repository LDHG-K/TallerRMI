/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services.Interfaces.graficInterfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author Luis
 */
public interface IUpgradeableCompetitor extends Remote {
    public void change()throws RemoteException;
}
