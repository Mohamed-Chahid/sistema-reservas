-- Crear base de datos
DROP DATABASE IF EXISTS sistema_reservas;
CREATE DATABASE sistema_reservas;
USE sistema_reservas;

-- Tabla: sala
CREATE TABLE sala (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    capacidad INT NOT NULL,
    recursos TEXT
);

-- Tabla: empleado
CREATE TABLE empleado (
    numEmpleado INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    departamento VARCHAR(100) NOT NULL
);

-- Tabla: reserva
CREATE TABLE reserva (
    id INT AUTO_INCREMENT PRIMARY KEY,
    num_Empleado INT NOT NULL,
    id_sala INT NOT NULL,
    fecha DATE NOT NULL,
    hora_inicio TIME NOT NULL,
    hora_fin TIME NOT NULL,
    FOREIGN KEY (num_Empleado) REFERENCES empleado(numEmpleado),
    FOREIGN KEY (id_sala) REFERENCES sala(id),
    CHECK (hora_inicio < hora_fin)
);

-- Datos de prueba para sala
INSERT INTO sala (nombre, capacidad, recursos) VALUES
('Sala Azul', 10, 'Proyector, Pizarra'),
('Sala Roja', 6, 'TV, Webcam'),
('Sala Verde', 12, 'Proyector, TV, Pizarra');

-- Datos de prueba para empleado
INSERT INTO empleado (nombre, email, departamento) VALUES
('Laura Pérez', 'laura.perez@empresa.com', 'Marketing'),
('Carlos Gómez', 'carlos.gomez@empresa.com', 'IT'),
('Marta Sánchez', 'marta.sanchez@empresa.com', 'Finanzas');

-- Datos de prueba para reserva
INSERT INTO reserva (num_Empleado, id_sala, fecha, hora_inicio, hora_fin) VALUES
(1, 1, '2025-06-25', '09:00:00', '10:00:00'),
(2, 2, '2025-06-25', '10:30:00', '11:30:00'),
(3, 1, '2025-06-25', '11:00:00', '12:00:00'); -- Aquesta servirà per provar solapaments si cal

