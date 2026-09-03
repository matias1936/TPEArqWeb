package repository.mysql;

import dao.ProductoDAO;
import entities.Producto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class MySQLProductoDAO implements ProductoDAO {
    private Connection conn;

    public MySQLProductoDAO(Connection conn) {
        this.conn = conn;
    }

    @Override
    public int insert(Producto producto) throws Exception {
        String sql = "INSERT INTO producto (idProducto, nombre, valor) VALUES (?, ?, ?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, producto.getIdProducto());
        ps.setString(2, producto.getNombre());
        ps.setFloat(3, producto.getValor());
        int resultado = ps.executeUpdate();
        ps.close();
        return resultado;
    }

    @Override
    public boolean delete(Integer id) {
        String sql = "DELETE FROM producto WHERE idProducto = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            int resultado = ps.executeUpdate();
            ps.close();
            return resultado > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Producto find(Integer id) {
        String sql = "SELECT idProducto, nombre, valor " +
                "FROM producto WHERE idProducto = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Producto producto = new Producto();
                producto.setIdProducto(rs.getInt("idProducto"));
                producto.setNombre(rs.getString("nombre"));
                producto.setValor(rs.getFloat("valor"));
                rs.close();
                ps.close();
                return producto;
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean update(Producto producto) {
        String sql = "UPDATE producto " +
                "SET nombre = ?, valor = ? " +
                "WHERE idProducto = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, producto.getNombre());
            ps.setFloat(2, producto.getValor());
            ps.setInt(3, producto.getIdProducto());
            int resultado = ps.executeUpdate();
            ps.close();
            return resultado > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
