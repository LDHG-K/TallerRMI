package AppCliente;

import java.rmi.Naming;
import java.time.LocalDate;

import Models.Competitor;
import Services.Interfaces.IServiceCompetitor;

/**
 * Hello world!
 *
 */
public class AppCliente 
{
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try 
        { 
           IServiceCompetitor obj = (IServiceCompetitor) Naming.lookup( "//" + 
                "127.0.0.1" + "/ServidorHolaMundo");  
                Competitor competitor = new Competitor(1, "apodo", LocalDate.now(), LocalDate.now());     
                System.out.println(competitor.getApodo());
           obj.createCompetitor(competitor);
             
            
             
        } 
        catch (Exception e) 
        { 
           System.out.println("Error: " + e.getMessage()); 
           e.printStackTrace(); 
        } 
    }
}
