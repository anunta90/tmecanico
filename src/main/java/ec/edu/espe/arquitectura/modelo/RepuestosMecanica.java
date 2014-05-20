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
import javax.persistence.Entity;
import javax.persistence.Id;
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
@Table(name = "repuestos_mecanica", catalog = "tmecanicadb", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RepuestosMecanica.findAll", query = "SELECT r FROM RepuestosMecanica r")})
public class RepuestosMecanica implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "COD_REPUESTO", nullable = false, length = 10)
    private String codRepuesto;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "NOMBRE_REPUESTO", nullable = false, length = 50)
    private String nombreRepuesto;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "TIPO_REPUESTO", nullable = false, length = 25)
    private String tipoRepuesto;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "repuestosMecanica")
    private List<DetalleRepuesto> detalleRepuestoList;

    public RepuestosMecanica() {
    }

    public RepuestosMecanica(String codRepuesto) {
        this.codRepuesto = codRepuesto;
    }

    public RepuestosMecanica(String codRepuesto, String nombreRepuesto, String tipoRepuesto) {
        this.codRepuesto = codRepuesto;
        this.nombreRepuesto = nombreRepuesto;
        this.tipoRepuesto = tipoRepuesto;
    }

    public String getCodRepuesto() {
        return codRepuesto;
    }

    public void setCodRepuesto(String codRepuesto) {
        this.codRepuesto = codRepuesto;
    }

    public String getNombreRepuesto() {
        return nombreRepuesto;
    }

    public void setNombreRepuesto(String nombreRepuesto) {
        this.nombreRepuesto = nombreRepuesto;
    }

    public String getTipoRepuesto() {
        return tipoRepuesto;
    }

    public void setTipoRepuesto(String tipoRepuesto) {
        this.tipoRepuesto = tipoRepuesto;
    }

    @XmlTransient
    public List<DetalleRepuesto> getDetalleRepuestoList() {
        return detalleRepuestoList;
    }

    public void setDetalleRepuestoList(List<DetalleRepuesto> detalleRepuestoList) {
        this.detalleRepuestoList = detalleRepuestoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codRepuesto != null ? codRepuesto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RepuestosMecanica)) {
            return false;
        }
        RepuestosMecanica other = (RepuestosMecanica) object;
        if ((this.codRepuesto == null && other.codRepuesto != null) || (this.codRepuesto != null && !this.codRepuesto.equals(other.codRepuesto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.edu.espe.arquitectura.modelo.RepuestosMecanica[ codRepuesto=" + codRepuesto + " ]";
    }
    
}
