package cl.duoc.ms_historial_db.repository;

import cl.duoc.ms_historial_db.model.entity.Historial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HistorialRepository extends JpaRepository<Historial, Long> {
    Historial findByPacienteId(Long pacienteId);
    Historial findByCitaId(Long citaId);
}
