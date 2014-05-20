/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.arquitectura.dao;

import ec.edu.espe.arquitectura.modelo.DetalleRepuesto;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Ronny
 */
@Stateless
public class DetalleRepuestoFacade extends AbstractFacade<DetalleRepuesto> {
    @PersistenceContext(unitName = "ec.edu.espe.arquitectura_tmecanico-ejb_ejb_1PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DetalleRepuestoFacade() {
        super(DetalleRepuesto.class);
    }
    
}
