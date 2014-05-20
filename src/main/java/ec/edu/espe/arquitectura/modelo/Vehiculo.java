/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.arquitectura.modelo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
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
@Table(name = "vehiculo", catalog = "tmecanicadb", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Vehiculo.findAll", query = "SELECT v FROM Vehiculo v")})
public class Vehiculo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "COD_PLACA", nullable = false, length = 8)
    private String codPlaca;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ANIO", nullable = false)
    private short anio;
    @Size(max = 13)
    @Column(name = "COLOR", length = 13)
    private String color;
    @Column(name = "KILOMETRAJE")
    private Integer kilometraje;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "MINIMO_DEDUCIBLE", precision = 8, scale = 2)
    private BigDecimal minimoDeducible;
    @Column(name = "MONTO_MAXIMO_COBERTURA", precision = 8, scale = 2)
    private BigDecimal montoMaximoCobertura;
    @Size(max = 50)
    @Column(name = "NUM_CHASIS", length = 50)
    private String numChasis;
    @Size(max = 50)
    @Column(name = "NUM_MOTOR", length = 50)
    private String numMotor;
    @Column(name = "PORCENTAJE_DEDUCIBLE", precision = 5, scale = 2)
    private BigDecimal porcentajeDeducible;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "ESTADO", nullable = false, length = 1)
    private String estado;
    @OneToMany(mappedBy = "codPlaca")
    private List<HojaTrabajo> hojaTrabajoList;
    @JoinColumns({
        @JoinColumn(name = "COD_MARCA", referencedColumnName = "COD_MARCA", nullable = false),
        @JoinColumn(name = "COD_MODELO", referencedColumnName = "COD_MODELO", nullable = false)})
    @ManyToOne(optional = false)
    private Modelo modelo;
    @JoinColumn(name = "COD_ASEGURADORA", referencedColumnName = "COD_ASEGURADORA")
    @ManyToOne
    private Aseguradora codAseguradora;
    @JoinColumn(name = "COD_CLIENTE", referencedColumnName = "COD_CLIENTE", nullable = false)
    @ManyToOne(optional = false)
    private Cliente codCliente;

    public Vehiculo() {
    }

    public Vehiculo(String codPlaca) {
        this.codPlaca = codPlaca;
    }

    public Vehiculo(String codPlaca, short anio, String estado) {
        this.codPlaca = codPlaca;
        this.anio = anio;
        this.estado = estado;
    }

    public String getCodPlaca() {
        return codPlaca;
    }

    public void setCodPlaca(String codPlaca) {
        this.codPlaca = codPlaca;
    }

    public short getAnio() {
        return anio;
    }

    public void setAnio(short anio) {
        this.anio = anio;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Integer getKilometraje() {
        return kilometraje;
    }

    public void setKilometraje(Integer kilometraje) {
        this.kilometraje = kilometraje;
    }

    public BigDecimal getMinimoDeducible() {
        return minimoDeducible;
    }

    public void setMinimoDeducible(BigDecimal minimoDeducible) {
        this.minimoDeducible = minimoDeducible;
    }

    public BigDecimal getMontoMaximoCobertura() {
        return montoMaximoCobertura;
    }

    public void setMontoMaximoCobertura(BigDecimal montoMaximoCobertura) {
        this.montoMaximoCobertura = montoMaximoCobertura;
    }

    public String getNumChasis() {
        return numChasis;
    }

    public void setNumChasis(String numChasis) {
        this.numChasis = numChasis;
    }

    public String getNumMotor() {
        return numMotor;
    }

    public void setNumMotor(String numMotor) {
        this.numMotor = numMotor;
    }

    public BigDecimal getPorcentajeDeducible() {
        return porcentajeDeducible;
    }

    public void setPorcentajeDeducible(BigDecimal porcentajeDeducible) {
        this.porcentajeDeducible = porcentajeDeducible;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @XmlTransient
    public List<HojaTrabajo> getHojaTrabajoList() {
        return hojaTrabajoList;
    }

    public void setHojaTrabajoList(List<HojaTrabajo> hojaTrabajoList) {
        this.hojaTrabajoList = hojaTrabajoList;
    }

    public Modelo getModelo() {
        return modelo;
    }

    public void setModelo(Modelo modelo) {
        this.modelo = modelo;
    }

    public Aseguradora getCodAseguradora() {
        return codAseguradora;
    }

    public void setCodAseguradora(Aseguradora codAseguradora) {
        this.codAseguradora = codAseguradora;
    }

    public Cliente getCodCliente() {
        return codCliente;
    }

    public void setCodCliente(Cliente codCliente) {
        this.codCliente = codCliente;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codPlaca != null ? codPlaca.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Vehiculo)) {
            return false;
        }
        Vehiculo other = (Vehiculo) object;
        if ((this.codPlaca == null && other.codPlaca != null) || (this.codPlaca != null && !this.codPlaca.equals(other.codPlaca))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.edu.espe.arquitectura.modelo.Vehiculo[ codPlaca=" + codPlaca + " ]";
    }
    
}
