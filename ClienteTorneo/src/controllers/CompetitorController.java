/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import estructural.Competitor;
import java.rmi.RemoteException;
import java.util.List;
import model.IServiceCompetitor;

/**
 *
 * @author Luis
 */
public class CompetitorController {

    private final IServiceCompetitor servicio;
    
    public CompetitorController (IServiceCompetitor servicio){
        this.servicio = servicio;
    }
    
    public void agregarParticipante(Competitor participante) throws RemoteException{
        servicio.createCompetitor(participante);
    }
    
    public void editarParticipante (Competitor participante) throws RemoteException {
        servicio.updateCompetitor(participante);
    }
    
    public void eliminarParticipante(Competitor participante) throws RemoteException{
        servicio.deleteCompetitor(participante.getId());
    }
    
    
}
