package repository.mysql;
import dao.ClienteDAO;
import entities.Cliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
public class MySQLClienteDAO implements ClienteDAO {
    private Connection conn;

    public MySQLClienteDAO(Connection conn) {
        this.conn = conn;
    }
@Override
    public int insert(Cliente cliente) throws Exception {

        String sql = "INSERT INTO cliente (idCliente, nombre, email) " +
                     "VALUES (?, ?, ?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, cliente.getIdCliente());
        ps.setString(2, cliente.getNombre());
        ps.setString(3, cliente.getEmail());
        int resultado = ps.executeUpdate();
        ps.close();
        return resultado;
    }
    @Override
    public boolean delete(Integer id) {
        String sql = "DELETE FROM cliente WHERE idCliente = ?";
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

    public Cliente find(Integer id) {
        String sql = "SELECT idCliente, nombre, email " +
                     "FROM cliente WHERE idCliente = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
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

    public boolean update(Cliente cliente) {
        String sql = "UPDATE cliente " +
                     "SET nombre = ?, email = ? " +
                     "WHERE idCliente = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, cliente.getNombre());
            ps.setString(2, cliente.getEmail());
            ps.setInt(3, cliente.getIdCliente());
            int resultado = ps.executeUpdate();
            ps.close();
            return resultado > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;

        }

    }

}
