/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas;

import Services.Interfaces.IServiceCompetitor;
import controllers.CompetitorController;
import java.rmi.RemoteException;
import java.util.HashMap;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 *
 * @author Luis
 */
public class GUIGraficoMatriculadosParticipates extends javax.swing.JFrame {

    private IServiceCompetitor servicio;
    private CompetitorController competitorController;
    
    /**
     * Creates new form GUIGraficoMatriculadosParticipates
     */
    public GUIGraficoMatriculadosParticipates(IServiceCompetitor ser) throws RemoteException {
        competitorController= new CompetitorController(ser);
        initComponents();
        CategoryDataset dataset = createDataset();
        JFreeChart chart = ChartFactory.createBarChart("GRAFICA DE BARRA DE CADUCIDAD POR MES"
                + "", "MES", "NUMERO DE INSCRIPCIONES VENCIDAS", dataset, PlotOrientation.VERTICAL, true, true, false);
        ChartPanel panel = new ChartPanel(chart);
        setContentPane(panel);
        
        
    }

    private CategoryDataset createDataset() throws RemoteException{
        
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        
        HashMap<String,Integer> x = competitorController.buscarEstadisticas();
        
        x.forEach((k,v) -> {
              dataset.addValue(v, k, k);  
        });
        
        return dataset;
        
    }
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
