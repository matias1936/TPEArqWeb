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
import java.nio.charset.StandardCharsets;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

public class CargarDatosIniciales {
    private final ClienteDAO clienteDAO;
    private final FacturaDAO facturaDAO;
    private final Factura_ProductoDAO fact_prodDAO;
    private final ProductoDAO productoDAO;

    public CargarDatosIniciales() {
        DAOFactory factory = DAOFactory.getInstance(DBType.MYSQL);
        this.clienteDAO = factory.createClienteDAO();
        this.productoDAO = factory.createProductoDAO();
        this.facturaDAO = factory.createFacturaDAO();
        this.fact_prodDAO = factory.createFacturaProductoDAO();
    }

    public void run() {
        this.cargarClientes("/clientes.csv");
        this.cargarProductos("/productos.csv");
        this.cargarFacturas("/facturas.csv");
        this.cargarFacturasProductos("/facturas-productos.csv");
    }

    private void cargarClientes(String resourcePath) {
        try (InputStream is = this.mustGetResource(resourcePath);
                CSVParser parser = CSVParser.parse(
                        is,
                        StandardCharsets.UTF_8,
                        CSVFormat.DEFAULT.builder()
                                .setHeader()
                                .setSkipHeaderRecord(true)
                                .get())) {

            for (CSVRecord record : parser) {

                Integer idCliente = Integer.parseInt(record.get("idCliente").trim());
                String nombre = record.get("nombre").trim();
                String email = record.get("email").trim();

                Cliente cliente = new Cliente(idCliente, nombre, email);

                this.clienteDAO.create(cliente);
            }

            System.out.println("Clientes cargados correctamente.");

        } catch (Exception e) {
            throw new RuntimeException(
                    "Error cargando clientes desde " + resourcePath, e);
        }
    }

    private void cargarFacturas(String resourcePath) {
        try (InputStream is = this.mustGetResource(resourcePath);
                CSVParser parser = CSVParser.parse(
                        is,
                        StandardCharsets.UTF_8,
                        CSVFormat.DEFAULT.builder()
                                .setHeader()
                                .setSkipHeaderRecord(true)
                                .get())) {

            for (CSVRecord record : parser) {

                Integer idFactura = Integer.parseInt(record.get("idFactura").trim());
                Integer idCliente = Integer.parseInt(record.get("idCliente").trim());

                Factura factura = new Factura(idFactura, idCliente);

                this.facturaDAO.create(factura);
            }

            System.out.println("Facturas cargadas correctamente.");

        } catch (Exception e) {
            throw new RuntimeException(
                    "Error cargando facturas desde " + resourcePath, e);
        }
    }

    private void cargarFacturasProductos(String resourcePath) {
        try (InputStream is = this.mustGetResource(resourcePath);
                CSVParser parser = CSVParser.parse(
                        is,
                        StandardCharsets.UTF_8,
                        CSVFormat.DEFAULT.builder()
                                .setHeader()
                                .setSkipHeaderRecord(true)
                                .get())) {

            for (CSVRecord record : parser) {

                Integer idFactura = Integer.parseInt(record.get("idFactura").trim());
                Integer idProducto = Integer.parseInt(record.get("idProducto").trim());
                Integer cantidad = Integer.parseInt(record.get("cantidad").trim());

                Factura_Producto fp = new Factura_Producto(
                        idFactura,
                        idProducto,
                        cantidad);

                this.fact_prodDAO.create(fp);
            }

            System.out.println("Facturas-productos cargados correctamente.");

        } catch (Exception e) {
            throw new RuntimeException(
                    "Error cargando facturas-productos desde " + resourcePath, e);
        }
    }

    private void cargarProductos(String resourcePath) {
        try (InputStream is = this.mustGetResource(resourcePath);
                CSVParser parser = CSVParser.parse(
                        is,
                        StandardCharsets.UTF_8,
                        CSVFormat.DEFAULT.builder()
                                .setHeader()
                                .setSkipHeaderRecord(true)
                                .get())) {

            for (CSVRecord record : parser) {

                Integer idProducto = Integer.parseInt(record.get("idProducto").trim());
                String nombre = record.get("nombre").trim();
                Float valor = Float.parseFloat(record.get("valor").trim());

                Producto producto = new Producto(idProducto, nombre, valor);

                this.productoDAO.create(producto);
            }

            System.out.println("Productos cargados correctamente.");

        } catch (Exception e) {
            throw new RuntimeException(
                    "Error cargando productos desde " + resourcePath, e);
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
