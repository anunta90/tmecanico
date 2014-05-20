/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.arquitectura.dao;

import ec.edu.espe.arquitectura.modelo.Factura;
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
public class FacturaFacade extends AbstractFacade<Factura> {
    @PersistenceContext(unitName = "ec.edu.espe.arquitectura_tmecanico-ejb_ejb_1PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FacturaFacade() {
        super(Factura.class);
    }
    
    
   public List<Factura> findFacturaxPagar( String codPlaca)
    {
        
        String sql="SELECT obj FROM Factura obj WHERE obj.codHojaParte.codPlaca="+codPlaca+"";
        Query qry=this.em.createQuery(sql);
        return qry.getResultList();
    }
}
