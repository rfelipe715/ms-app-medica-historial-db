package cl.duoc.ms_historial_db.service;

import cl.duoc.ms_historial_db.model.dto.HistorialUpdateDTO;
import cl.duoc.ms_historial_db.model.entity.Historial;
import cl.duoc.ms_historial_db.repository.HistorialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HistorialService {

    @Autowired
    HistorialRepository historialRepository;

    public List<Historial> findAll() {
        return historialRepository.findAll();
    }

    public Historial findById(Long id) {
        return historialRepository.findById(id).get();
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
        historialRepository.deleteById(id);
    }

    public HistorialUpdateDTO actualizarHistorial(HistorialUpdateDTO historial){
        Optional<Historial> historialEncontrado = historialRepository.findById(historial.getId());

        if (historialEncontrado.isPresent()) {
            Historial historialAActualizar = historialEncontrado.get();
            historialAActualizar.actualizarHistorial(historial);
            historialRepository.save(historialAActualizar);
            return historial;
        }
        return null;
    }
}
