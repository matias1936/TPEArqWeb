package utils;

import dao.ClienteDAO;
import dao.FacturaDAO;
import dao.Factura_ProductoDAO;
import dao.ProductoDAO;
import entities.Cliente;
import entities.Factura;
import entities.Factura_Producto;
import entities.Producto;
import factory.DAOFactory;
import factory.DBType;

import java.io.*;

public class CargarDatosIniciales {
    private final ClienteDAO clienteDAO;
    private final FacturaDAO facturaDAO;
    private final Factura_ProductoDAO fact_prodDAO;
    private final ProductoDAO productoDAO;

    public CargarDatosIniciales() {
        DAOFactory factory = DAOFactory.getInstance(DBType.MYSQL);
        this.clienteDAO = factory.createClienteDAO();
        this.facturaDAO = factory.createFacturaDAO();
        this.fact_prodDAO = factory.createFacturaProductoDAO();
        this.productoDAO = factory.createProductoDAO();
    }

    public void run (){
        this.cargarClientes("/resources/clientes.csv");
        this.cargarFacturas("/resources/facturas.csv");
        this.cargarFacturasProductos("/resources/facturas-productos.csv");
        this.cargarProductos("/resources/productos.csv");
    }

    private void cargarClientes(String resourcePath){
        try {
            try (
                    InputStream is = this.mustGetResource(resourcePath);
                    BufferedReader br = new BufferedReader(new InputStreamReader(is));
            ) {
                boolean first = true;

                String line;
                while((line = br.readLine()) != null) {
                    if (first) {
                        first = false;
                    } else if (!line.isBlank()) {
                        String[] p = line.split(",", -1);

                        Integer idCliente = Integer.parseInt(p[0].trim());
                        String nombre = p[1].trim();
                        String email = p[2].trim();

                        Cliente cliente = new Cliente(idCliente, nombre, email);
                        this.clienteDAO.create(cliente);
                    }
                }

                System.out.println("Cliente cargados correctamente.");
            }

        } catch (Exception e) {
            throw new RuntimeException("Error cargando clientes desde " + resourcePath, e);
        }
    }


    private void cargarFacturas(String resourcePath){
        try {
            try (
                    InputStream is = this.mustGetResource(resourcePath);
                    BufferedReader br = new BufferedReader(new InputStreamReader(is));
            ) {
                boolean first = true;

                String line;
                while((line = br.readLine()) != null) {
                    if (first) {
                        first = false;
                    } else if (!line.isBlank()) {
                        String[] p = line.split(",", -1);

                        Integer idFactura = Integer.parseInt(p[0].trim());
                        Integer idCliente = Integer.parseInt(p[1].trim());

                        Factura factura = new Factura(idFactura, idCliente);
                        this.facturaDAO.create(factura);
                    }
                }

                System.out.println("Facturas cargadas correctamente.");
            }

        } catch (Exception e) {
            throw new RuntimeException("Error cargando facturas desde " + resourcePath, e);
        }
    }

    private void cargarFacturasProductos(String resourcePath){
        try {
            try (
                    InputStream is = this.mustGetResource(resourcePath);
                    BufferedReader br = new BufferedReader(new InputStreamReader(is));
            ) {
                boolean first = true;

                String line;
                while((line = br.readLine()) != null) {
                    if (first) {
                        first = false;
                    } else if (!line.isBlank()) {
                        String[] p = line.split(",", -1);

                        Integer idFactura = Integer.parseInt(p[0].trim());
                        Integer idProducto = Integer.parseInt(p[1].trim());
                        Integer cantidad =  Integer.parseInt(p[2].trim());

                        Factura_Producto fp = new Factura_Producto(idFactura, idProducto, cantidad);
                        this.fact_prodDAO.create(fp);
                    }
                }

                System.out.println("Facturas-productos cargados correctamente.");
            }

        } catch (Exception e) {
            throw new RuntimeException("Error cargando facturas-productos desde " + resourcePath, e);
        }
    }

    private void cargarProductos(String resourcePath){
        try {
            try (
                    InputStream is = this.mustGetResource(resourcePath);
                    BufferedReader br = new BufferedReader(new InputStreamReader(is));
            ) {
                boolean first = true;

                String line;
                while((line = br.readLine()) != null) {
                    if (first) {
                        first = false;
                    } else if (!line.isBlank()) {
                        String[] p = line.split(",", -1);

                        Integer idProducto = Integer.parseInt(p[0].trim());
                        String nombre = p[1].trim();
                        Float valor =  Float.parseFloat(p[2].trim());

                        Producto producto = new Producto(idProducto, nombre, valor);
                        this.productoDAO.create(producto);
                    }
                }

                System.out.println("Productos cargados correctamente.");
            }

        } catch (Exception e) {
            throw new RuntimeException("Error cargando productos desde " + resourcePath, e);
        }
    }




    private InputStream mustGetResource(String path) {
        InputStream is = this.getClass().getResourceAsStream(path);
        if (is == null) {
            throw new IllegalArgumentException("Recurso no encontrado: " + path);
        } else {
            return is;
        }
    }
}





