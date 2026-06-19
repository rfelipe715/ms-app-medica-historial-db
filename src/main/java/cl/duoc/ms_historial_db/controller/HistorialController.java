package cl.duoc.ms_historial_db.controller;

import cl.duoc.ms_historial_db.model.dto.HistorialDTO;
import cl.duoc.ms_historial_db.model.dto.HistorialUpdateDTO;
import cl.duoc.ms_historial_db.model.entity.Historial;
import cl.duoc.ms_historial_db.service.HistorialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/historiales")
public class HistorialController {

    @Autowired
    HistorialService historialService;

    @PostMapping
    public ResponseEntity<Historial> registrarHistorial(@RequestBody HistorialDTO historialDTO) {
        Historial historialCreado = new Historial();
        historialCreado.setPacienteId(historialDTO.getPacienteId());
        historialCreado.setCitaId(historialDTO.getCitaId());
        historialCreado.setFecha(historialDTO.getFecha());
        historialCreado.setDiagnostico(historialDTO.getDiagnostico());
        historialCreado.setObservaciones(historialDTO.getObservaciones());

        Historial nuevoHistorial = historialService.registerHistorial(historialCreado);
        return ResponseEntity.ok(nuevoHistorial);
    }

    @GetMapping
    public ResponseEntity<List<Historial>> findAll(){
        List<Historial> historiales = historialService.findAll();
        return ResponseEntity.ok(historiales);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Historial> findById(Long id){
        Historial historial = historialService.findById(id);
        return ResponseEntity.ok(historial);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(Long id){
        historialService.eliminarHistorial(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping
    public ResponseEntity<HistorialUpdateDTO> updateHistorial(@RequestBody HistorialUpdateDTO historial) {
        HistorialUpdateDTO historialActualizado = historialService.actualizarHistorial(historial);
        return ResponseEntity.ok(historialActualizado);
    }
}
