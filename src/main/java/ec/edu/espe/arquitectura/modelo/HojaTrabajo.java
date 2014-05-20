/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.arquitectura.modelo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Ronny
 */
@Entity
@Table(name = "hoja_trabajo", catalog = "tmecanicadb", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "HojaTrabajo.findAll", query = "SELECT h FROM HojaTrabajo h")})
public class HojaTrabajo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "COD_HOJA_PARTE", nullable = false, length = 25)
    private String codHojaParte;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @OneToMany(mappedBy = "codHojaParte")
    private List<Factura> facturaList;
    @OneToMany(mappedBy = "codHojaParte")
    private List<DetalleTrabajo> detalleTrabajoList;
    @JoinColumn(name = "COD_PLACA", referencedColumnName = "COD_PLACA")
    @ManyToOne
    private Vehiculo codPlaca;
    @JoinColumn(name = "COD_EMPLEADO_MECANICA", referencedColumnName = "COD_EMPLEADO_MECANICA")
    @ManyToOne
    private Empleado codEmpleadoMecanica;

    public HojaTrabajo() {
    }

    public HojaTrabajo(String codHojaParte) {
        this.codHojaParte = codHojaParte;
    }

    public HojaTrabajo(String codHojaParte, Date fecha) {
        this.codHojaParte = codHojaParte;
        this.fecha = fecha;
    }

    public String getCodHojaParte() {
        return codHojaParte;
    }

    public void setCodHojaParte(String codHojaParte) {
        this.codHojaParte = codHojaParte;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    @XmlTransient
    public List<Factura> getFacturaList() {
        return facturaList;
    }

    public void setFacturaList(List<Factura> facturaList) {
        this.facturaList = facturaList;
    }

    @XmlTransient
    public List<DetalleTrabajo> getDetalleTrabajoList() {
        return detalleTrabajoList;
    }

    public void setDetalleTrabajoList(List<DetalleTrabajo> detalleTrabajoList) {
        this.detalleTrabajoList = detalleTrabajoList;
    }

    public Vehiculo getCodPlaca() {
        return codPlaca;
    }

    public void setCodPlaca(Vehiculo codPlaca) {
        this.codPlaca = codPlaca;
    }

    public Empleado getCodEmpleadoMecanica() {
        return codEmpleadoMecanica;
    }

    public void setCodEmpleadoMecanica(Empleado codEmpleadoMecanica) {
        this.codEmpleadoMecanica = codEmpleadoMecanica;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codHojaParte != null ? codHojaParte.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HojaTrabajo)) {
            return false;
        }
        HojaTrabajo other = (HojaTrabajo) object;
        if ((this.codHojaParte == null && other.codHojaParte != null) || (this.codHojaParte != null && !this.codHojaParte.equals(other.codHojaParte))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.edu.espe.arquitectura.modelo.HojaTrabajo[ codHojaParte=" + codHojaParte + " ]";
    }
    
}
