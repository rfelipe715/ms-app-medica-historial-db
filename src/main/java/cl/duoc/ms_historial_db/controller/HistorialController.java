package cl.duoc.ms_historial_db.controller;

import cl.duoc.ms_historial_db.model.dto.HistorialDTO;
import cl.duoc.ms_historial_db.model.dto.HistorialUpdateDTO;
import cl.duoc.ms_historial_db.model.entity.Historial;
import cl.duoc.ms_historial_db.service.HistorialService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/historiales")
public class HistorialController {

    @Autowired
    HistorialService historialService;

    @PostMapping
    public ResponseEntity<Historial> registrarHistorial(@Valid @RequestBody HistorialDTO historialDTO) {
        Historial historialCreado = new Historial();
        historialCreado.setPacienteId(historialDTO.getPacienteId());
        historialCreado.setCitaId(historialDTO.getCitaId());
        historialCreado.setFecha(historialDTO.getFecha());
        historialCreado.setDiagnostico(historialDTO.getDiagnostico());
        historialCreado.setObservaciones(historialDTO.getObservaciones());
        Historial nuevoHistorial = historialService.registerHistorial(historialCreado);
        return new ResponseEntity<>(nuevoHistorial, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Historial>> findAll(){
        List<Historial> historiales = historialService.findAll();
        return ResponseEntity.ok(historiales);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Historial> findById(@PathVariable Long id){
        Historial historial = historialService.findById(id);
        return ResponseEntity.ok(historial);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id){
        historialService.eliminarHistorial(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Historial> updateHistorial(@PathVariable Long id, @Valid @RequestBody HistorialUpdateDTO historial) {
        Historial historialActualizado = historialService.actualizarHistorial(id, historial);
        return ResponseEntity.ok(historialActualizado);
    }
}
