/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.arquitectura.web;

import ec.edu.espe.arquitectura.modelo.Aseguradora;
import ec.edu.espe.arquitectura.modelo.Cliente;
import ec.edu.espe.arquitectura.modelo.Marca;
import ec.edu.espe.arquitectura.modelo.Modelo;
import ec.edu.espe.arquitectura.modelo.ModeloPK;
import ec.edu.espe.arquitectura.modelo.Vehiculo;
import ec.edu.espe.arquitectura.servicio.AseguradoraServicio;
import ec.edu.espe.arquitectura.servicio.ClienteServicio;
import ec.edu.espe.arquitectura.servicio.MarcaServicio;
import ec.edu.espe.arquitectura.servicio.ModeloServicio;
import ec.edu.espe.arquitectura.servicio.VehiculoServicio;
import java.awt.event.ActionEvent;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.apache.commons.beanutils.BeanUtils;

/**
 *
 * @author Ronny
 */
@ManagedBean
@ViewScoped
public class VehiculosBean extends BotonesBean {

    /**
     * Creates a new instance of VehiculoBean
     */
    @EJB
    private VehiculoServicio vehiculoServicio;
    @EJB
    private MarcaServicio marcaServicio;
    @EJB
    private ModeloServicio modeloServicio;
    @EJB
    private AseguradoraServicio aseguradoraServicio;
    @EJB
    private ClienteServicio clienteServicio;
    
    
    private List<Vehiculo> vehiculos;
    private List<Marca> marcas;
    private List<Short> anios;
    private List<Aseguradora> aseguradoras;
    private List<Cliente> clientes;
    private List<Modelo> modelos;
    private Vehiculo vehiculo;
    private Vehiculo vehiculoSeleccionado;
    private String codMarca;
    private String codModelo;
    private String codCliente;
    private String codAseguradora;

    @PostConstruct
    @Override
    public void postConstructor() {
        super.sinSeleccion();
        this.vehiculos = this.vehiculoServicio.findAllEstado();
        this.clientes = this.clienteServicio.findAll();
        this.aseguradoras = this.aseguradoraServicio.findAll();
        this.anios = new ArrayList<Short>();
        for (int i = 1980; i < 2015; i++) {
            anios.add(Short.valueOf(String.valueOf(i)));
        }
    }

    public void cargarTabla(ActionEvent evento) {
        Marca marca = this.marcaServicio.findByID(Short.parseShort(codMarca));
        this.modelos = this.modeloServicio.findByMarca(marca);
    }

    public void nuevo(ActionEvent evento) {
        super.crear();
        this.vehiculo = new Vehiculo();
        this.marcas = this.marcaServicio.findAll();
    }

    public void modificar(ActionEvent evento) {
        this.vehiculo = new Vehiculo();
        this.marcas = this.marcaServicio.findAll();
        try {
            this.vehiculo = (Vehiculo) BeanUtils.cloneBean(this.vehiculoSeleccionado);
            this.codAseguradora = this.vehiculo.getCodAseguradora().getCodAseguradora();
            this.codModelo = String.valueOf(this.vehiculo.getModelo().getModeloPK().getCodModelo());
            this.codMarca = String.valueOf(this.vehiculo.getModelo().getModeloPK().getCodMarca());
            this.codCliente = this.vehiculo.getCodCliente().getCodCliente();
            this.modelos = this.modeloServicio.findByMarca(this.marcaServicio.findByID(Short.parseShort(codMarca)));
            super.modificar();
        } catch (Exception ex) {
            MensajesGenericos.errorCopyProperties();
        }
    }

    public void eliminar(ActionEvent evento) throws IllegalAccessException, InstantiationException, InvocationTargetException, NoSuchMethodException {
        
        this.vehiculo = (Vehiculo) BeanUtils.cloneBean(this.vehiculoSeleccionado);
        this.vehiculo.setEstado("I");
        this.vehiculoServicio.update(this.vehiculo);
        vehiculos.remove(this.vehiculo);
        MensajesGenericos.infoEliminar("Vehiculo", this.vehiculo.getCodPlaca().toString().concat(" - ").concat(this.vehiculo.getNumChasis()), Boolean.TRUE);
        super.sinSeleccion();
    }

