/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.arquitectura.servicio;

import ec.edu.espe.arquitectura.dao.AseguradoraFacade;
import ec.edu.espe.arquitectura.modelo.Aseguradora;
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
public class AseguradoraServicio {

    @EJB
    private AseguradoraFacade aseguradoraFacade;

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void create(Aseguradora aseguradora) {
        this.aseguradoraFacade.create(aseguradora);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void update(Aseguradora aseguradora) {
        this.aseguradoraFacade.edit(aseguradora);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void delete(Aseguradora aseguradora) {
        this.aseguradoraFacade.remove(aseguradora);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public List<Aseguradora> findAll() {
        return this.aseguradoraFacade.findAll();
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Aseguradora findByID(String aseguradora) {
        return this.aseguradoraFacade.find(aseguradora);
    }
}
