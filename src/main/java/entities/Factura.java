package entities;

public class Factura {

    private Integer idFactura;
    private Integer idCliente;

    public Factura(Integer idFactura, Integer idCliente) {
        this.idFactura = idFactura;
        this.idCliente = idCliente;
    }

    public Factura() {

    }

    public Integer getIdFactura() {
        return idFactura;
    }

    public void setIdFactura(Integer idFactura) {
        this.idFactura = idFactura;
    }

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    @Override
    public String toString() {
        return "Factura{" +
                "idFactura=" + idFactura +
                ", idCliente=" + idCliente +
                '}';
    }
}
