/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.arquitectura.dao;

import ec.edu.espe.arquitectura.modelo.DetalleTrabajo;
import ec.edu.espe.arquitectura.modelo.HojaTrabajo;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Ronny
 */
@Stateless
public class DetalleTrabajoFacade extends AbstractFacade<DetalleTrabajo> {
    @PersistenceContext(unitName = "ec.edu.espe.arquitectura_tmecanico-ejb_ejb_1PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DetalleTrabajoFacade() {
        super(DetalleTrabajo.class);
    }
    
    public DetalleTrabajo deleteByCodHojaTrabajo(String cod_HojaTrabajo) {
        Short cod=Short.valueOf(cod_HojaTrabajo);
        Query qry = em.createQuery("DELETE FROM DetalleTrabajo obj WHERE obj.codHojaParte="+cod+"");
        qry.executeUpdate();
        return null;
    }
    
    public List<DetalleTrabajo> getByHojaTrabajo(String hojaTrabajo)
    {
        String sql="SELECT obj FROM DetalleTrabajo obj WHERE obj.codHojaParte="+hojaTrabajo+"";
        Query qry = this.em.createQuery(sql);
        return qry.getResultList();
        
    }

    @Override
    public void create(DetalleTrabajo entity) {
        super.create(entity); //To change body of generated methods, choose Tools | Templates.
        em.flush();
    }
   
    
    
    
}
