/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services.Interfaces;

import Models.Competitor;

/**
 *
 * @author Luis
 */
public interface IServiceCompetitor {
    
    Competitor searchCompetitorById(long id);
    
    void updateCompetitor(Competitor competitor);
    
    void deleteCompetitor(long id);
    
    void createCompetitor(Competitor competitor);

}
