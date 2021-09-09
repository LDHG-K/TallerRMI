/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AppCliente;

import Vistas.GUIPrincipal;
import java.rmi.Naming;
import Services.Interfaces.IServiceCompetitor;

/**
 *
 * @author usuario
 */
public class AppCliente {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        //String rmiRegistryHost = "192.168.16.13"; //Servidor Tristancho
        String rmiRegistryHost = "127.0.0.1";
     	try {
            if (args.length > 0) {
                rmiRegistryHost = args[0];
            }
            IServiceCompetitor model = (IServiceCompetitor) Naming.lookup("//" + rmiRegistryHost +"/ServidorGrupo4");
            //IServiciosEstudiante model = (IServiciosEstudiante) Naming.lookup("//" + rmiRegistryHost +"/AplicacionEstudiante");
            if(model == null){
                System.out.println("Error... Cliente ");
      		return;	
            }
            GUIPrincipal gui = new GUIPrincipal(model);
            gui.setVisible(true);
    	} catch (Exception e) {
            System.out.println("Error...  " + e);
    	}

    }
    
}
