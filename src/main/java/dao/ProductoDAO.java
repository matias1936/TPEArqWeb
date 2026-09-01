package dao;

import entities.Producto;

public interface ProductoDAO {
    Producto findById(Long id);
    void create(Producto p);
    void update(Producto p);
    void delete(Long id);
    void deleteByProducto(Long productoId);
    void deleteAll();
}