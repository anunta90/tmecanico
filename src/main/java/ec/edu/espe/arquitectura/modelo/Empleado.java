/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.arquitectura.modelo;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
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
@Table(name = "empleado", catalog = "tmecanicadb", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Empleado.findAll", query = "SELECT e FROM Empleado e")})
public class Empleado implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 13)
    @Column(name = "COD_EMPLEADO_MECANICA", nullable = false, length = 13)
    private String codEmpleadoMecanica;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "CORREO_ELECTRONICO", nullable = false, length = 100)
    private String correoElectronico;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 120)
    @Column(name = "DIRECCION", nullable = false, length = 120)
    private String direccion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "NOMBRE", nullable = false, length = 50)
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "TELEFONO", nullable = false, length = 25)
    private String telefono;
    @OneToMany(mappedBy = "codEmpleadoMecanica")
    private List<HojaTrabajo> hojaTrabajoList;

    public Empleado() {
    }

    public Empleado(String codEmpleadoMecanica) {
        this.codEmpleadoMecanica = codEmpleadoMecanica;
    }

    public Empleado(String codEmpleadoMecanica, String correoElectronico, String direccion, String nombre, String telefono) {
        this.codEmpleadoMecanica = codEmpleadoMecanica;
        this.correoElectronico = correoElectronico;
        this.direccion = direccion;
        this.nombre = nombre;
        this.telefono = telefono;
    }

    public String getCodEmpleadoMecanica() {
        return codEmpleadoMecanica;
    }

    public void setCodEmpleadoMecanica(String codEmpleadoMecanica) {
        this.codEmpleadoMecanica = codEmpleadoMecanica;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @XmlTransient
    public List<HojaTrabajo> getHojaTrabajoList() {
        return hojaTrabajoList;
    }

    public void setHojaTrabajoList(List<HojaTrabajo> hojaTrabajoList) {
        this.hojaTrabajoList = hojaTrabajoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codEmpleadoMecanica != null ? codEmpleadoMecanica.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Empleado)) {
            return false;
        }
        Empleado other = (Empleado) object;
        if ((this.codEmpleadoMecanica == null && other.codEmpleadoMecanica != null) || (this.codEmpleadoMecanica != null && !this.codEmpleadoMecanica.equals(other.codEmpleadoMecanica))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.edu.espe.arquitectura.modelo.Empleado[ codEmpleadoMecanica=" + codEmpleadoMecanica + " ]";
    }
    
}
