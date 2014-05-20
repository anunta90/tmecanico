/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.arquitectura.servicio;

import ec.edu.espe.arquitectura.dao.FacturaFacade;
import ec.edu.espe.arquitectura.modelo.Factura;
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
public class FacturaServicio {

    @EJB
    private FacturaFacade facturaFacade;

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void create(Factura factura) {
        this.facturaFacade.create(factura);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void update(Factura factura) {
        this.facturaFacade.edit(factura);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void delete(Factura factura) {
        this.facturaFacade.remove(factura);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public List<Factura> findAll() {
        return this.facturaFacade.findAll();
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void findByID(Factura factura) {
        this.facturaFacade.find(factura);
    }
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public List<Factura> facturasxPagar(String codPlaca)
    {
        return this.facturaFacade.findFacturaxPagar(codPlaca);
    }
}
