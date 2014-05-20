/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.arquitectura.modelo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
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
@Table(name = "factura", catalog = "tmecanicadb", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Factura.findAll", query = "SELECT f FROM Factura f")})
public class Factura implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "COD_FACTURA", nullable = false, length = 20)
    private String codFactura;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "IVA", nullable = false, precision = 4, scale = 2)
    private BigDecimal iva;
    @Basic(optional = false)
    @NotNull
    @Column(name = "SUBTOTAL", nullable = false, precision = 4, scale = 2)
    private BigDecimal subtotal;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TOTAL", nullable = false, precision = 4, scale = 2)
    private BigDecimal total;
    @JoinColumn(name = "COD_ASEGURADORA", referencedColumnName = "COD_ASEGURADORA")
    @ManyToOne
    private Aseguradora codAseguradora;
    @JoinColumn(name = "COD_HOJA_PARTE", referencedColumnName = "COD_HOJA_PARTE")
    @ManyToOne
    private HojaTrabajo codHojaParte;
    @Column(name = "FECHA_EMISION", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "factura")
    private List<DetalleFactura> detalleFacturaList;

    public Factura() {
    }

    public Factura(String codFactura) {
        this.codFactura = codFactura;
    }

    public Factura(String codFactura, BigDecimal iva, BigDecimal subtotal, BigDecimal total) {
        this.codFactura = codFactura;
        this.iva = iva;
        this.subtotal = subtotal;
        this.total = total;
    }

    public String getCodFactura() {
        return codFactura;
    }

    public void setCodFactura(String codFactura) {
        this.codFactura = codFactura;
    }

    public BigDecimal getIva() {
        return iva;
    }

    public void setIva(BigDecimal iva) {
        this.iva = iva;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public Aseguradora getCodAseguradora() {
        return codAseguradora;
    }

    public void setCodAseguradora(Aseguradora codAseguradora) {
        this.codAseguradora = codAseguradora;
    }

    public HojaTrabajo getCodHojaParte() {
        return codHojaParte;
    }

    public void setCodHojaParte(HojaTrabajo codHojaParte) {
        this.codHojaParte = codHojaParte;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    
    
    @XmlTransient
    public List<DetalleFactura> getDetalleFacturaList() {
        return detalleFacturaList;
    }

    public void setDetalleFacturaList(List<DetalleFactura> detalleFacturaList) {
        this.detalleFacturaList = detalleFacturaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codFactura != null ? codFactura.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Factura)) {
            return false;
        }
        Factura other = (Factura) object;
        if ((this.codFactura == null && other.codFactura != null) || (this.codFactura != null && !this.codFactura.equals(other.codFactura))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.edu.espe.arquitectura.modelo.Factura[ codFactura=" + codFactura + " ]";
    }
    
}
