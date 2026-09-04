import dao.ClienteDAO;
import dao.Factura_ProductoDAO;
import dao.FacturaDAO;
import dao.ProductoDAO;
import entities.Cliente;
import factory.DAOFactory;
import factory.DBType;

public class Main {
    public static void main(String[] args) throws Exception {

        DAOFactory factory = DAOFactory.getInstance(DBType.MYSQL);

        System.out.println();
        System.out.println("////////////////////////////////////////////");
        System.out.println("////////////////////////////////////////////");
        System.out.println();

        ClienteDAO cliente = factory.createClienteDAO();
        ProductoDAO producto = factory.createProductoDAO();
        FacturaDAO factura = factory.createFacturaDAO();
        Factura_ProductoDAO factura_producto = factory.createFacturaProductoDAO();

        System.out.println("Busco un cliente por id: ");
        Cliente clienteById = cliente.findById(2L);
        System.out.println(clienteById);

        System.out.println("////////////////////////////////////////////");
        System.out.println("////////////////////////////////////////////");
    }
}