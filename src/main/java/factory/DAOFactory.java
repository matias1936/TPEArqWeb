package factory;

import dao.FacturaDAO;
import dao.ProductoDAO;

public abstract class DAOFactory {

    private static volatile DAOFactory instance;
 
    publis static DAOFactory getInstance(DBType type){
        if (instance == null) {
            synchronized (DAOFactory.class){
                if(instance == null){
                    switch (type) {
                        case MYSQL:
                            instance = new MySQLDAOFactory();
                            break;
                    
                        default:
                            throw new IllegalArgumentException("DBType no soportado: "+ type);
                    }
                }
            }
        }
        return instance;
    }

    //factory methods (contratos por cada DAO)
    public abstract ClienteDAO createClienteDAO();
    public abstract FacturaDAO createFacturaDAO();
    public abstract ProductoDAO createProductoDAO();
    public abstract Factura_ProductoDAO createFacturaProductoDAO();

    //factory method de la conexion
    protected abstract Connection getConnection();

    //cierre de la fabrica
    public final void shutdown(){
        doShutdown();
        synchronized (DAOFactory.class){
            instance = null;
        }
    }

    protected abstract void doShutdown();
}
