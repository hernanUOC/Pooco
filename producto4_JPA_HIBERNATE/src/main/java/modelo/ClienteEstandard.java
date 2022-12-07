package modelo;

public class ClienteEstandard extends Cliente{

    private float calculo;
    private float descuento;

    public ClienteEstandard(String eMail, String nombre, String domicilio, String nif) {
        super(eMail, nombre, domicilio, nif);
    }
    public float getCalculo() {
        return calculo;
    }


    public void setCalculo(float calculo) {
        this.calculo = calculo;
    }


    public float getDescuento() {
        return descuento;
    }


    public void setDescuento(float descuento) {
        this.descuento = descuento;
    }


    @Override
    public String tipoCliente() {
        return tipo="Estandard";
    }

    @Override
    public float calcAnual() {
        return calculo=0;
    }


    @Override
    public float descuentoEnv() {
        return descuento=0;
    }

}
