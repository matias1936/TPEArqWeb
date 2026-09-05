package utils;

import dao.ClienteDAO;
import dao.FacturaDAO;
import dao.Factura_ProductoDAO;
import dao.ProductoDAO;
import factory.DAOFactory;
import factory.DBType;

public class BorrarDatos {
    private final ClienteDAO clienteDAO;

    private final FacturaDAO facturaDAO;

    private final Factura_ProductoDAO fact_prodDAO;

    private final ProductoDAO productoDAO;

    public BorrarDatos() {

        DAOFactory factory = DAOFactory.getInstance(DBType.MYSQL);

        this.clienteDAO = factory.createClienteDAO();

        this.productoDAO = factory.createProductoDAO();

        this.facturaDAO = factory.createFacturaDAO();

        this.fact_prodDAO = factory.createFacturaProductoDAO();

    }

    public void run() {

        try {

            fact_prodDAO.deleteAll();

            facturaDAO.deleteAll();

            productoDAO.deleteAll();

            clienteDAO.deleteAll();

            System.out.println("Datos borrados correctamente.");

        } catch (Exception e) {

            throw new RuntimeException("Error borrando los datos.", e);

        }

    }
}
