package repository.mysql;
import java.sql.Connection;

import dao.ClienteDAO;
import dao.Factura_ProductoDAO;
import dao.FacturaDAO;
import dao.ProductoDAO;
import factory.DAOFactory;

public class MySQLDAOFactory extends DAOFactory {
      private final Connection connection;

    public MySQLDAOFactory() {

        this.connection = MySQLConnectionManager.getInstance().getConnection();

    }

    @Override

    public ClienteDAO createClienteDAO() {

        return new MySQLClienteDAO(connection);

    }

    @Override

    public Factura_ProductoDAO createFacturaProductoDAO() {

        return new MySQLFactura_productoDAO(connection);

    }

    @Override

    public FacturaDAO createFacturaDAO() {

        return new MySQLFacturaDAO(connection);

    }

    @Override

    public ProductoDAO createProductoDAO() {

        return new MySQLProductoDAO(connection);

    }

    @Override

    protected Connection getConnection() {

        return connection;

    }

    @Override

    protected void doShutdown() {

        MySQLConnectionManager.getInstance().shutdown();

    }
}
