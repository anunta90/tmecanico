/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.arquitectura.servicio;

import ec.edu.espe.arquitectura.dao.ClienteFacade;
import ec.edu.espe.arquitectura.modelo.Cliente;
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
public class ClienteServicio {

    @EJB
    private ClienteFacade clienteFacade;

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void create(Cliente cliente) {
        this.clienteFacade.create(cliente);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void update(Cliente cliente) {
        this.clienteFacade.edit(cliente);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void delete(Cliente cliente) {
        this.clienteFacade.remove(cliente);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public List<Cliente> findAll() {
        return this.clienteFacade.findAll();
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Cliente findByID(String cliente) {
        return this.clienteFacade.find(cliente);

    }
}
