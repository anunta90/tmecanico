/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.arquitectura.web;

import ec.edu.espe.arquitectura.modelo.DetalleRepuesto;
import ec.edu.espe.arquitectura.modelo.DetalleRepuestoPK;
import ec.edu.espe.arquitectura.modelo.DetalleTrabajo;
import ec.edu.espe.arquitectura.modelo.Empleado;
import ec.edu.espe.arquitectura.modelo.HojaTrabajo;
import ec.edu.espe.arquitectura.modelo.RepuestosMecanica;
import ec.edu.espe.arquitectura.modelo.Vehiculo;
import ec.edu.espe.arquitectura.servicio.DetalleRepuestoServicio;
import ec.edu.espe.arquitectura.servicio.DetalleTrabajoServico;
import ec.edu.espe.arquitectura.servicio.EmpleadoServicio;
import ec.edu.espe.arquitectura.servicio.HojaTrabajoServicio;
import ec.edu.espe.arquitectura.servicio.RepuestosMecanicaServcio;
import ec.edu.espe.arquitectura.servicio.VehiculoServicio;
import java.awt.event.ActionEvent;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
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
public class HojaTrabajoBean extends BotonesBean implements Serializable {

    //Create a new instance of HojaTrabajoBean
    @EJB
    private HojaTrabajoServicio hojaTrabajoServicio;
    @EJB
    private VehiculoServicio vehiculoServicio;
    @EJB
    private EmpleadoServicio empleadoServicio;
    @EJB
    private DetalleTrabajoServico detalleTrabajoServicio;
    @EJB
    private DetalleRepuestoServicio detalleRepuestoServicio;
    @EJB
    private RepuestosMecanicaServcio repuestosMecanicaServicio;
    private List<HojaTrabajo> hojas;
    private List<Vehiculo> vehiculos;
    private List<Empleado> empleados;
    private List<DetalleTrabajo> detallesTrabajos;
    private List<DetalleRepuesto> detallesRespuestos;
    private List<RepuestosMecanica> repuestosMecanica;
    private HojaTrabajo hojaTrabajo;
    private HojaTrabajo hojaTrabajoSeleccionado;
    private DetalleTrabajo detalleTrabajo;
    private DetalleTrabajo detalleTrabajoSeleccionado;
    private DetalleRepuesto detalleRepuesto;
    private String codEmpleadoMecanica;
    private String codPlaca;
    private String codDetalleTrabajo;
    private String codDetalleRepuesto;
    private String codRepuesto;
    private String codigo;
    private String descripcion;
    private String vEstimado;
    private String vFinal;
    private String vEstimadoR;
    private String vFinalR;

    @PostConstruct
    @Override
    public void postConstructor() {
        super.sinSeleccion();
        this.hojas = this.hojaTrabajoServicio.findAll();
        this.vehiculos = this.vehiculoServicio.findAll();
        this.empleados = this.empleadoServicio.findAll();
        this.repuestosMecanica = this.repuestosMecanicaServicio.findAll();
    }

    public void nuevo(ActionEvent evento) {
        super.crear();
        this.codigo = "";
        this.descripcion = "";
        this.vEstimado = "";
        this.vFinal = "";
        this.vEstimadoR = "";
        this.vFinalR = "";
        this.hojaTrabajo = new HojaTrabajo();
        this.detalleTrabajo = new DetalleTrabajo();
        this.detalleRepuesto = new DetalleRepuesto();
        this.detallesTrabajos = this.detalleTrabajoServicio.findAll();
        this.detallesRespuestos = this.detalleRepuestoServicio.findAll();
        this.repuestosMecanica = this.repuestosMecanicaServicio.findAll();
        this.detallesTrabajos.clear();
        this.detallesRespuestos.clear();
    }

    public void agregar(ActionEvent evento) {
        this.detalleTrabajo.setCodDetalleTrabajo(Short.parseShort(this.codigo));
        this.detalleTrabajo.setDescripcion(descripcion);
        this.detalleTrabajo.setEstado("ACT");
        this.detalleTrabajo.setValorEstimado(BigDecimal.valueOf(Long.valueOf(vEstimado)));
        this.detalleTrabajo.setValorFinal(BigDecimal.valueOf(Long.valueOf(vFinal)));
        this.detallesTrabajos.add(detalleTrabajo);
        //System.out.println("ListaDetalleTrabajo:" + detallesTrabajos.size());
        this.detalleTrabajo = new DetalleTrabajo();
    }

