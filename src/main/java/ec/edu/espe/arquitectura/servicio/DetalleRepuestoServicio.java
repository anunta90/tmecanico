/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.arquitectura.servicio;

import ec.edu.espe.arquitectura.dao.DetalleRepuestoFacade;
import ec.edu.espe.arquitectura.modelo.DetalleRepuesto;
import ec.edu.espe.arquitectura.modelo.DetalleRepuestoPK;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

/**
 *
 * @author Ronny
 */
@Stateless
@LocalBean
public class DetalleRepuestoServicio {

    @EJB
    private DetalleRepuestoFacade detalleRepuestoFacade;

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void create(DetalleRepuesto detalleRepuesto) {
        this.detalleRepuestoFacade.create(detalleRepuesto);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void update(DetalleRepuesto detalleRepuesto) {
        this.detalleRepuestoFacade.edit(detalleRepuesto);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void delete(DetalleRepuesto detalleRepuesto) {
        this.detalleRepuestoFacade.remove(detalleRepuesto);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public List<DetalleRepuesto> findAll() {
        return this.detalleRepuestoFacade.findAll();
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void findByID(String detalleRepuesto) {
        this.detalleRepuestoFacade.find(detalleRepuesto);
    }
}
