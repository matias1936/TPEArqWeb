import java.util.List;

import dao.ClienteDAO;
import dao.FacturaDAO;
import dao.Factura_ProductoDAO;
import dao.ProductoDAO;
import entities.Cliente;
import entities.Producto;
import factory.DAOFactory;
import factory.DBType;
import utils.BorrarDatos;
import utils.CargarDatosIniciales;

public class Main {

    private static final DBType MOTOR = DBType.MYSQL;

    public static void main(String[] args) throws Exception {

        System.out.println("=== Motor de base de datos: " + MOTOR + " ===");

        new BorrarDatos().run();
        System.out.println("Listo.");

        new CargarDatosIniciales().run();
        System.out.println("Carga inicial finalizada.");

        DAOFactory f = DAOFactory.getInstance(MOTOR);

        ClienteDAO clienteDAO = f.createClienteDAO();
        ProductoDAO productoDAO = f.createProductoDAO();
        FacturaDAO facturaDAO = f.createFacturaDAO();
        Factura_ProductoDAO factura_productoDAO = f.createFacturaProductoDAO();

        // Punto 3: producto que más recaudó
        Producto productoMayorRecaudacion = productoDAO.findProductoMayorRecaudacion();

        System.out.println("Producto que más recaudó:");
        System.out.println(productoMayorRecaudacion);

        // Punto 4: clientes ordenados por facturación
        List<Cliente> clientes = clienteDAO.findClientesOrdenadosPorFacturacion();

        System.out.println();
        System.out.println("Clientes ordenados por facturación:");

        for (Cliente cliente : clientes) {
            System.out.println(cliente);
        }

        f.shutdown();
    }
}