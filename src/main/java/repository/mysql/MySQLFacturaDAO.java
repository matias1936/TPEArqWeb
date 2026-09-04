package repository.mysql;

import dao.FacturaDAO;
import entities.Factura;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MySQLFacturaDAO implements FacturaDAO {
    private Connection conn;

    public MySQLFacturaDAO(Connection conn) {
        this.conn = conn;
        crearTablaSiNoExiste();
    }

    private void crearTablaSiNoExiste() {
        final String sql = "CREATE TABLE IF NOT EXISTS factura (" +
                "idFactura INT PRIMARY KEY," +
                "idCliente INT NOT NULL," +
                "FOREIGN KEY (idCliente) REFERENCES cliente(idCliente)" +
                ")";

        try (Statement st = conn.createStatement()) {
            st.execute(sql);
        } catch (SQLException e) {
            throw new RuntimeException("Error creando tabla 'factura'", e);
        }
    }

    @Override

    public void create(Factura factura) {

        String sql = "INSERT INTO factura (idFactura, idCliente) " +

                "VALUES (?, ?)";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, factura.getIdFactura());

            ps.setInt(2, factura.getIdCliente());

            ps.executeUpdate();

        } catch (SQLException e) {

            throw new RuntimeException("Error creando factura", e);

        }

    }

    @Override

    public Factura findById(Long id) {

        String sql = "SELECT idFactura, idCliente " +

                "FROM factura WHERE idFactura = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setLong(1, id);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {

                    return new Factura(

                            rs.getInt("idFactura"),

                            rs.getInt("idCliente")

                    );

                }

            }

        } catch (SQLException e) {

            throw new RuntimeException("Error buscando factura", e);

        }

        return null;

    }

    @Override

    public void update(Factura factura) {

        String sql = "UPDATE factura " +

                "SET idCliente = ? " +

                "WHERE idFactura = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, factura.getIdCliente());

            ps.setInt(2, factura.getIdFactura());

            ps.executeUpdate();

        } catch (SQLException e) {

            throw new RuntimeException("Error actualizando factura", e);

        }

    }

    @Override

    public void delete(Long id) {

        String sql = "DELETE FROM factura WHERE idFactura = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setLong(1, id);

            ps.executeUpdate();

        } catch (SQLException e) {

            throw new RuntimeException("Error eliminando factura", e);

        }

    }

    @Override

    public void deleteByFactura(Long facturaId) {

        delete(facturaId);

    }

    @Override

    public void deleteAll() {

        String sql = "DELETE FROM factura";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.executeUpdate();

        } catch (SQLException e) {

            throw new RuntimeException("Error eliminando todas las facturas", e);

        }

    }
}
