package dao;

import entities.Factura;

public interface FacturaDAO {
    Factura findById(Long id);

    void create(Factura f);

    void update(Factura f);

    void delete(Long id);

    void deleteByFactura(Long FacturaId);

    void deleteAll();
}
