package cl.duoc.ms_historial_db.controller;

import cl.duoc.ms_historial_db.model.dto.HistorialDTO;
import cl.duoc.ms_historial_db.model.dto.HistorialUpdateDTO;
import cl.duoc.ms_historial_db.model.entity.Historial;
import cl.duoc.ms_historial_db.service.HistorialService;
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
    public ResponseEntity<Historial> registrarHistorial(@RequestBody HistorialDTO historialDTO) {
        try {
            Historial historialCreado = new Historial();
            historialCreado.setPacienteId(historialDTO.getPacienteId());
            historialCreado.setCitaId(historialDTO.getCitaId());
            historialCreado.setFecha(historialDTO.getFecha());
            historialCreado.setDiagnostico(historialDTO.getDiagnostico());
            historialCreado.setObservaciones(historialDTO.getObservaciones());
            Historial nuevoHistorial = historialService.registerHistorial(historialCreado);
            return new ResponseEntity<>(nuevoHistorial, HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Historial>> findAll(){
        try {
            List<Historial> historiales = historialService.findAll();
            return ResponseEntity.ok(historiales);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Historial> findById(@PathVariable Long id){
        try {
            Historial historial = historialService.findById(id);
            if (historial != null) {
                return ResponseEntity.ok(historial);
            }
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id){
        try {
            historialService.eliminarHistorial(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<HistorialUpdateDTO> updateHistorial(@PathVariable Long id, @RequestBody HistorialUpdateDTO historial) {
        try {
            HistorialUpdateDTO historialActualizado = historialService.actualizarHistorial(historial);
            return ResponseEntity.ok(historialActualizado);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
