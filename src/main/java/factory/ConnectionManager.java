package factory;

public interface ConnectionManager {

    Connection getConnection();

    void shutdown();
} 
