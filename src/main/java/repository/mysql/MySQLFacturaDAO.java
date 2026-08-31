package repository.mysql;
import dao.FacturaDAO;
import entities.Factura;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class MySQLFacturaDAO implements FacturaDAO{
     private Connection conn;

    public MySQLFacturaDAO(Connection conn) {

        this.conn = conn;

    }
    @Override
    public int insert(Factura factura) throws Exception {
        String sql = "INSERT INTO factura (idFactura, idCliente) " +
                     "VALUES (?, ?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, factura.getIdFactura());
        ps.setInt(2, factura.getIdCliente());
        int resultado = ps.executeUpdate();
        ps.close();
        return resultado;
    }

    @Override
    public boolean delete(Integer id) {
        String sql = "DELETE FROM factura WHERE idFactura = ?";
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
    public Factura find(Integer id) {
        String sql = "SELECT idFactura, idCliente " +
                     "FROM factura WHERE idFactura = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Factura factura = new Factura();
                factura.setIdFactura(rs.getInt("idFactura"));
            factura.setIdCliente(rs.getInt("idCliente"));
                rs.close();
                ps.close();
                return factura;
         }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean update(Factura factura) {
        String sql = "UPDATE factura " +
                     "SET idCliente = ? " +
                     "WHERE idFactura = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, factura.getIdCliente());
            ps.setInt(2, factura.getIdFactura());
            int resultado = ps.executeUpdate();
            ps.close();
            return resultado > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }
}
