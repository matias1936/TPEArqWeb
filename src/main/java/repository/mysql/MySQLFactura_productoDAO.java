package repository.mysql;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import dao.Factura_Producto;
import entities.Factura_Producto;

public class MySQLFactura_productoDAO implements Factura_ProductoDAO {
     private Connection conn;

    public MySQLFactura_productoDAO(Connection conn) {

        this.conn = conn;

    }

    @Override
    public int insert(Factura_Producto facturaProducto) throws Exception {
        String sql = "INSERT INTO factura_producto " +
                     "(idFactura, idProducto, cantidad) " +
                     "VALUES (?, ?, ?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, facturaProducto.getIdFactura());
        ps.setInt(2, facturaProducto.getIdProducto());
        ps.setInt(3, facturaProducto.getCantidad());
        int resultado = ps.executeUpdate();
        ps.close();
        return resultado;
    }
    @Override
    public boolean delete(Integer idFactura, Integer idProducto) {
        String sql = "DELETE FROM factura_producto " +
                     "WHERE idFactura = ? AND idProducto = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, idFactura);
            ps.setInt(2, idProducto);
            int resultado = ps.executeUpdate();
            ps.close();
            return resultado > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    @Override
    public Factura_Producto find(Integer idFactura, Integer idProducto) {
        String sql = "SELECT idFactura, idProducto, cantidad " +
                     "FROM factura_producto " +
                     "WHERE idFactura = ? AND idProducto = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, idFactura);
            ps.setInt(2, idProducto);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Factura_Producto facturaProducto = new Factura_Producto();
                facturaProducto.setIdFactura(
                        rs.getInt("idFactura")
                );
                facturaProducto.setIdProducto(
                        rs.getInt("idProducto")
                );
                facturaProducto.setCantidad(
                    rs.getInt("cantidad")
                );
                rs.close();
                ps.close();
              return facturaProducto;
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    @Override
    public boolean update(Factura_Producto facturaProducto) {
        String sql = "UPDATE factura_producto " +
                     "SET cantidad = ? " +
                  "WHERE idFactura = ? AND idProducto = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, facturaProducto.getCantidad());
            ps.setInt(2, facturaProducto.getIdFactura());
            ps.setInt(3, facturaProducto.getIdProducto());
            int resultado = ps.executeUpdate();
            ps.close();
            return resultado > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }
}
