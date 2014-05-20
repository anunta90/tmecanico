/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.arquitectura.modelo;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Ronny
 */
@Entity
@Table(name = "modelo", catalog = "tmecanicadb", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Modelo.findAll", query = "SELECT m FROM Modelo m")})
public class Modelo implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ModeloPK modeloPK;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "NOMBRE", nullable = false, length = 50)
    private String nombre;
    @JoinColumn(name = "COD_MARCA", referencedColumnName = "COD_MARCA", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Marca marca;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "modelo")
    private List<Vehiculo> vehiculoList;

    public Modelo() {
    }

    public Modelo(ModeloPK modeloPK) {
        this.modeloPK = modeloPK;
    }

    public Modelo(ModeloPK modeloPK, String nombre) {
        this.modeloPK = modeloPK;
        this.nombre = nombre;
    }

    public Modelo(short codMarca, short codModelo) {
        this.modeloPK = new ModeloPK(codMarca, codModelo);
    }

    public ModeloPK getModeloPK() {
        return modeloPK;
    }

    public void setModeloPK(ModeloPK modeloPK) {
        this.modeloPK = modeloPK;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Marca getMarca() {
        return marca;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
    }

    @XmlTransient
    public List<Vehiculo> getVehiculoList() {
        return vehiculoList;
    }

    public void setVehiculoList(List<Vehiculo> vehiculoList) {
        this.vehiculoList = vehiculoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (modeloPK != null ? modeloPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Modelo)) {
            return false;
        }
        Modelo other = (Modelo) object;
        if ((this.modeloPK == null && other.modeloPK != null) || (this.modeloPK != null && !this.modeloPK.equals(other.modeloPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.edu.espe.arquitectura.modelo.Modelo[ modeloPK=" + modeloPK + " ]";
    }
    
}
