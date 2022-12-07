package modeloEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "cliente")
public class Cliente {
    @Id
    @Column(name = "id_eMail", nullable = false, length = 45)
    private String id;

    @Column(name = "Nombre", length = 45)
    private String nombre;

    @Column(name = "Domicilio", length = 100)
    private String domicilio;

    @Column(name = "Nif", length = 9)
    private String nif;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public Cliente(String id, String nombre, String domicilio, String nif) {
        this.id = id;
        this.nombre = nombre;
        this.domicilio = domicilio;
        this.nif = nif;
    }

    public Cliente()
    {

    }

}