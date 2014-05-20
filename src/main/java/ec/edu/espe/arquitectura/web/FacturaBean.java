/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.arquitectura.web;

import ec.edu.espe.arquitectura.modelo.Aseguradora;
import ec.edu.espe.arquitectura.modelo.DetalleFactura;
import ec.edu.espe.arquitectura.modelo.DetalleFacturaPK;
import ec.edu.espe.arquitectura.modelo.DetalleTrabajo;
import ec.edu.espe.arquitectura.modelo.Factura;
import ec.edu.espe.arquitectura.modelo.HojaTrabajo;
import ec.edu.espe.arquitectura.servicio.AseguradoraServicio;
import ec.edu.espe.arquitectura.servicio.DetalleFacturaServicio;
import ec.edu.espe.arquitectura.servicio.DetalleTrabajoServico;
import ec.edu.espe.arquitectura.servicio.FacturaServicio;
import ec.edu.espe.arquitectura.servicio.HojaTrabajoServicio;
import java.awt.event.ActionEvent;
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
public class FacturaBean extends BotonesBean {

    @EJB
    private FacturaServicio facturaServicio;
    @EJB
    private AseguradoraServicio aseguradoraServicio;
    @EJB
    private HojaTrabajoServicio hojaTrabajoServicio;
    @EJB
    private DetalleTrabajoServico detalleTrabajoServicio;
    @EJB
    private DetalleFacturaServicio detalleFacturaServicio;
   
    
    private List<Aseguradora> aseguradoras;
    private List<HojaTrabajo> hojasTrabajos;
    private List<DetalleTrabajo> detallesTrabajos;
    private List<DetalleFactura> detallesFactura;
    private List<Factura> facturas;
    private Factura factura;
    private Factura facturaSeleccionada;
    private DetalleFactura detalleFactura;
    private String codFactura;
    private String codDetalleFactura;
    private String subtotal;
    private String iva;
    private String total;
    private String descripcionDetalleFactura;
    private String cantidad;
    private String valorUni;
    private String valorTot;
    private String codAseguradora;
    private String codHojaTrabajo; 

    @PostConstruct
    @Override
    public void postConstructor() {
        super.sinSeleccion();
        this.aseguradoras = this.aseguradoraServicio.findAll();
        this.hojasTrabajos = this.hojaTrabajoServicio.findAll();
        this.detallesTrabajos = this.detalleTrabajoServicio.findAll();
        this.facturas = this.facturaServicio.findAll();
    }

    public void nuevo(ActionEvent evento) {
        super.crear();
        this.factura = new Factura();
        this.detalleFactura = new DetalleFactura();
        this.aseguradoras = this.aseguradoraServicio.findAll();
        this.hojasTrabajos = this.hojaTrabajoServicio.findAll();
        this.detallesTrabajos = this.detalleTrabajoServicio.findAll();
        
        this.subtotal = "12";
        resetearVariables();
    }
    
    private void resetearVariables() {
        codFactura = "";
        codDetalleFactura = "";
        subtotal = "";
        iva = "";
        total = "";
        descripcionDetalleFactura = "";
        cantidad = "";
        valorUni = "";
        valorTot = "";
        codAseguradora = "";
        codHojaTrabajo = ""; 
        detallesFactura = new ArrayList<DetalleFactura>();
    }

    public void cargarTabla(ActionEvent evento) {

        HojaTrabajo hojaTrabajo;
        hojaTrabajo = this.hojaTrabajoServicio.findByID(codHojaTrabajo);
        this.detallesTrabajos = this.detalleTrabajoServicio.findByHojaTrabajo(hojaTrabajo.getCodHojaParte());
    }

    public void filaSeleccionada(ActionEvent evento) {
        if (facturaSeleccionada instanceof Factura) {
            super.seleccionadoUno();
        } else {
            super.sinSeleccion();
        }
    }
    
    public void filaSeleccionadaF(ActionEvent evento) throws IllegalAccessException, InstantiationException, InvocationTargetException, NoSuchMethodException {
        if (facturaSeleccionada instanceof Factura) {
            this.factura = new Factura();
            this.factura = (Factura) BeanUtils.cloneBean(this.facturaSeleccionada);
            this.facturaSeleccionada = new Factura();
            super.seleccionadoUno();
        } else {
            super.sinSeleccion();
        }
    }

    @Override
    public void cancelar() {
        if (super.getEnRegistro()) {
            super.sinSeleccion();
        } else {
            super.seleccionadoUno();
        }
        this.factura = new Factura();
        MensajesGenericos.infoCancelar();
    }

