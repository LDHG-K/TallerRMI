/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services.Services;


import Models.Competitor;
import Repository.ConnectionDB;
import Services.Interfaces.IServiceCompetitor;
import Services.Interfaces.graficInterfaces.IUpgradeableCompetitor;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.sql.Date;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Luis
 */
public class ServiceCompetitor  extends UnicastRemoteObject implements IServiceCompetitor{

     private ArrayList<IUpgradeableCompetitor> guisCompetitors;
    
    
    private ConnectionDB connection;
    
    public ServiceCompetitor(ConnectionDB connection)throws RemoteException{
       guisCompetitors = new ArrayList<IUpgradeableCompetitor>();
        this.connection = connection;
    }
    
    
    @Override
    public Competitor searchCompetitorById(long id) throws RemoteException{
        
        String cad = "SELECT * FROM participante WHERE id ="+id;
        ResultSet res = null;
        Competitor searched = null;
        
        try {
            res = connection.executeQueryStatement(cad);
            while(res.next()){
                
                searched = new Competitor(id, res.getString(2), res.getObject(3,Date.class), res.getObject(4,Date.class));

                
                        
            }
        } catch (SQLException ex) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
        
        return searched;
        
    }

    @Override
    public void updateCompetitor(Competitor competitor)throws RemoteException {
        
        String cad = "UPDATE participante SET apodo ='"+competitor.getApodo()+
                "', fecha_inscripcion ='" + competitor.getFechaInscripcion()+
                "', fecha_caducidad ='"+competitor.getFechaCaducidad()+
                "' WHERE id = "+competitor.getId();
        try {
            if (!connection.executeUpdateStatement(cad)) {
                throw new Exception("Operacion no ejecutada");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
        
    }

    @Override
    public void deleteCompetitor(long id)throws RemoteException {
        
        
        String cad = "DELETE FROM participante WHERE id="+id;
        try {
            if (!connection.executeUpdateStatement(cad)) {
                throw new Exception("Operacion no ejecutada");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    }

    @Override
    public void createCompetitor(Competitor competitor)throws RemoteException {
        
        
        String cad = "INSERT INTO participante VALUES("+  competitor.getId()   +",'"
                                                + competitor.getApodo()+"','"
                                                + competitor.getFechaInscripcion()+"','"
                                                + competitor.getFechaCaducidad()+ "')";

        

        try {
            if (!connection.executeUpdateStatement(cad)) {
                throw new Exception("Operacion no ejecutada");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    }

    @Override
    public List<Competitor> searchAll() throws RemoteException {
        
        try {
            String cad = "SELECT * FROM participante";
            ResultSet res;
            ArrayList<Competitor> competitors = new ArrayList<>();

            int id;
            String apodo;
            Date fechaInscripcion;
            Date fechaCaducidad;

            res = connection.executeQueryStatement(cad);
            while(res.next()){

                id = Integer.parseInt(res.getString(1));
                apodo = res.getString(2);
                fechaInscripcion = res.getObject(3, Date.class);
                fechaCaducidad = res.getObject(4, Date.class);

                competitors.add(new Competitor(id, apodo, fechaInscripcion, fechaCaducidad));
                              
            }
            return competitors;
        } catch (SQLException ex) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
        
    }

    @Override
    public void addGUICompetitorUpgradable(IUpgradeableCompetitor guiA) throws RemoteException {
        guisCompetitors.add(guiA);
    }
    
    
    @Override
    public HashMap searchStatistics() {
        
    try {
            String cad = "select MonthName(fecha_caducidad), count(*)\n" +
                    "  from participante\n" +
                    " where fecha_caducidad >= makedate(year(curdate()), 1)\n" +
                    "   and fecha_caducidad < makedate(year(curdate()) + 1, 1)\n" +
                    " group by MonthName(fecha_caducidad)";
            
            ResultSet res;
            
            HashMap <String, Integer> map = new HashMap <String, Integer> ();
            
            
            Integer valor;
            String llave;

            res = connection.executeQueryStatement(cad);
            while(res.next()){

                llave = res.getString(1);
                valor = Integer.parseInt(res.getString(2));
                
                map.put(llave, valor);
            }
            return map;
        } catch (SQLException ex) {
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
