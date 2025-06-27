
-- Crear base de datos 
DROP DATABASE IF EXISTS sistema_reservas;
CREATE DATABASE sistema_reservas CHARACTER SET utf8mb4 COLLATE utf8mb4_spanish_ci;
USE sistema_reservas;

-- Tabla: sala
CREATE TABLE sala (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL COLLATE utf8mb4_spanish_ci,
    capacidad INT NOT NULL,
    recursos TEXT COLLATE utf8mb4_spanish_ci
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

-- Tabla: empleado
CREATE TABLE empleado (
    num_empleado INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL COLLATE utf8mb4_spanish_ci,
    email VARCHAR(100) NOT NULL COLLATE utf8mb4_spanish_ci,
    departamento VARCHAR(100) NOT NULL COLLATE utf8mb4_spanish_ci
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

-- Tabla: reserva
CREATE TABLE reserva (
    id INT AUTO_INCREMENT PRIMARY KEY,
    num_empleado INT NOT NULL,
    id_sala INT NOT NULL,
    fecha DATE NOT NULL,
    hora_inicio TIME NOT NULL,
    hora_fin TIME NOT NULL,
    FOREIGN KEY (num_empleado) REFERENCES empleado(num_empleado) ON DELETE CASCADE,
    FOREIGN KEY (id_sala) REFERENCES sala(id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

-- Triggers para validaciones en MySQL 5.7
DELIMITER $$

CREATE TRIGGER trg_check_capacidad_sala
BEFORE INSERT ON sala
FOR EACH ROW
BEGIN
    IF NEW.capacidad <= 0 THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'La capacidad debe ser mayor que 0';
    END IF;
END$$

CREATE TRIGGER trg_check_horas_reserva
BEFORE INSERT ON reserva
FOR EACH ROW
BEGIN
    IF NEW.hora_inicio >= NEW.hora_fin THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'La hora de inicio debe ser menor que la hora de fin';
    END IF;
END$$

DELIMITER ;

-- Datos de prueba para sala
INSERT INTO sala (nombre, capacidad, recursos) VALUES
('Sala Azul', 10, 'Proyector, Pizarra'),
('Sala Roja', 6, 'TV, Webcam'),
('Sala Verde', 12, 'Proyector, TV, Pizarra');

-- Datos de prueba para empleado
INSERT INTO empleado (nombre, email, departamento) VALUES
('Laura Perez', 'laura.perez@empresa.com', 'Marketing'),
('Carlos Gomez', 'carlos.gomez@empresa.com', 'IT'),
('Marta Sanchez', 'marta.sanchez@empresa.com', 'Finanzas');

-- Datos de prueba para reserva
INSERT INTO reserva (num_empleado, id_sala, fecha, hora_inicio, hora_fin) VALUES
(1, 1, '2025-06-25', '09:00:00', '10:00:00'),
(2, 2, '2025-06-25', '10:30:00', '11:30:00'),
(3, 1, '2025-06-26', '11:00:00', '12:00:00');