    //Funciones
    public void guardar(ActionEvent evento) {
        try {
            this.factura.setCodFactura(codFactura);
            this.factura.setCodAseguradora(this.aseguradoraServicio.findByID(codAseguradora));
            this.factura.setCodHojaParte(this.hojaTrabajoServicio.findByID(codHojaTrabajo));
            double subtotal = calcularSubtotal();
            this.factura.setSubtotal(BigDecimal.valueOf(subtotal));
            this.factura.setIva(BigDecimal.valueOf(subtotal *0.12));
            this.factura.setTotal(BigDecimal.valueOf(subtotal * 1.12));
            this.facturaServicio.create(factura);
            this.facturas.add(factura);
            MensajesGenericos.infoCrear("Factura", this.factura.getCodFactura().toString().concat(" - ").concat(this.factura.getCodAseguradora().toString()), Boolean.TRUE);
            super.sinSeleccion();
            
            if(detallesFactura.size() > 0)
            {
                for (int i=0;i<detallesFactura.size();i++)
                {
                    this.detalleFacturaServicio.create(detallesFactura.get(i));
                }
                MensajesGenericos.infoCrear("Detalles factura", String.valueOf(detallesFactura.size()) , Boolean.TRUE);
            }
            

        } catch (Exception e) {
            MensajesGenericos.errorGuardar();
        }

    }
    
    private double calcularSubtotal() {
        double subtotal = 0;
        if (detallesFactura != null) {
            for(DetalleFactura detalle : detallesFactura) {
               subtotal += detalle.getValorTotal().doubleValue();
            }
        }
        return subtotal;
    }
    
    public void agregar(ActionEvent evento) {
        DetalleFacturaPK detalleFacturaPK = new DetalleFacturaPK();
        detalleFacturaPK.setCodFactura(codFactura);
        detalleFacturaPK.setCodDetalleFactura(Short.parseShort(codDetalleFactura));
        this.detalleFactura.setDetalleFacturaPK(detalleFacturaPK);
        this.detalleFactura.setDescripcion(descripcionDetalleFactura);
        this.detalleFactura.setCantidad(Short.parseShort(cantidad));
        this.detalleFactura.setValorUnitario(BigDecimal.valueOf(Double.parseDouble(valorUni)));
        
        detalleFactura.setValorTotal(BigDecimal.valueOf(Double.valueOf(valorUni) * Double.valueOf(cantidad)));
        if(detallesFactura == null) {
            detallesFactura = new ArrayList<DetalleFactura>();
        }
        this.detallesFactura.add(detalleFactura);
        this.detalleFactura = new DetalleFactura();
        System.out.println(detallesFactura);
    }

    public List<Aseguradora> getAseguradoras() {
        return aseguradoras;
    }

    public void setAseguradoras(List<Aseguradora> aseguradoras) {
        this.aseguradoras = aseguradoras;
    }

    public List<HojaTrabajo> getHojasTrabajos() {
        return hojasTrabajos;
    }

    public void setHojasTrabajos(List<HojaTrabajo> hojasTrabajos) {
        this.hojasTrabajos = hojasTrabajos;
    }

    public List<DetalleTrabajo> getDetallesTrabajos() {
        return detallesTrabajos;
    }

    public void setDetallesTrabajos(List<DetalleTrabajo> detallesTrabajos) {
        this.detallesTrabajos = detallesTrabajos;
    }

    public List<DetalleFactura> getDetallesFactura() {
        return detallesFactura;
    }

    public void setDetallesFactura(List<DetalleFactura> detallesFactura) {
        this.detallesFactura = detallesFactura;
    }

    public List<Factura> getFacturas() {
        return facturas;
    }

    public void setFacturas(List<Factura> facturas) {
        this.facturas = facturas;
    }

    public Factura getFactura() {
        return factura;
    }

    public void setFactura(Factura factura) {
        this.factura = factura;
    }

    public Factura getFacturaSeleccionada() {
        return facturaSeleccionada;
    }

    public void setFacturaSeleccionada(Factura facturaSeleccionada) {
        this.facturaSeleccionada = facturaSeleccionada;
    }

    public DetalleFactura getDetalleFactura() {
        return detalleFactura;
    }

    public void setDetalleFactura(DetalleFactura detalleFactura) {
        this.detalleFactura = detalleFactura;
    }

    public String getCodFactura() {
        return codFactura;
    }

    public void setCodFactura(String codFactura) {
        this.codFactura = codFactura;
    }

    public String getCodDetalleFactura() {
        return codDetalleFactura;
    }

    public void setCodDetalleFactura(String codDetalleFactura) {
        this.codDetalleFactura = codDetalleFactura;
    }

    public String getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(String subtotal) {
        this.subtotal = subtotal;
    }

    public String getIva() {
        return iva;
    }

    public void setIva(String iva) {
        this.iva = iva;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getDescripcionDetalleFactura() {
        return descripcionDetalleFactura;
    }

    public void setDescripcionDetalleFactura(String descripcionDetalleFactura) {
        this.descripcionDetalleFactura = descripcionDetalleFactura;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public String getValorUni() {
        return valorUni;
    }

    public void setValorUni(String valorUni) {
        this.valorUni = valorUni;
    }

    public String getValorTot() {
        return valorTot;
    }

    public void setValorTot(String valorTot) {
        this.valorTot = valorTot;
    }

    public String getCodAseguradora() {
        return codAseguradora;
    }

    public void setCodAseguradora(String codAseguradora) {
        this.codAseguradora = codAseguradora;
    }

    public String getCodHojaTrabajo() {
        return codHojaTrabajo;
    }

    public void setCodHojaTrabajo(String codHojaTrabajo) {
        this.codHojaTrabajo = codHojaTrabajo;
    }
    
    
}
