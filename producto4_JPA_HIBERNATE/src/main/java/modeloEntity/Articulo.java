package modeloEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "articulo")
public class Articulo {
    @Id
    @Column(name = "idArticulo", nullable = false, length = 10)
    private String id;

    @Column(name = "Descripcion", length = 250)
    private String descripcion;

    @Column(name = "PvpVenta")
    private Float pvpVenta;

    @Column(name = "GastosEnvio")
    private Float gastosEnvio;

    @Column(name = "TiempoPreparacion")
    private Integer tiempoPreparacion;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Float getPvpVenta() {
        return pvpVenta;
    }

    public void setPvpVenta(Float pvpVenta) {
        this.pvpVenta = pvpVenta;
    }

    public Float getGastosEnvio() {
        return gastosEnvio;
    }

    public void setGastosEnvio(Float gastosEnvio) {
        this.gastosEnvio = gastosEnvio;
    }

    public Integer getTiempoPreparacion() {
        return tiempoPreparacion;
    }

    public void setTiempoPreparacion(Integer tiempoPreparacion) {
        this.tiempoPreparacion = tiempoPreparacion;
    }


    public Articulo(String id, String descripcion, Float pvpVenta, Float gastosEnvio, Integer tiempoPreparacion) {
        this.id = id;
        this.descripcion = descripcion;
        this.pvpVenta = pvpVenta;
        this.gastosEnvio = gastosEnvio;
        this.tiempoPreparacion = tiempoPreparacion;
    }
    public Articulo()
    {

    }
}