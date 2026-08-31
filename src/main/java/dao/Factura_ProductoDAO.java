package dao;

import entities.Factura_Producto;

public interface Factura_ProductoDAO {
    Factura_Producto findById(Long id);

    void create(Factura_Producto d);
    void update(Factura_Producto d);
    void delete(Long id);
    void deleteByFacturaProducto(Long FacturaProductoId);

    void deleteAll();
}