    public void abrirDialog(ActionEvent evento) {
        this.codigo = "";
        this.descripcion = "";
        this.vEstimado = "";
        this.vFinal = "";

    }

    public void abrirDialogR(ActionEvent evento) {
        this.vEstimadoR = "";
        this.vFinalR = "";

    }

    //Generacion de Lista de Repuestos
    public void agregarRepuesto(ActionEvent evento) throws IllegalAccessException, InstantiationException, InvocationTargetException, NoSuchMethodException {

        if (detallesRespuestos != null || detalleTrabajo != null) {
            this.vEstimadoR = "";
            this.vFinalR = "";
            this.detallesRespuestos.clear();
        }
        this.detalleTrabajo = (DetalleTrabajo) BeanUtils.cloneBean(this.detalleTrabajoSeleccionado);
        if (!detalleTrabajo.getCodDetalleTrabajo().toString().isEmpty()) {
            this.detalleTrabajo = this.detalleTrabajoServicio.findByID(detalleTrabajo.getCodDetalleTrabajo());
            super.sinSeleccion();
        }

        DetalleRepuestoPK detalleRepuestoPK = new DetalleRepuestoPK();
        detalleRepuestoPK.setCodDetalleTrabajo(Short.valueOf(detalleTrabajo.getCodDetalleTrabajo()));
        //detalleRepuestoPK.setCodDetalleTrabajo(Short.valueOf(detalleTrabajo.getCodDetalleTrabajo()));
        detalleRepuestoPK.setCodRepuesto(codRepuesto);
        if (detalleRepuesto == null) {
            detalleRepuesto = new DetalleRepuesto();
        }
        this.detalleRepuesto.setDetalleRepuestoPK(detalleRepuestoPK);

        this.detalleRepuesto.setRepuestosMecanica(this.repuestosMecanicaServicio.findByID(this.codRepuesto));
        this.detalleRepuesto.setValorEstimado(BigDecimal.valueOf(Double.valueOf(vEstimadoR)));
        this.detalleRepuesto.setValorFinal(BigDecimal.valueOf(Double.valueOf(vFinalR)));
        if (this.detallesRespuestos == null) {
            this.detallesRespuestos = new ArrayList<DetalleRepuesto>();

        }
        this.detallesRespuestos.add(this.detalleRepuesto);
        System.out.println("Codigo Repuesto:" + detalleRepuesto.getDetalleRepuestoPK().getCodRepuesto());
        System.out.println("Codigo de la Hoja de Trabajo:" + detalleRepuesto.getDetalleRepuestoPK().getCodDetalleTrabajo());
        System.out.println("Valor Es;" + detalleRepuesto.getValorEstimado());
        System.out.println("Valor Fin:" + detalleRepuesto.getValorFinal());
        System.out.println("ListaR:" + detallesRespuestos.size());
        this.detalleRepuesto = new DetalleRepuesto();
        GuardarRepuestos();
    }

    public void guardar(ActionEvent evento) {

        try {
            this.hojaTrabajo.setCodEmpleadoMecanica(this.empleadoServicio.findByID(codEmpleadoMecanica));
            this.hojaTrabajo.setCodPlaca(this.vehiculoServicio.findByID(codPlaca));
            this.hojaTrabajoServicio.create(this.hojaTrabajo);
            System.out.println("codigo hoja de trabajo" + this.hojaTrabajo.getCodHojaParte());
            System.out.printf("Hoja de Trabajo: %s", hojaTrabajoServicio.findByID(hojaTrabajo.getCodHojaParte()));
            this.hojas.add(this.hojaTrabajo);
            MensajesGenericos.infoCrear("Cabecera de Hoja de Trabajo", this.hojaTrabajo.getFecha().toString().concat(" - ").concat(this.hojaTrabajo.getCodEmpleadoMecanica().getNombre().toString()).concat("-").concat(this.hojaTrabajo.getCodHojaParte().toString()), Boolean.TRUE);

            if (!detallesTrabajos.isEmpty()) {
                for (int i = 0; i < this.detallesTrabajos.size(); i++) {
                    this.detallesTrabajos.get(i).setCodHojaParte(this.hojaTrabajo);
                    System.out.println("Codigo Detalle Trabajo" + detallesTrabajos.get(i).getCodDetalleTrabajo());
                    this.detalleTrabajoServicio.create(detallesTrabajos.get(i));

                    //this.detalleRepuestoServicio.create(detallesRespuestos.get(i));
                }
//                if (!detallesRespuestos.isEmpty()) {
//                    
//                    for(DetalleRepuesto detalleRepuesto : detallesRespuestos) {
//                       
//                       detalleRepuesto.setDetalleRepuestoPK(new DetalleRepuestoPK(detalleTrabajo.getCodDetalleTrabajo(), codRepuesto));
//              System.out.printf("Guardado repuesto: %s", detalleRepuesto);
//                       this.detalleRepuestoServicio.create(detalleRepuesto);
//                        
//                    }
//                }
            } else {
                System.out.println("Cabecera Guardada Sin Detalles");
            }
        } catch (Throwable ex) {
            ex.printStackTrace(System.out);
        }

        super.sinSeleccion();

    }

