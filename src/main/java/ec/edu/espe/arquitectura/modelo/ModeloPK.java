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

/**
 *
 * @author Ronny
 */
@Embeddable
public class ModeloPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "COD_MARCA", nullable = false)
    private short codMarca;
    @Basic(optional = false)
    @NotNull
    @Column(name = "COD_MODELO", nullable = false)
    private short codModelo;

    public ModeloPK() {
    }

    public ModeloPK(short codMarca, short codModelo) {
        this.codMarca = codMarca;
        this.codModelo = codModelo;
    }

    public short getCodMarca() {
        return codMarca;
    }

    public void setCodMarca(short codMarca) {
        this.codMarca = codMarca;
    }

    public short getCodModelo() {
        return codModelo;
    }

    public void setCodModelo(short codModelo) {
        this.codModelo = codModelo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) codMarca;
        hash += (int) codModelo;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ModeloPK)) {
            return false;
        }
        ModeloPK other = (ModeloPK) object;
        if (this.codMarca != other.codMarca) {
            return false;
        }
        if (this.codModelo != other.codModelo) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.edu.espe.arquitectura.modelo.ModeloPK[ codMarca=" + codMarca + ", codModelo=" + codModelo + " ]";
    }
    
}
