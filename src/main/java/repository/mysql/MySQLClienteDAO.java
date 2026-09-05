package repository.mysql;

import dao.ClienteDAO;
import entities.Cliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.SQLException;

public class MySQLClienteDAO implements ClienteDAO {
    private Connection conn;

    public MySQLClienteDAO(Connection conn) {
        this.conn = conn;
        crearTablaSiNoExiste();
    }

    private void crearTablaSiNoExiste() {
        final String sql = "CREATE TABLE IF NOT EXISTS cliente (" +
                "idCliente INT PRIMARY KEY," +
                "nombre VARCHAR(100) NOT NULL," +
                "email VARCHAR(120) NOT NULL" +
                ")";
        try (Statement st = conn.createStatement()) {
            st.execute(sql);
        } catch (SQLException e) {
            throw new RuntimeException("Error creando tabla 'cliente'", e);
        }
    }

    @Override

    public void create(Cliente c) {

        String sql = "INSERT INTO cliente (idCliente, nombre, email) " +

                "VALUES (?, ?, ?)";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, c.getIdCliente());

            ps.setString(2, c.getNombre());

            ps.setString(3, c.getEmail());

            ps.executeUpdate();

        } catch (SQLException e) {

            throw new RuntimeException("Error creando cliente", e);

        }

    }

    @Override
    public Cliente findById(Long id) {
        String sql = "SELECT idCliente, nombre, email " +
                "FROM cliente WHERE idCliente = ?";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setLong(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setIdCliente(rs.getInt("idCliente"));
                cliente.setNombre(rs.getString("nombre"));
                cliente.setEmail(rs.getString("email"));

                rs.close();
                ps.close();

                return cliente;
            }

            rs.close();
            ps.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public void update(Cliente cliente) {
        String sql = "UPDATE cliente " +
                "SET nombre = ?, email = ? " +
                "WHERE idCliente = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, cliente.getNombre());
            ps.setString(2, cliente.getEmail());
            ps.setInt(3, cliente.getIdCliente());

            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error actualizando cliente", e);
        }
    }

    @Override

    public void delete(Long id) {

        String sql = "DELETE FROM cliente WHERE idCliente = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setLong(1, id);

            ps.executeUpdate();

        } catch (SQLException e) {

            throw new RuntimeException("Error eliminando cliente", e);

        }

    }

    @Override

    public void deleteAll() {

        String sql = "DELETE FROM cliente";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.executeUpdate();

        } catch (SQLException e) {

            throw new RuntimeException("Error eliminando todos los clientes", e);

        }

    }

    @Override
    public List<Cliente> findAll() {

        String sql = "SELECT idCliente, nombre, email FROM cliente";

        List<Cliente> clientes = new ArrayList<>();

        try (PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                Cliente cliente = new Cliente();

                cliente.setIdCliente(rs.getInt("idCliente"));
                cliente.setNombre(rs.getString("nombre"));
                cliente.setEmail(rs.getString("email"));

                clientes.add(cliente);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error buscando todos los clientes", e);
        }

        return clientes;
    }

    @Override

    public List<Cliente> findByCliente(Long clienteId) {

        String sql = "SELECT idCliente, nombre, email " +

                "FROM cliente WHERE idCliente = ?";

        List<Cliente> clientes = new ArrayList<>();

        try (PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setLong(1, clienteId);

            try (ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {

                    Cliente cliente = new Cliente();

                    cliente.setIdCliente(rs.getInt("idCliente"));

                    cliente.setNombre(rs.getString("nombre"));

                    cliente.setEmail(rs.getString("email"));

                    clientes.add(cliente);

                }

            }

        } catch (SQLException e) {

            throw new RuntimeException("Error buscando cliente", e);

        }

        return clientes;

    }

    @Override
    public List<Cliente> findClientesOrdenadosPorFacturacion() {

        String sql = "SELECT c.idCliente, c.nombre, c.email " +
                "FROM cliente c " +
                "JOIN factura f ON c.idCliente = f.idCliente " +
                "JOIN factura_producto fp ON f.idFactura = fp.idFactura " +
                "JOIN producto p ON fp.idProducto = p.idProducto " +
                "GROUP BY c.idCliente, c.nombre, c.email " +
                "ORDER BY SUM(fp.cantidad * p.valor) DESC";

        List<Cliente> clientes = new ArrayList<>();

        try (PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                Cliente cliente = new Cliente();

                cliente.setIdCliente(rs.getInt("idCliente"));
                cliente.setNombre(rs.getString("nombre"));
                cliente.setEmail(rs.getString("email"));

                clientes.add(cliente);
            }

        } catch (SQLException e) {
            throw new RuntimeException(
                    "Error buscando clientes ordenados por facturación", e);
        }

        return clientes;
    }
}
