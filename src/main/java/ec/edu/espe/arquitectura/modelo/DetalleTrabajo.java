/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.arquitectura.modelo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
@Table(name = "detalle_trabajo", catalog = "tmecanicadb", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DetalleTrabajo.findAll", query = "SELECT d FROM DetalleTrabajo d")})
public class DetalleTrabajo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "COD_DETALLE_TRABAJO", nullable = false)
    private Short codDetalleTrabajo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "DESCRIPCION", nullable = false, length = 200)
    private String descripcion;
    @Size(max = 13)
    @Column(name = "ESTADO", length = 13)
    private String estado;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "VALOR_ESTIMADO", precision = 8, scale = 2)
    private BigDecimal valorEstimado;
    @Column(name = "VALOR_FINAL", precision = 8, scale = 2)
    private BigDecimal valorFinal;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "detalleTrabajo")
    private List<DetalleRepuesto> detalleRepuestoList;
    @JoinColumn(name = "COD_HOJA_PARTE", referencedColumnName = "COD_HOJA_PARTE")
    @ManyToOne
    private HojaTrabajo codHojaParte;

    public DetalleTrabajo() {
    }

    public DetalleTrabajo(Short codDetalleTrabajo) {
        this.codDetalleTrabajo = codDetalleTrabajo;
    }

    public DetalleTrabajo(Short codDetalleTrabajo, String descripcion) {
        this.codDetalleTrabajo = codDetalleTrabajo;
        this.descripcion = descripcion;
    }

    public Short getCodDetalleTrabajo() {
        return codDetalleTrabajo;
    }

    public void setCodDetalleTrabajo(Short codDetalleTrabajo) {
        this.codDetalleTrabajo = codDetalleTrabajo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
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

    @XmlTransient
    public List<DetalleRepuesto> getDetalleRepuestoList() {
        return detalleRepuestoList;
    }

    public void setDetalleRepuestoList(List<DetalleRepuesto> detalleRepuestoList) {
        this.detalleRepuestoList = detalleRepuestoList;
    }

    public HojaTrabajo getCodHojaParte() {
        return codHojaParte;
    }

    public void setCodHojaParte(HojaTrabajo codHojaParte) {
        this.codHojaParte = codHojaParte;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codDetalleTrabajo != null ? codDetalleTrabajo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DetalleTrabajo)) {
            return false;
        }
        DetalleTrabajo other = (DetalleTrabajo) object;
        if ((this.codDetalleTrabajo == null && other.codDetalleTrabajo != null) || (this.codDetalleTrabajo != null && !this.codDetalleTrabajo.equals(other.codDetalleTrabajo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.edu.espe.arquitectura.modelo.DetalleTrabajo[ codDetalleTrabajo=" + codDetalleTrabajo + " ]";
    }
    
}
