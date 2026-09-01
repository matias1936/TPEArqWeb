package entities;
import lombok.Data;

@Data
public class Producto {

    private Integer idProducto;
    private String nombre;
    private Float valor;

    public Producto(Integer idProducto, String nombre, Float valor) {
        this.idProducto = idProducto;
        this.nombre = nombre;
        this.valor = valor;
    }
}