    public void GuardarRepuestos() {
        if (!detallesRespuestos.isEmpty()) {

            for (int i =0 ; i<detallesRespuestos.size();i++) {

                //detalleRepuesto.setDetalleRepuestoPK(new DetalleRepuestoPK(detalleTrabajo.getCodDetalleTrabajo(), codRepuesto));
                this.detalleRepuestoServicio.create(detallesRespuestos.get(i));
                System.out.printf("Guardado repuesto: %s", detalleRepuesto);
            }
        }
    }
    
    
    

    public void eliminar(ActionEvent evento) throws IllegalAccessException, InstantiationException, InvocationTargetException, NoSuchMethodException {

        this.hojaTrabajo = (HojaTrabajo) BeanUtils.cloneBean(this.hojaTrabajoSeleccionado);
        //System.out.println("Codigo de Hoja de Trabajo" + this.hojaTrabajo.getCodHojaParte());
        detalleTrabajoServicio.EliminarDetallesTrabajo(this.hojaTrabajo.getCodHojaParte());
        hojaTrabajoServicio.delete(hojaTrabajo);
        MensajesGenericos.infoEliminar("Hoja Trabajo", this.hojaTrabajo.getCodPlaca().toString(), Boolean.TRUE);
        hojas.remove(this.hojaTrabajo);
        super.sinSeleccion();
    }

    public void filaSeleccionada(ActionEvent evento) {
        if (hojaTrabajoSeleccionado instanceof HojaTrabajo) {
            super.seleccionadoUno();
        } else {
            super.sinSeleccion();
        }
    }

    public void filaSeleccionadaD(ActionEvent evento) throws IllegalAccessException, InstantiationException, InvocationTargetException, NoSuchMethodException {
        if (detalleTrabajoSeleccionado instanceof DetalleTrabajo) {
            super.seleccionadoUno();
        } else {
            super.sinSeleccion();
        }
    }

    public void verDetallesTrabajo(ActionEvent evento) throws IllegalAccessException, InstantiationException, InvocationTargetException, NoSuchMethodException {
        if (detallesTrabajos != null) {
            this.detallesTrabajos.clear();
        }

        this.hojaTrabajo = (HojaTrabajo) BeanUtils.cloneBean(this.hojaTrabajoSeleccionado);
        this.detallesTrabajos = this.detalleTrabajoServicio.findByHojaTrabajo(hojaTrabajo.getCodHojaParte());
        super.sinSeleccion();
    }

    @Override
    public void cancelar() {
        if (super.getEnRegistro()) {
            super.sinSeleccion();
        } else {
            super.seleccionadoUno();
        }
        this.hojaTrabajo = new HojaTrabajo();
        MensajesGenericos.infoCancelar();
    }

    //Getter and Setter
    public EmpleadoServicio getEmpleadoServicio() {
        return empleadoServicio;
    }

    public void setEmpleadoServicio(EmpleadoServicio empleadoServicio) {
        this.empleadoServicio = empleadoServicio;
    }

    public List<Vehiculo> getVehiculos() {
        return vehiculos;
    }

    public void setVehiculos(List<Vehiculo> vehiculos) {
        this.vehiculos = vehiculos;
    }

