/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.arquitectura.modelo;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Ronny
 */
@Entity
@Table(name = "detalle_repuesto", catalog = "tmecanicadb", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DetalleRepuesto.findAll", query = "SELECT d FROM DetalleRepuesto d")})
public class DetalleRepuesto implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected DetalleRepuestoPK detalleRepuestoPK;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "VALOR_ESTIMADO", precision = 8, scale = 2)
    private BigDecimal valorEstimado;
    @Column(name = "VALOR_FINAL", precision = 8, scale = 2)
    private BigDecimal valorFinal;
    @JoinColumn(name = "COD_REPUESTO", referencedColumnName = "COD_REPUESTO", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private RepuestosMecanica repuestosMecanica;
    @JoinColumn(name = "COD_DETALLE_TRABAJO", referencedColumnName = "COD_DETALLE_TRABAJO", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private DetalleTrabajo detalleTrabajo;

    public DetalleRepuesto() {
    }

    public DetalleRepuesto(DetalleRepuestoPK detalleRepuestoPK) {
        this.detalleRepuestoPK = detalleRepuestoPK;
    }

    public DetalleRepuesto(short codDetalleTrabajo, String codRepuesto) {
        this.detalleRepuestoPK = new DetalleRepuestoPK(codDetalleTrabajo, codRepuesto);
    }

    public DetalleRepuestoPK getDetalleRepuestoPK() {
        return detalleRepuestoPK;
    }

    public void setDetalleRepuestoPK(DetalleRepuestoPK detalleRepuestoPK) {
        this.detalleRepuestoPK = detalleRepuestoPK;
    }

    public BigDecimal getValorEstimado() {
        return valorEstimado;
    }

    public void setValorEstimado(BigDecimal valorEstimado) {
        this.valorEstimado = valorEstimado;
    }

    public BigDecimal getValorFinal() {
        return valorFinal;
    }

    public void setValorFinal(BigDecimal valorFinal) {
        this.valorFinal = valorFinal;
    }

    public RepuestosMecanica getRepuestosMecanica() {
        return repuestosMecanica;
    }

    public void setRepuestosMecanica(RepuestosMecanica repuestosMecanica) {
        this.repuestosMecanica = repuestosMecanica;
    }

    public DetalleTrabajo getDetalleTrabajo() {
        return detalleTrabajo;
    }

    public void setDetalleTrabajo(DetalleTrabajo detalleTrabajo) {
        this.detalleTrabajo = detalleTrabajo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (detalleRepuestoPK != null ? detalleRepuestoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DetalleRepuesto)) {
            return false;
        }
        DetalleRepuesto other = (DetalleRepuesto) object;
        if ((this.detalleRepuestoPK == null && other.detalleRepuestoPK != null) || (this.detalleRepuestoPK != null && !this.detalleRepuestoPK.equals(other.detalleRepuestoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.edu.espe.arquitectura.modelo.DetalleRepuesto[ detalleRepuestoPK=" + detalleRepuestoPK + " ]";
    }
    
}
