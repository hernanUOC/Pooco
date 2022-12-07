package modelo;

public abstract class Cliente {
    private String eMail;
    private String nombre;
    private String domicilio;
    private String nif;
    public String tipo;

    public Cliente(String eMail, String nombre, String domicilio, String nif) {
        this.eMail = eMail;
        this.nombre = nombre;
        this.domicilio = domicilio;
        this.nif = nif;
    }

    public String geteMail() {
        return eMail;
    }

    public void setEMail(String eMail) {
        this.eMail = eMail;
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

    public abstract String tipoCliente();

    public abstract float calcAnual();

    public abstract float descuentoEnv();

    @Override
    public String toString(){
        return " eMail: " + this.eMail +
                " Nombre: " + this.nombre +
                " Domicilio: " + this.domicilio +
                " NIF: " + this.nif +
                " Tipo: " + tipoCliente();
    }



}