    public List<Empleado> getEmpleados() {
        return empleados;
    }

    public void setEmpleados(List<Empleado> empleados) {
        this.empleados = empleados;
    }

    public List<DetalleTrabajo> getDetallesTrabajos() {
        return detallesTrabajos;
    }

    public void setDetallesTrabajos(List<DetalleTrabajo> detallesTrabajos) {
        this.detallesTrabajos = detallesTrabajos;
    }

    public List<DetalleRepuesto> getDetallesRespuestos() {
        return detallesRespuestos;
    }

    public void setDetallesRespuestos(List<DetalleRepuesto> detallesRespuestos) {
        this.detallesRespuestos = detallesRespuestos;
    }

    public List<RepuestosMecanica> getRepuestosMecanica() {
        return repuestosMecanica;
    }

    public void setRepuestosMecanica(List<RepuestosMecanica> repuestosMecanica) {
        this.repuestosMecanica = repuestosMecanica;
    }

    public HojaTrabajo getHojaTrabajo() {
        return hojaTrabajo;
    }

    public void setHojaTrabajo(HojaTrabajo hojaTrabajo) {
        this.hojaTrabajo = hojaTrabajo;
    }

    public HojaTrabajo getHojaTrabajoSeleccionado() {
        return hojaTrabajoSeleccionado;
    }

    public void setHojaTrabajoSeleccionado(HojaTrabajo hojaTrabajoSeleccionado) {
        this.hojaTrabajoSeleccionado = hojaTrabajoSeleccionado;
    }

    public String getCodEmpleadoMecanica() {
        return codEmpleadoMecanica;
    }

    public void setCodEmpleadoMecanica(String codEmpleadoMecanica) {
        this.codEmpleadoMecanica = codEmpleadoMecanica;
    }

    public String getCodPlaca() {
        return codPlaca;
    }

    public void setCodPlaca(String codPlaca) {
        this.codPlaca = codPlaca;
    }

    public String getCodDetalleTrabajo() {
        return codDetalleTrabajo;
    }

    public void setCodDetalleTrabajo(String codDetalleTrabajo) {
        this.codDetalleTrabajo = codDetalleTrabajo;
    }

    public String getCodDetalleRepuesto() {
        return codDetalleRepuesto;
    }

    public void setCodDetalleRepuesto(String codDetalleRepuesto) {
        this.codDetalleRepuesto = codDetalleRepuesto;
    }

    public String getCodRepuesto() {
        return codRepuesto;
    }

    public void setCodRepuesto(String codRepuesto) {
        this.codRepuesto = codRepuesto;
    }

    public List<HojaTrabajo> getHojas() {
        return hojas;
    }

    public void setHojas(List<HojaTrabajo> hojas) {
        this.hojas = hojas;
    }

    public DetalleTrabajo getDetalleTrabajo() {
        return detalleTrabajo;
    }

    public void setDetalleTrabajo(DetalleTrabajo detalleTrabajo) {
        this.detalleTrabajo = detalleTrabajo;
    }

    public DetalleTrabajo getDetalleTrabajoSeleccionado() {
        return detalleTrabajoSeleccionado;
    }

    public void setDetalleTrabajoSeleccionado(DetalleTrabajo detalleTrabajoSeleccionado) {
        this.detalleTrabajoSeleccionado = detalleTrabajoSeleccionado;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getvEstimado() {
        return vEstimado;
    }

    public void setvEstimado(String vEstimado) {
        this.vEstimado = vEstimado;
    }

    public String getvFinal() {
        return vFinal;
    }

    public void setvFinal(String vFinal) {
        this.vFinal = vFinal;
    }

    public String getvEstimadoR() {
        return vEstimadoR;
    }

    public void setvEstimadoR(String vEstimadoR) {
        this.vEstimadoR = vEstimadoR;
    }

    public String getvFinalR() {
        return vFinalR;
    }

    public void setvFinalR(String vFinalR) {
        this.vFinalR = vFinalR;
    }

    public DetalleRepuesto getDetalleRepuesto() {
        return detalleRepuesto;
    }

    public void setDetalleRepuesto(DetalleRepuesto detalleRepuesto) {
        this.detalleRepuesto = detalleRepuesto;
    }
}
