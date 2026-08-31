package factory;

public abstract class AbstractFactory
{
    public static final int MYSQL = 1;
    public static final int DERBY = 2;
    public abstract ClienteDAO getClienteDAO();
    public abstract Factura_ProductoDAO getFactura_ProductoDAO();
    public abstract FacturaDAO getFacturaDAO();
    public abstract ProductoDAO getProductoDAO();

    public static AbstractFactory getDAOFactory(int whichFactory) {
        switch (whichFactory) {
            case MYSQL : {
                return MySQLDAOFactory.getInstance();
            }
            case DERBY: return null;
            default: return null;
        }
    }
}

