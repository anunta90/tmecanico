/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.arquitectura.servicio;

import ec.edu.espe.arquitectura.dao.HojaTrabajoFacade;
import ec.edu.espe.arquitectura.modelo.HojaTrabajo;
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
public class HojaTrabajoServicio {

    @EJB
    private HojaTrabajoFacade hojaTrabajoFacade;

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void create(HojaTrabajo hojaTrabajo) {
        this.hojaTrabajoFacade.create(hojaTrabajo);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void update(HojaTrabajo hojaTrabajo) {
        this.hojaTrabajoFacade.edit(hojaTrabajo);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void delete(HojaTrabajo hojaTrabajo) {
        this.hojaTrabajoFacade.remove(hojaTrabajo);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public List<HojaTrabajo> findAll() {
        return this.hojaTrabajoFacade.findAll();
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public HojaTrabajo findByID(String hojaTrabajo) {
        return this.hojaTrabajoFacade.getByID(hojaTrabajo);
    }
}
