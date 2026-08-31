package dao;

import entities.Cliente;

import java.util.List;

public interface ClienteDAO {
    Cliente findById(Long id);
    List<Cliente> findAll();
    List<Cliente> findByCliente(Long clienteId);

    void create(Cliente c);
    void update(Cliente c);
    void delete(Long id);
    void deleteAll();
}