    public void filaSeleccionada(ActionEvent evento) {
        if (vehiculoSeleccionado instanceof Vehiculo) {
            super.seleccionadoUno();
        } else {
            super.sinSeleccion();
        }
    }

    public void guardar(ActionEvent evento) {
        try {
            if (super.getEnRegistro()) {
                this.vehiculo.setCodAseguradora(this.aseguradoraServicio.findByID(codAseguradora));
                this.vehiculo.setCodCliente(this.clienteServicio.findByID(codCliente));
                ModeloPK modeloPK = new ModeloPK();
                modeloPK.setCodMarca(Short.valueOf(codMarca));
                modeloPK.setCodModelo(Short.valueOf(codModelo));
                this.vehiculo.setModelo(this.modeloServicio.findByID(modeloPK));
                this.vehiculo.setEstado("A");
                this.vehiculoServicio.create(this.vehiculo);
                System.out.println("-->Aseguradora" + this.vehiculo.getCodAseguradora().getCodAseguradora());
                this.vehiculos.add(this.vehiculo);
                MensajesGenericos.infoCrear("Vehiculo", this.vehiculo.getCodPlaca().toString().concat(" - ").concat(this.vehiculo.getNumChasis()), Boolean.TRUE);
                super.sinSeleccion();
            } else if (super.getEnEdicion()) {
                int i = this.vehiculos.indexOf(this.vehiculo);
                vehiculo.setCodAseguradora(this.aseguradoraServicio.findByID(codAseguradora));
                vehiculo.setCodCliente(this.clienteServicio.findByID(codCliente));
                ModeloPK modeloPK = new ModeloPK();
                modeloPK.setCodMarca(Short.valueOf(codMarca));
                modeloPK.setCodModelo(Short.valueOf(codModelo));
                vehiculo.setModelo(this.modeloServicio.findByID(modeloPK));
                this.vehiculoServicio.update(vehiculo);
                //this.vehiculoServicio.update(this.vehiculo);
                vehiculos.set(i, this.vehiculo);
                MensajesGenericos.infoCrear("Vehiculo", this.vehiculo.getCodPlaca().toString().concat(" - ").concat(this.vehiculo.getNumChasis()), Boolean.TRUE);
                super.sinSeleccion();
            }
        } catch (Exception e) {
            MensajesGenericos.errorGuardar();
        }

    }

    @Override
    public void cancelar() {
        if (super.getEnRegistro()) {
            super.sinSeleccion();
        } else {
            super.seleccionadoUno();
        }
        this.vehiculo = new Vehiculo();
        MensajesGenericos.infoCancelar();
    }

    public List<Vehiculo> getVehiculos() {
        return vehiculos;
    }

    public List<Short> getAnios() {
        return anios;
    }

    public void setAnios(List<Short> anios) {
        this.anios = anios;
    }

    public void setVehiculos(List<Vehiculo> vehiculos) {
        this.vehiculos = vehiculos;
    }

    public List<Marca> getMarcas() {
        return marcas;
    }

    public void setMarcas(List<Marca> marcas) {
        this.marcas = marcas;
    }

    public List<Aseguradora> getAseguradoras() {
        return aseguradoras;
    }

    public void setAseguradoras(List<Aseguradora> aseguradoras) {
        this.aseguradoras = aseguradoras;
    }

    public List<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
    }

    public List<Modelo> getModelos() {
        return modelos;
    }

    public void setModelos(List<Modelo> modelos) {
        this.modelos = modelos;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    public Vehiculo getVehiculoSeleccionado() {
        return vehiculoSeleccionado;
    }

    public void setVehiculoSeleccionado(Vehiculo vehiculoSeleccionado) {
        this.vehiculoSeleccionado = vehiculoSeleccionado;
    }

    public String getCodMarca() {
        return codMarca;
    }

    public void setCodMarca(String codMarca) {
        this.codMarca = codMarca;
    }

    public String getCodModelo() {
        return codModelo;
    }

    public void setCodModelo(String codModelo) {
        this.codModelo = codModelo;
    }

    public String getCodCliente() {
        return codCliente;
    }

    public void setCodCliente(String codCliente) {
        this.codCliente = codCliente;
    }

    public String getCodAseguradora() {
        return codAseguradora;
    }

    public void setCodAseguradora(String codAseguradora) {
        this.codAseguradora = codAseguradora;
    }
}