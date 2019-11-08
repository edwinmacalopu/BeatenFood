package ww.utp.beatenfood.models;



public class Producto {
    int idprod;
    int iduser;
    String nombreproducto;
    String tipoproducto;
    int cantidad;
    String medidaunidad;
    String fechainicio;
    String fechacaducidad;
    String consumido;
    String fotoproducto;

    public int getIdprod() {
        return idprod;
    }

    public void setIdprod(int idprod) {
        this.idprod = idprod;
    }

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

    public String getConsumido() {
        return consumido;
    }

    public void setConsumido(String consumido) {
        this.consumido = consumido;
    }

    public String getFotoproducto() {
        return fotoproducto;
    }

    public void setFotoproducto(String fotoproducto) {
        this.fotoproducto = fotoproducto;
    }
}
