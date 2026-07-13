-- Crear tabla de historial
CREATE TABLE IF NOT EXISTS historial (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    id_paciente BIGINT NOT NULL,
    id_cita BIGINT NOT NULL,
    fecha VARCHAR(255) NOT NULL,
    diagnostico TEXT,
    observaciones TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    KEY idx_id_paciente (id_paciente),
    KEY idx_id_cita (id_cita),
    KEY idx_fecha (fecha)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
