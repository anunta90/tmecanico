/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.arquitectura.servicio;

import ec.edu.espe.arquitectura.dao.DetalleFacturaFacade;
import ec.edu.espe.arquitectura.modelo.DetalleFactura;
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
public class DetalleFacturaServicio {

    @EJB
    private DetalleFacturaFacade detalleFacturaFacade;

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void create(DetalleFactura detalleFactura) {
        this.detalleFacturaFacade.create(detalleFactura);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void update(DetalleFactura detalleFactura) {
        this.detalleFacturaFacade.edit(detalleFactura);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void delete(DetalleFactura detalleFactura) {
        this.detalleFacturaFacade.remove(detalleFactura);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public List<DetalleFactura> findAll() {
        return this.detalleFacturaFacade.findAll();
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void findByID(DetalleFactura detalleFactura) {
        this.detalleFacturaFacade.find(detalleFactura);
    }
}
