package factory;
import java.sql.Connection;
public interface ConnectionManager {

    Connection getConnection();

    void shutdown();
} 
