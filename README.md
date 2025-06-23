# Sistema de Reservas de Salas

Aplicación de consola en Java para gestionar reservas de salas de reuniones en una empresa.

## Requisitos
- Java 11 o superior
- Maven
- MySQL

## Estructura de paquetes
- `com.empresa.app`: clase principal `Aplicacion.java` (menú de consola)
- `com.empresa.modelo`: clases `Sala`, `Empleado`, `Reserva`
- `com.empresa.dao`: DAOs e interfaces
- `com.empresa.util`: conexión a MySQL
- `com.empresa.tests`: pruebas unitarias con JUnit 5

## Instalación y ejecución
1. Clona el repositorio y accede a la carpeta del proyecto.
2. Crea la base de datos ejecutando el script `database.sql` en tu servidor MySQL.
3. Configura el usuario y contraseña de MySQL en `ConexionMySQL.java`.
4. Compila el proyecto:
   ```
mvn clean install
   ```
5. Ejecuta la aplicación:
   ```
mvn exec:java
   ```

## Pruebas
Para ejecutar las pruebas unitarias:
```
mvn test
```

## Autor
- Desarrollado por el equipo de la empresa.
