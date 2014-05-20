/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.arquitectura.servicio;

import ec.edu.espe.arquitectura.dao.MarcaFacade;
import ec.edu.espe.arquitectura.modelo.Marca;
import java.util.ArrayList;
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
public class MarcaServicio {

    @EJB
    private MarcaFacade marcaFacade;

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void create(Marca marca) {
        this.marcaFacade.create(marca);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void update(Marca marca) {
        this.marcaFacade.edit(marca);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void delete(Marca marca) {
        this.marcaFacade.remove(marca);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public List<Marca> findAll() {
        List<Marca> marcas = new ArrayList<Marca>();
        marcas = this.marcaFacade.findAll();
        System.out.println("--> Marcas: " + marcas.size());
        return this.marcaFacade.findAll();
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Marca findByID(Short marca) {
        return this.marcaFacade.getByID(marca);
    }
}
