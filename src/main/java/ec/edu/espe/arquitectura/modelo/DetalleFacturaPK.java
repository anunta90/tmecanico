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
public class DetalleFacturaPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "COD_DETALLE_FACTURA", nullable = false)
    private short codDetalleFactura;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "COD_FACTURA", nullable = false, length = 20)
    private String codFactura;

    public DetalleFacturaPK() {
    }

    public DetalleFacturaPK(short codDetalleFactura, String codFactura) {
        this.codDetalleFactura = codDetalleFactura;
        this.codFactura = codFactura;
    }

    public short getCodDetalleFactura() {
        return codDetalleFactura;
    }

    public void setCodDetalleFactura(short codDetalleFactura) {
        this.codDetalleFactura = codDetalleFactura;
    }

    public String getCodFactura() {
        return codFactura;
    }

    public void setCodFactura(String codFactura) {
        this.codFactura = codFactura;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) codDetalleFactura;
        hash += (codFactura != null ? codFactura.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DetalleFacturaPK)) {
            return false;
        }
        DetalleFacturaPK other = (DetalleFacturaPK) object;
        if (this.codDetalleFactura != other.codDetalleFactura) {
            return false;
        }
        if ((this.codFactura == null && other.codFactura != null) || (this.codFactura != null && !this.codFactura.equals(other.codFactura))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.edu.espe.arquitectura.modelo.DetalleFacturaPK[ codDetalleFactura=" + codDetalleFactura + ", codFactura=" + codFactura + " ]";
    }
    
}
