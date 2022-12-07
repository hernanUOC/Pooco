package modelo;

public class ClientePremium extends Cliente{


    private float calculo;
    private float descuento;


    public ClientePremium(String eMail, String nombre, String domicilio, String nif) {
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
        return tipo="Premium";
    }


    @Override
    public float calcAnual() {
        return calculo=30f;
    }


    @Override
    public float descuentoEnv() {
        return descuento=20f;
    }



}
