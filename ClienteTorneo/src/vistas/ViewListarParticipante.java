/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import Vistas.GUIListarParticipante;
import Services.Interfaces.IServiceCompetitor;

/**
 *
 * @author usuario
 */
public class ViewListarParticipante extends UnicastRemoteObject implements IUpgradeableCompetitor, Serializable {

    private IServiceCompetitor model;
    private transient GUIListarParticipante guiListarParticipante;
    
    
    public ViewListarParticipante(IServiceCompetitor m) throws RemoteException {
        model = m;
        model.addGUICompetitorUpgradable(this);
        guiListarParticipante  = new GUIListarParticipante(model);
        guiListarParticipante.setVisible(true);
    }

    @Override
    public void change() throws RemoteException {
        guiListarParticipante.change();
    }
    
}
