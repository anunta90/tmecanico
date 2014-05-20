/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.arquitectura.dao;

import ec.edu.espe.arquitectura.modelo.Marca;
import ec.edu.espe.arquitectura.modelo.Modelo;
import ec.edu.espe.arquitectura.modelo.ModeloPK;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Ronny
 */
@Stateless
public class ModeloFacade extends AbstractFacade<Modelo> {
    @PersistenceContext(unitName = "ec.edu.espe.arquitectura_tmecanico-ejb_ejb_1PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ModeloFacade() {
        super(Modelo.class);
    }
    
    public List<Modelo> getByMarca(Marca marca)
    {
        String sql="SELECT obj FROM Modelo obj WHERE obj.marca=?1";
        Query qry = this.em.createQuery(sql);
        qry.setParameter(1, marca);
        return qry.getResultList();
        
    }
    
    public List<Modelo> getByPK(ModeloPK modeloPK)
    {
        String sql="SELECT obj FROM Modelo obj WHERE obj.modeloPK.codMarca=?1 AND obj.modeloPK.codModelo=?2";
        Query qry = this.em.createQuery(sql);
        qry.setParameter(1, modeloPK.getCodMarca());
        qry.setParameter(2, modeloPK.getCodModelo());
        return qry.getResultList();
        
    }
    
    
    
}
