/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.arquitectura.modelo;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Ronny
 */
@Embeddable
public class DetalleRepuestoPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "COD_DETALLE_TRABAJO", nullable = false)
    private short codDetalleTrabajo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "COD_REPUESTO", nullable = false, length = 10)
    private String codRepuesto;

    public DetalleRepuestoPK() {
    }

    public DetalleRepuestoPK(short codDetalleTrabajo, String codRepuesto) {
        this.codDetalleTrabajo = codDetalleTrabajo;
        this.codRepuesto = codRepuesto;
    }

    public short getCodDetalleTrabajo() {
        return codDetalleTrabajo;
    }

    public void setCodDetalleTrabajo(short codDetalleTrabajo) {
        this.codDetalleTrabajo = codDetalleTrabajo;
    }

    public String getCodRepuesto() {
        return codRepuesto;
    }

    public void setCodRepuesto(String codRepuesto) {
        this.codRepuesto = codRepuesto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) codDetalleTrabajo;
        hash += (codRepuesto != null ? codRepuesto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DetalleRepuestoPK)) {
            return false;
        }
        DetalleRepuestoPK other = (DetalleRepuestoPK) object;
        if (this.codDetalleTrabajo != other.codDetalleTrabajo) {
            return false;
        }
        if ((this.codRepuesto == null && other.codRepuesto != null) || (this.codRepuesto != null && !this.codRepuesto.equals(other.codRepuesto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.edu.espe.arquitectura.modelo.DetalleRepuestoPK[ codDetalleTrabajo=" + codDetalleTrabajo + ", codRepuesto=" + codRepuesto + " ]";
    }
    
}
