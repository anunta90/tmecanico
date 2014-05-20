/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.arquitectura.servicio;

import ec.edu.espe.arquitectura.dao.ModeloFacade;
import ec.edu.espe.arquitectura.modelo.Marca;
import ec.edu.espe.arquitectura.modelo.Modelo;
import ec.edu.espe.arquitectura.modelo.ModeloPK;
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
public class ModeloServicio {

    @EJB
    private ModeloFacade modeloFacade;

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void create(Modelo modelo) {
        this.modeloFacade.create(modelo);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void update(Modelo modelo) {
        this.modeloFacade.edit(modelo);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void delete(Modelo modelo) {
        this.modeloFacade.remove(modelo);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public List<Modelo> findAll() {
        return this.modeloFacade.findAll();
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Modelo findByID(ModeloPK modelo) {
        return this.modeloFacade.find(modelo);
    }
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public List<Modelo> findByMarca(Marca marca) {
        return this.modeloFacade.getByMarca(marca);
    }
}
