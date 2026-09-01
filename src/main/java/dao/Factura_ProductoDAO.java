package dao;

import entities.Factura_Producto;

public interface Factura_ProductoDAO {
    Factura_Producto findById(Long idFactura, Long idProducto);

    void create(Factura_Producto d);
    void update(Factura_Producto d);
    void delete(Long idFactura, Long idProducto);
    void deleteByFactura(Long facturaId);

    void deleteAll();
}