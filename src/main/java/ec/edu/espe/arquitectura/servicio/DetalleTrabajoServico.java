/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.arquitectura.servicio;

import ec.edu.espe.arquitectura.dao.DetalleTrabajoFacade;
import ec.edu.espe.arquitectura.modelo.DetalleTrabajo;
import ec.edu.espe.arquitectura.modelo.HojaTrabajo;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.Query;

/**
 *
 * @author Ronny
 */
@Stateless
@LocalBean
public class DetalleTrabajoServico {

    @EJB
    private DetalleTrabajoFacade detalleTrabajoFacade;

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void create(DetalleTrabajo detalleTrabajo) {
        this.detalleTrabajoFacade.create(detalleTrabajo);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void update(DetalleTrabajo detalleTrabajo) {
        this.detalleTrabajoFacade.edit(detalleTrabajo);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void delete(DetalleTrabajo detalleTrabajo) {
        this.detalleTrabajoFacade.remove(detalleTrabajo);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public List<DetalleTrabajo> findAll() {
        return this.detalleTrabajoFacade.findAll();
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public DetalleTrabajo findByID(Short detalleTrabajo) {
        return this.detalleTrabajoFacade.find(detalleTrabajo);
    }
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public DetalleTrabajo EliminarDetallesTrabajo(String cod_HojaTrabajo) {
        if (cod_HojaTrabajo!=null) {
            return this.detalleTrabajoFacade.deleteByCodHojaTrabajo(cod_HojaTrabajo);
        }
        return null;
        
    }
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public List<DetalleTrabajo> findByHojaTrabajo(String hojaTrabajo) {
        return this.detalleTrabajoFacade.getByHojaTrabajo(hojaTrabajo);
    }
    
    
    
    
}
