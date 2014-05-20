/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.arquitectura.dao;

import ec.edu.espe.arquitectura.modelo.Marca;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Ronny
 */
@Stateless
public class MarcaFacade extends AbstractFacade<Marca> {
    @PersistenceContext(unitName = "ec.edu.espe.arquitectura_tmecanico-ejb_ejb_1PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MarcaFacade() {
        super(Marca.class);
    }

    public Marca getByID(Short marca)
    {
        String sql="SELECT obj FROM Marca obj WHERE obj.codMarca=?1";
        Query qry = this.em.createQuery(sql);
        qry.setParameter(1, marca);
        return (Marca) qry.getSingleResult();
    }
    
}
