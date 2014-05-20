/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.arquitectura.servicio;

import ec.edu.espe.arquitectura.dao.RepuestosMecanicaFacade;
import ec.edu.espe.arquitectura.modelo.RepuestosMecanica;
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
public class RepuestosMecanicaServcio {

    @EJB
    private RepuestosMecanicaFacade repuestosMecanicaFacade;

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void create(RepuestosMecanica repuestosMecanica) {
        this.repuestosMecanicaFacade.create(repuestosMecanica);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void update(RepuestosMecanica repuestosMecanica) {
        this.repuestosMecanicaFacade.edit(repuestosMecanica);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void delete(RepuestosMecanica repuestosMecanica) {
        this.repuestosMecanicaFacade.remove(repuestosMecanica);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public List<RepuestosMecanica> findAll() {
        return this.repuestosMecanicaFacade.findAll();
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public RepuestosMecanica findByID(String repuestosMecanica) {
        return this.repuestosMecanicaFacade.find(repuestosMecanica);
    }
}
