package dao;

import entities.Cliente;

import java.util.List;

public interface ClienteDAO {

    List<Cliente> findAll();

    Cliente findById(Long id);

    List<Cliente> findByCliente(Long clienteId);

    List<Cliente> findClientesOrdenadosPorFacturacion();

    void create(Cliente c);

    void update(Cliente c);

    void delete(Long id);

    void deleteAll();
}
