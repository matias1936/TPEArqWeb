package repository.mysql;
import dao.ClienteDAO;
import dao.Factura_ProductoDAO;
import dao.FacturaDAO;
import dao.ProductoDAO;
import factory.DAOFactory;

public class MySQLDAOFactory extends DAOFactory {
    @Override

    public ClienteDAO getClienteDAO() {
        return new MySQLClienteDAO();
    }
    @Override
    public Factura_ProductoDAO getFactura_ProductoDAO() {
        return new MySQLFactura_productoDAO();
    }
    @Override
    public FacturaDAO getFacturaDAO() {
        return new MySQLFacturaDAO();
    }
    @Override
    public ProductoDAO getProductoDAO() {
        return new MySQLProductoDAO();
    }
}
