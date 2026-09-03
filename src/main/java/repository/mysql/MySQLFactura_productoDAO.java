package repository.mysql;

import dao.Factura_ProductoDAO;
import entities.Factura_Producto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MySQLFactura_productoDAO implements Factura_ProductoDAO {
     private Connection conn;

    public MySQLFactura_productoDAO(Connection conn) {

        this.conn = conn;

    }

    @Override

    public void create(Factura_Producto facturaProducto) {

        String sql = "INSERT INTO factura_producto " +

                     "(idFactura, idProducto, cantidad) " +

                     "VALUES (?, ?, ?)";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, facturaProducto.getIdFactura());

            ps.setInt(2, facturaProducto.getIdProducto());

            ps.setInt(3, facturaProducto.getCantidad());

            ps.executeUpdate();

        } catch (SQLException e) {

            throw new RuntimeException("Error creando factura_producto", e);

        }

    }

    @Override

    public Factura_Producto findById(Long idFactura, Long idProducto) {

        String sql = "SELECT idFactura, idProducto, cantidad " +

                     "FROM factura_producto " +

                     "WHERE idFactura = ? AND idProducto = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setLong(1, idFactura);

            ps.setLong(2, idProducto);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {

                    return new Factura_Producto(

                            rs.getInt("idFactura"),

                            rs.getInt("idProducto"),

                            rs.getInt("cantidad")

                    );

                }

            }

        } catch (SQLException e) {

            throw new RuntimeException("Error buscando factura_producto", e);

        }

        return null;

    }

    @Override

    public void update(Factura_Producto facturaProducto) {

        String sql = "UPDATE factura_producto " +

                     "SET cantidad = ? " +

                     "WHERE idFactura = ? AND idProducto = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, facturaProducto.getCantidad());

            ps.setInt(2, facturaProducto.getIdFactura());

            ps.setInt(3, facturaProducto.getIdProducto());

            ps.executeUpdate();

        } catch (SQLException e) {

            throw new RuntimeException("Error actualizando factura_producto", e);

        }

    }

    @Override

    public void delete(Long idFactura, Long idProducto) {

        String sql = "DELETE FROM factura_producto " +

                     "WHERE idFactura = ? AND idProducto = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setLong(1, idFactura);

            ps.setLong(2, idProducto);

            ps.executeUpdate();

        } catch (SQLException e) {

            throw new RuntimeException("Error eliminando factura_producto", e);

        }

    }

    @Override

    public void deleteByFactura(Long facturaId) {

        String sql = "DELETE FROM factura_producto WHERE idFactura = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setLong(1, facturaId);

            ps.executeUpdate();

        } catch (SQLException e) {

            throw new RuntimeException("Error eliminando productos de la factura", e);

        }

    }

    @Override

    public void deleteAll() {

        String sql = "DELETE FROM factura_producto";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.executeUpdate();

        } catch (SQLException e) {

            throw new RuntimeException("Error eliminando todos los factura_producto", e);

        }

    }
}
