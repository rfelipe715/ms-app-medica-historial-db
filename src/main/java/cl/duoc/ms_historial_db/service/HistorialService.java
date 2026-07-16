package cl.duoc.ms_historial_db.service;

import cl.duoc.ms_historial_db.exception.HistorialNotFoundException;
import cl.duoc.ms_historial_db.model.dto.HistorialUpdateDTO;
import cl.duoc.ms_historial_db.model.entity.Historial;
import cl.duoc.ms_historial_db.repository.HistorialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HistorialService {

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
        return this.historialRepository.save(historial);
    }

    public void eliminarHistorial(Long id) {
        if (!historialRepository.existsById(id)) {
            throw new HistorialNotFoundException(id);
        }
        historialRepository.deleteById(id);
    }

    public Historial actualizarHistorial(Long id, HistorialUpdateDTO historial) {
        Historial historialAActualizar = historialRepository.findById(id)
                .orElseThrow(() -> new HistorialNotFoundException(id));

        historialAActualizar.actualizarHistorial(historial);

        return historialRepository.save(historialAActualizar);
    }
}
