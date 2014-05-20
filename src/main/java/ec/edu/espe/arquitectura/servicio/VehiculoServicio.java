/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.arquitectura.servicio;

import ec.edu.espe.arquitectura.dao.VehiculoFacade;
import ec.edu.espe.arquitectura.modelo.Vehiculo;
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
public class VehiculoServicio {

    @EJB
    private VehiculoFacade vehiculoFacade;

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void create(Vehiculo vehiculo) {
        this.vehiculoFacade.create(vehiculo);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void update(Vehiculo vehiculo) {
        this.vehiculoFacade.edit(vehiculo);
    }

    public void delete(Vehiculo vehiculo) {
        this.vehiculoFacade.remove(vehiculo);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public List<Vehiculo> findAll() {
        return this.vehiculoFacade.findAll();
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Vehiculo findByID(String vehiculo) {
        return this.vehiculoFacade.find(vehiculo);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public List<Vehiculo> findAllEstado() {
        return this.vehiculoFacade.findAllEstado();
    }
}
