package cl.duoc.ms_historial_db.controller;

import cl.duoc.ms_historial_db.model.dto.HistorialDTO;
import cl.duoc.ms_historial_db.model.dto.HistorialUpdateDTO;
import cl.duoc.ms_historial_db.model.entity.Historial;
import cl.duoc.ms_historial_db.service.HistorialService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/historiales")
@Tag(name = "Historial (DB)", description = "Persistencia del historial clínico en la base de datos")
public class HistorialController {

    @Autowired
    HistorialService historialService;

    @Operation(summary = "Registrar un nuevo historial", description = "Crea y persiste un nuevo registro de historial clínico.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Historial registrado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos en la solicitud")
    })
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

    @Operation(summary = "Listar todos los historiales", description = "Retorna todos los historiales clínicos persistidos en la base de datos.")
    @ApiResponse(responseCode = "200", description = "Lista de historiales obtenida exitosamente")
    @GetMapping
    public ResponseEntity<List<Historial>> findAll(){
        List<Historial> historiales = historialService.findAll();
        return ResponseEntity.ok(historiales);
    }

    @Operation(summary = "Buscar historial por ID", description = "Retorna un historial clínico específico según su identificador único.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Historial encontrado"),
            @ApiResponse(responseCode = "404", description = "No existe un historial con el ID indicado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Historial> findById(@PathVariable Long id){
        Historial historial = historialService.findById(id);
        return ResponseEntity.ok(historial);
    }

    @Operation(summary = "Eliminar un historial", description = "Elimina de forma permanente un historial clínico identificado por su ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Historial eliminado exitosamente, sin contenido de respuesta"),
            @ApiResponse(responseCode = "404", description = "No existe un historial con el ID indicado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id){
        historialService.eliminarHistorial(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Actualizar un historial existente", description = "Actualiza los datos de un historial clínico ya registrado y persiste el cambio.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Historial actualizado exitosamente"),
            @ApiResponse(responseCode = "404", description = "No existe un historial con el ID indicado")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Historial> updateHistorial(@PathVariable Long id, @Valid @RequestBody HistorialUpdateDTO historial) {
        Historial historialActualizado = historialService.actualizarHistorial(id, historial);
        return ResponseEntity.ok(historialActualizado);
    }
}
