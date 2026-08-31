package repository.mysql;
import dao.ClienteDAO;
import dao.Factura_ProductoDAO;
import dao.FacturaDAO;
import dao.ProductoDAO;
import factory.AbstractFactory;

public class MySQLDAOFactory extends AbstractFactory {
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
