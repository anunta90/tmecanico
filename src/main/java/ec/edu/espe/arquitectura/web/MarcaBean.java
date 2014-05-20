/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.arquitectura.web;

import ec.edu.espe.arquitectura.modelo.Marca;
import ec.edu.espe.arquitectura.servicio.MarcaServicio;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Ronny
 */
@ManagedBean
@ViewScoped
public class MarcaBean {

   @EJB
   MarcaServicio marcaServicio;
   
   private List<Marca> marcas;
   
   @PostConstruct
   public void postConstructor()
   {
       marcas=new ArrayList<Marca>();
       marcas=this.marcaServicio.findAll();
       
   }  
         

    public List<Marca> getMarcas() {
        return marcas;
    }

    public void setMarcas(List<Marca> marcas) {
        this.marcas = marcas;
    }
   
   
}
