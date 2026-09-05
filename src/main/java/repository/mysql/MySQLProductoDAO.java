package repository.mysql;

import java.sql.Statement;
import dao.ProductoDAO;
import entities.Producto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MySQLProductoDAO implements ProductoDAO {
    private Connection conn;

    public MySQLProductoDAO(Connection conn) {
        this.conn = conn;
        crearTablaSiNoExiste();
    }

    private void crearTablaSiNoExiste() {
        final String sql = "CREATE TABLE IF NOT EXISTS producto (" +
                "idProducto INT PRIMARY KEY," +
                "nombre VARCHAR(100) NOT NULL," +
                "valor FLOAT NOT NULL" +
                ")";

        try (Statement st = conn.createStatement()) {
            st.execute(sql);
        } catch (SQLException e) {
            throw new RuntimeException("Error creando tabla 'producto'", e);
        }
    }

    @Override

    public void create(Producto producto) {

        String sql = "INSERT INTO producto (idProducto, nombre, valor) VALUES (?, ?, ?)";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, producto.getIdProducto());

            ps.setString(2, producto.getNombre());

            ps.setFloat(3, producto.getValor());

            ps.executeUpdate();

        } catch (SQLException e) {

            throw new RuntimeException("Error creando producto", e);

        }

    }

    @Override

    public void delete(Long id) {

        String sql = "DELETE FROM producto WHERE idProducto = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setLong(1, id);

            ps.executeUpdate();

        } catch (SQLException e) {

            throw new RuntimeException("Error eliminando producto", e);

        }

    }

    @Override

    public Producto findById(Long id) {

        String sql = "SELECT idProducto, nombre, valor " +

                "FROM producto WHERE idProducto = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setLong(1, id);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {

                    Producto producto = new Producto(

                            rs.getInt("idProducto"),

                            rs.getString("nombre"),

                            rs.getFloat("valor"));

                    return producto;

                }

            }

        } catch (SQLException e) {

            throw new RuntimeException("Error buscando producto", e);

        }

        return null;

    }

    @Override

    public void update(Producto producto) {

        String sql = "UPDATE producto " +

                "SET nombre = ?, valor = ? " +

                "WHERE idProducto = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, producto.getNombre());

            ps.setFloat(2, producto.getValor());

            ps.setLong(3, producto.getIdProducto());

            ps.executeUpdate();

        } catch (SQLException e) {

            throw new RuntimeException("Error actualizando producto", e);

        }

    }

    @Override

    public void deleteByProducto(Long productoId) {

        delete(productoId);

    }

    @Override

    public void deleteAll() {

        String sql = "DELETE FROM producto";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.executeUpdate();

        } catch (SQLException e) {

            throw new RuntimeException("Error eliminando todos los productos", e);

        }

    }

    @Override
    public Producto findProductoMayorRecaudacion() {
        String sql = "SELECT p.idProducto, p.nombre, p.valor " +
                "FROM producto p " +
                "JOIN factura_producto fp ON p.idProducto = fp.idProducto " +
                "GROUP BY p.idProducto, p.nombre, p.valor " +
                "ORDER BY SUM(fp.cantidad * p.valor) DESC " +
                "LIMIT 1";

        try (PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {
                return new Producto(
                        rs.getInt("idProducto"),
                        rs.getString("nombre"),
                        rs.getFloat("valor"));
            }

        } catch (SQLException e) {
            throw new RuntimeException(
                    "Error buscando el producto con mayor recaudación", e);
        }

        return null;
    }
}
