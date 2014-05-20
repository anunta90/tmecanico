/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.arquitectura.dao;

import ec.edu.espe.arquitectura.modelo.RepuestosMecanica;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Ronny
 */
@Stateless
public class RepuestosMecanicaFacade extends AbstractFacade<RepuestosMecanica> {

    @PersistenceContext(unitName = "ec.edu.espe.arquitectura_tmecanico-ejb_ejb_1PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RepuestosMecanicaFacade() {
        super(RepuestosMecanica.class);
    }

    public RepuestosMecanica getByID(String codigo) {
        String sql="SELECT obj FROM RepuestosMecanica obj WHERE obj.codRepuesto=?1";
        Query qry = this.em.createQuery(sql);
        qry.setParameter(1, codigo);
        return (RepuestosMecanica) qry.getSingleResult();
    }
}
