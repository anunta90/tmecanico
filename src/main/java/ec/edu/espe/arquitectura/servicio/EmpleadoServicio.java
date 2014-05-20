/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.arquitectura.servicio;

import ec.edu.espe.arquitectura.dao.EmpleadoFacade;
import ec.edu.espe.arquitectura.modelo.Empleado;
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
public class EmpleadoServicio {

    @EJB
    private EmpleadoFacade empleadoFacade;

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void create(Empleado empleado) {
        this.empleadoFacade.create(empleado);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void update(Empleado empleado) {
        this.empleadoFacade.edit(empleado);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void delete(Empleado empleado) {
        this.empleadoFacade.remove(empleado);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public List<Empleado> findAll() {
        return this.empleadoFacade.findAll();
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Empleado findByID(String empleado) {
        return this.empleadoFacade.find(empleado);
    }
}
