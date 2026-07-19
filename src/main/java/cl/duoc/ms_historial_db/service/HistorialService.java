package cl.duoc.ms_historial_db.service;

import cl.duoc.ms_historial_db.exception.HistorialNotFoundException;
import cl.duoc.ms_historial_db.model.dto.HistorialUpdateDTO;
import cl.duoc.ms_historial_db.model.entity.Historial;
import cl.duoc.ms_historial_db.repository.HistorialRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HistorialService {

    private static final Logger log = LoggerFactory.getLogger(HistorialService.class);

    @Autowired
    HistorialRepository historialRepository;

    public List<Historial> findAll() {
        return historialRepository.findAll();
    }

    public Historial findById(Long id) {
        return historialRepository.findById(id)
                .orElseThrow(() -> new HistorialNotFoundException(id));
    }

    public Historial findByPacienteId(Long pacienteId) {
        return this.historialRepository.findByPacienteId(pacienteId);
    }

    public Historial findByCitaId(Long citaId) {
        return this.historialRepository.findByCitaId(citaId);
    }

    public Historial registerHistorial(Historial historial) {
        Historial guardado = this.historialRepository.save(historial);
        log.info("Historial registrado con id={}, pacienteId={}, citaId={}",
                guardado.getId(), guardado.getPacienteId(), guardado.getCitaId());
        return guardado;
    }

    public void eliminarHistorial(Long id) {
        if (!historialRepository.existsById(id)) {
            log.warn("Intento de eliminar un historial inexistente, id={}", id);
            throw new HistorialNotFoundException(id);
        }
        historialRepository.deleteById(id);
        log.info("Historial id={} eliminado correctamente", id);
    }

    public Historial actualizarHistorial(Long id, HistorialUpdateDTO historial) {
        Historial historialAActualizar = historialRepository.findById(id)
                .orElseThrow(() -> {
                    log.warn("Intento de actualizar un historial inexistente, id={}", id);
                    return new HistorialNotFoundException(id);
                });

        historialAActualizar.actualizarHistorial(historial);

        Historial actualizado = historialRepository.save(historialAActualizar);
        log.info("Historial id={} actualizado correctamente", id);
        return actualizado;
    }
}
