# Sistema de Reservas de Salas

Aplicación de consola en Java para gestionar reservas de salas de reuniones en una empresa.

## Requisitos
- Java 11 o superior
- Maven
- MySQL (puede usarse en Docker)

## Estructura de paquetes
- `com.empresa.app`: clase principal `Aplicacion.java` (menú de consola y control de flujo)
- `com.empresa.app.SalaService`, `EmpleadoService`, `ReservaService`, `Utilidades`: lógica de negocio y utilidades
- `com.empresa.modelo`: clases de entidad `Sala`, `Empleado` (con campo `num_empleado`), `Reserva`
- `com.empresa.dao`: DAOs e interfaces
- `com.empresa.util`: conexión a MySQL
- `com.empresa.tests`: pruebas unitarias con JUnit 5

## Instalación y ejecución
1. Clona el repositorio y accede a la carpeta del proyecto.
2. Crea la base de datos ejecutando el script `database.sql` en tu servidor MySQL. Asegúrate de que la tabla `empleado` tiene el campo `num_empleado` como clave primaria.
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
- Puedes consultar las reservas de un empleado concreto.
- Puedes consultar las reservas de una sala en concreto.
- En todas las operaciones, puedes cancelar en cualquier momento escribiendo `cancelar` o `0`.
- El mensaje "Escriba 'cancelar' o 0 para cancelar la operación" aparece solo una vez al inicio de cada operación.
- No se requiere contraseña para ninguna operación.

## Pruebas
Para ejecutar las pruebas unitarias:
```sh
mvn test
```

- Existen tests para las entidades principales (`Sala`, `Empleado`, `Reserva`) y para la lógica de solapamiento de reservas.
- Puedes ampliar los tests usando JUnit y Mockito para simular DAOs o servicios.

## Autor
- Desarrollado por Mohamed Chahid.
