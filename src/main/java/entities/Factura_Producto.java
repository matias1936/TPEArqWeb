package entities;
import lombok.Data;

@Data
public class Factura_Producto {

    private Integer idFactura;
    private Integer idProducto;
    private Integer cantidad;

    public Factura_Producto(Integer idFactura, Integer idProducto, Integer cantidad) {
        this.idFactura = idFactura;
        this.idProducto = idProducto;
        this.cantidad = cantidad;
    }
}