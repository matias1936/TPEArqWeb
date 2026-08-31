package dao;

import entities.Factura_Producto;

    public interface ProductoDAO {
        Producto findById(Long id);
        void create(Producto p);
        void update(Producto p);
        void delete(Long id);
        void deleteByProducto(Long ProductoId);

        void deleteAll();
    }
    }
