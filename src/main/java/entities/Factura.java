package entities;
import lombok.Data;

@Data
public class Factura {

    private Integer idFactura;
    private Integer idCliente;

    public Factura(Integer idFactura, Integer idCliente) {
        this.idFactura = idFactura;
        this.idCliente = idCliente;
    }
}
