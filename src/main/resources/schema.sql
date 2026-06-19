-- Crear tabla de historial
CREATE TABLE IF NOT EXISTS historial (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    id_paciente BIGINT NOT NULL,
    id_cita BIGINT NOT NULL,
    fecha VARCHAR(255) NOT NULL,
    diagnostico TEXT,
    observaciones TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Crear índices para mejora de consultas
CREATE INDEX idx_id_paciente ON historial(id_paciente);
CREATE INDEX idx_id_cita ON historial(id_cita);
CREATE INDEX idx_fecha ON historial(fecha);
