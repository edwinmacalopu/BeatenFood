package ww.utp.beatenfood.models;

import android.graphics.Bitmap;

public class Producto {

    int iduser;
    String nombreproducto;
    String tipoproducto;
    int cantidad;
    String medidaunidad;
    String fechainicio;
    String fechacaducidad;
    boolean consumido;
    Bitmap fotoproducto;

    public int getIduser() {
        return iduser;
    }

    public void setIduser(int iduser) {
        this.iduser = iduser;
    }

    public String getNombreproducto() {
        return nombreproducto;
    }

    public void setNombreproducto(String nombreproducto) {
        this.nombreproducto = nombreproducto;
    }

    public String getTipoproducto() {
        return tipoproducto;
    }

    public void setTipoproducto(String tipoproducto) {
        this.tipoproducto = tipoproducto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getMedidaunidad() {
        return medidaunidad;
    }

    public void setMedidaunidad(String medidaunidad) {
        this.medidaunidad = medidaunidad;
    }

    public String getFechainicio() {
        return fechainicio;
    }

    public void setFechainicio(String fechainicio) {
        this.fechainicio = fechainicio;
    }

    public String getFechacaducidad() {
        return fechacaducidad;
    }

    public void setFechacaducidad(String fechacaducidad) {
        this.fechacaducidad = fechacaducidad;
    }

    public boolean isConsumido() {
        return consumido;
    }

    public void setConsumido(boolean consumido) {
        this.consumido = consumido;
    }

    public Bitmap getFotoproducto() {
        return fotoproducto;
    }

    public void setFotoproducto(Bitmap fotoproducto) {
        this.fotoproducto = fotoproducto;
    }
}
