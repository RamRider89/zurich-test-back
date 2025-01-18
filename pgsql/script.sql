/**
-- zurich_test_duarte
-- Script para inicializar la base de datos: zurich_test_duarte
-- Enero 2025
-- david.duarte@davdav.tech
*/

/**
* PASOS PARA EJECUTAR ESTE SCRIPT
*
* Ejecutar el script en dos pasos:
-
* Paso 1:  Crear la base de datos manualmente.
- En pgAdmin 4, clic derecho en el servidor.
- Seleccionar Create -> Database e ingresar el nombre de la base de datos (zurich_test_duarte).
- Clic en Save.
-
* Paso 2: Ejecutar este script para crear las tablas e insertar datos de ejemplo.
- Conéctarce a la base de datos zurich_test_duarte.
- Abrir el Query Tool.
- Copiar y pegar este script.
* Ejecutar el script.
*
*/

-- Clientes table
CREATE TABLE clientes (
    id BIGSERIAL PRIMARY KEY, -- Usando BIGSERIAL para autoincremento en PostgreSQL
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    phone VARCHAR(20) NOT NULL, -- Se usa VARCHAR para mayor flexibilidad del numero telefonico
    address VARCHAR(255) NOT NULL
);

-- Polizas table
CREATE TABLE polizas (
    id BIGSERIAL PRIMARY KEY, -- Usando BIGSERIAL para autoincremento en PostgreSQL
    cliente BIGINT NOT NULL,
    type_poliza SMALLINT NOT NULL,
    date_start DATE NOT NULL,
    date_expiration DATE NOT NULL,
    monto BIGINT NOT NULL,
    status BOOLEAN NOT NULL,
    CONSTRAINT fk_polizas_cliente FOREIGN KEY (cliente) REFERENCES clientes (id)
);


-- Poliza types table
CREATE TABLE poliza_types (
    id SMALLINT NOT NULL PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);


ALTER TABLE polizas ADD CONSTRAINT fk_polizas_type_poliza FOREIGN KEY (type_poliza) REFERENCES poliza_types (id);

-- Indexes
CREATE INDEX idx_polizas_cliente ON polizas (cliente);
CREATE INDEX idx_polizas_type_poliza ON polizas (type_poliza);

-- Informacion por default
-- Insertando tipos de pólizas
INSERT INTO poliza_types (id, name) VALUES
(0, 'Default'),
(1, 'Vida'),
(2, 'Automóvil'),
(3, 'Salud'),
(4, 'Hogar');

--
--
-- Datos de ejemplo
-- Insertando dos clientes de ejemplo
INSERT INTO clientes (name, email, phone, address) VALUES 
('Carlos Duarte', 'carlos.duarte@zurich.com', '1234567890', 'Allende 123'),
('Sofia Mendoza', 'sofia.mendoza@zurich.com', '9876543210', 'Calle Cuarta No. 456');

-- Insertando dos pólizas de ejemplo
INSERT INTO polizas (cliente, type_poliza, date_start, date_expiration, monto, status) VALUES
(1, 1, '2024-01-01', '2025-01-01', 10000, true),
(2, 2, '2024-06-15', '2025-06-15', 2000, true);