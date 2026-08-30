<<<<<<< Updated upstream
=======
import java.util.List;

>>>>>>> Stashed changes
public class Main {
    public static void main(String[] args) throws Exception {
        HelperMySQL dbMySQL = new HelperMySQL();
        dbMySQL.dropTables();
        dbMySQL.createTables();
        dbMySQL.populateDB();
        dbMySQL.closeConnection();

        AbstractFactory chosenFactory = AbstractFactory.getDAOFactory(1);
        System.out.println();
        System.out.println("////////////////////////////////////////////");
        System.out.println("////////////////////////////////////////////");
        System.out.println();
        ClienteDAO cliente = chosenFactory.getClienteDAO();
        Factura_ProductoDAO factura_producto = chosenFactory.getFactura_ProductoDAO;
        FacturaDAO factura = chosenFactory.getFacturaDAO();
        ProductoDAO producto = chosenFactory.getProductoDAO();


        System.out.println("Busco un cliente por id: ");
        Cliente clienteById = cliente.find(2);
        System.out.println(clienteById);
        System.out.println("////////////////////////////////////////////");
        System.out.println("////////////////////////////////////////////");
        /*
        System.out.println("Lista de direcciones: ");
//        List<Direccion> listadoDirecciones = direccion.selectList();
//        System.out.println(listadoDirecciones);
        List<Direccion> listadoDirecciones = direccion.selectList();
        for (Direccion dir : listadoDirecciones) {
            System.out.println(dir);
        }
         */
        System.out.println("////////////////////////////////////////////");
        System.out.println("////////////////////////////////////////////");

//        Cliente c = new Cliente(6,"Sergio","sergio233@gmail.com");
//        cliente.insertCliente(c);

        ClienteDTO clienteDTO = cliente.findClienteDTO(2);
        System.out.println(ClienteDTO);

    }
}
