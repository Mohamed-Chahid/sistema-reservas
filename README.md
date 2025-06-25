# Sistema de Reservas de Salas

Aplicación de consola en Java para gestionar reservas de salas de reuniones en una empresa.

## Requisitos
- Java 11 o superior
- Maven
- MySQL (puede usarse en Docker)

## Estructura de paquetes
- `com.empresa.app`: clase principal `Aplicacion.java` (menú de consola y control de flujo)
- `com.empresa.app.SalaService`, `EmpleadoService`, `ReservaService`, `Utilidades`: lógica de negocio y utilidades
- `com.empresa.modelo`: clases de entidad `Sala`, `Empleado`, `Reserva`
- `com.empresa.dao`: DAOs e interfaces
- `com.empresa.util`: conexión a MySQL
- `com.empresa.tests`: pruebas unitarias con JUnit 5

## Instalación y ejecución
1. Clona el repositorio y accede a la carpeta del proyecto.
2. Crea la base de datos ejecutando el script `database.sql` en tu servidor MySQL.
3. Configura el usuario y contraseña de MySQL en `ConexionMySQL.java`.
4. Compila el proyecto:
   ```sh
   mvn clean install
   ```
5. Ejecuta la aplicación:
   ```sh
   mvn exec:java
   ```

## Uso
- El menú permite gestionar salas, empleados y reservas.
- Ahora existe la opción "Ver mis reservas" para consultar las reservas de un empleado en concreto (por ID de empleado).
- En todas las operaciones (excepto listar), puedes cancelar en cualquier momento escribiendo `cancelar` o `0`.
- El mensaje "Escriba 'cancelar' o 0 para cancelar la operación" aparece solo una vez al inicio de cada operación.
- No se requiere contraseña para ninguna operación.

## Pruebas
Para ejecutar las pruebas unitarias:
```sh
mvn test
```

## Autor
- Desarrollado por Mohamed Chahid.
