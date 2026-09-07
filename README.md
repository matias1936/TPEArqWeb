# TPEArqWeb

Aplicación Java que gestiona clientes, productos y facturas, implementada con el patrón **DAO + Abstract Factory** para desacoplar la lógica de negocio del motor de base de datos.

## Tecnologías

- Java 11
- Maven
- MySQL (driver `mysql-connector-j` 8.0.33)
- Apache Commons CSV (carga de datos iniciales)
- Lombok

## Estructura del proyecto

```
src/main/java
├── dao/            # Interfaces de acceso a datos (contratos)
├── dto/             # Objetos de transferencia de datos
├── entities/        # Entidades del dominio (Cliente, Producto, Factura, etc.)
├── factory/         # DAOFactory, DBType y ConnectionManager (abstracción del motor de BD)
├── repository/mysql/# Implementación concreta de los DAO para MySQL
├── utils/           # Utilidades: carga y borrado de datos iniciales
└── Main.java

src/main/resources/  # CSVs con datos de carga inicial (clientes, productos, facturas)
```

## Configuración de la base de datos

1. Crear una base de datos llamada `demodao`:
   ```sql
   CREATE DATABASE demodao;
   ```
2. Las credenciales por defecto están en `MySQLConnectionManager.java`:
    - Usuario: `root`
    - Contraseña: (vacía)

## Cómo ejecutar

1. Clonar el repositorio:
   ```bash
   git clone https://github.com/matias1936/TPEArqWeb.git
   ```
2. Compilar con Maven:
   ```bash
   mvn clean compile
   ```
3. Ejecutar la clase `Main` (desde el IDE o con Maven).

Al ejecutar, el programa:
- Borra los datos existentes (`BorrarDatos`)
- Carga los datos iniciales desde los CSV de `resources` (`CargarDatosIniciales`)
- Muestra el producto que más recaudó
- Lista los clientes ordenados por facturación

## Notas

- El motor de base de datos se define en `Main.java` mediante la constante `MOTOR` (actualmente `DBType.MYSQL`). La arquitectura está preparada para soportar otros motores agregando una nueva implementación en `factory` y `repository`.
