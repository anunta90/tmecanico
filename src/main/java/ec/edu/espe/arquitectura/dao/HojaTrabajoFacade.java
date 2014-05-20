/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.arquitectura.dao;

import ec.edu.espe.arquitectura.modelo.HojaTrabajo;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Ronny
 */
@Stateless
public class HojaTrabajoFacade extends AbstractFacade<HojaTrabajo> {
    @PersistenceContext(unitName = "ec.edu.espe.arquitectura_tmecanico-ejb_ejb_1PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public HojaTrabajoFacade() {
        super(HojaTrabajo.class);
    }
    
    public HojaTrabajo getByID(String codHojaParte)
    {
        String sql="SELECT obj FROM HojaTrabajo obj WHERE obj.codHojaParte=?1";
        Query qry = this.em.createQuery(sql);
        qry.setParameter(1, codHojaParte);
        return (HojaTrabajo) qry.getSingleResult();
    }
    
}
