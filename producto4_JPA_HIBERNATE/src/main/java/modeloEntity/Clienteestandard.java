package modeloEntity;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "clienteestandard")
public class Clienteestandard {
    @Id
    @Column(name = "id_eMailestandard", nullable = false, length = 45)
    private String id;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "id_eMailestandard", nullable = false)
    private Cliente cliente;

    @Column(name = "Descuento")
    private Float descuento;

    @Column(name = "TarifaAnual")
    private Float tarifaAnual;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Float getDescuento() {
        return descuento;
    }

    public void setDescuento(Float descuento) {
        this.descuento = descuento;
    }

    public Float getTarifaAnual() {
        return tarifaAnual;
    }

    public void setTarifaAnual(Float tarifaAnual) {
        this.tarifaAnual = tarifaAnual;
    }

    public Clienteestandard(String id, Cliente cliente, Float descuento, Float tarifaAnual) {
        this.id = id;
        this.cliente = cliente;
        this.descuento = descuento;
        this.tarifaAnual = tarifaAnual;
    }

    public Clienteestandard()
    {

    }
}