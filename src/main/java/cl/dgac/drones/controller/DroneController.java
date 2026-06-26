package cl.dgac.drones.controller;

import cl.dgac.drones.dto.DroneRequestDTO;
import cl.dgac.drones.dto.DroneResponseDTO;
import cl.dgac.drones.service.DroneService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/drones")
@Tag(name = "Drones", description = "Operaciones relacionadas con el registro y control de aeronaves no tripuladas (Drones) en el sistema DGAC")
public class DroneController {

    private final DroneService droneService;

    public DroneController(DroneService droneService) {
        this.droneService = droneService;
    }

    @Operation(summary = "Listar todos los drones", description = "Obtiene una lista completa de todos los drones registrados en la plataforma.")
    @ApiResponse(responseCode = "200", description = "Lista de drones obtenida exitosamente")
    @GetMapping
    public ResponseEntity<List<DroneResponseDTO>> listarDrones() {
        return ResponseEntity.ok(droneService.listarDrones());
    }

    @Operation(summary = "Buscar drone por ID", description = "Obtiene los detalles de un drone específico mediante su identificador único interno.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Drone encontrado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Drone no encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<DroneResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(droneService.buscarPorId(id));
    }

    @Operation(summary = "Registrar nuevo drone", description = "Ingresa una nueva aeronave no tripulada a la base de datos de la DGAC.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Drone registrado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos (ej. número de serie duplicado)")
    })
    @PostMapping
    public ResponseEntity<DroneResponseDTO> crearDrone(@Valid @RequestBody DroneRequestDTO dto) {
        DroneResponseDTO droneCreado = droneService.crearDrone(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(droneCreado);
    }

    @Operation(summary = "Actualizar información del drone", description = "Modifica los datos o el estado operativo de un drone existente.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Drone actualizado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Drone no encontrado"),
            @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos")
    })
    @PutMapping("/{id}")
    public ResponseEntity<DroneResponseDTO> actualizarDrone(
            @PathVariable Long id,
            @Valid @RequestBody DroneRequestDTO dto) {

        return ResponseEntity.ok(droneService.actualizarDrone(id, dto));
    }

    @Operation(summary = "Eliminar drone", description = "Elimina un registro de drone del sistema mediante su ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Drone eliminado exitosamente (Sin contenido)"),
            @ApiResponse(responseCode = "404", description = "Drone no encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarDrone(@PathVariable Long id) {
        droneService.eliminarDrone(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Buscar drone por número de serie", description = "Busca un drone exacto utilizando el número de serie de fábrica del equipo.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Drone encontrado"),
            @ApiResponse(responseCode = "404", description = "Número de serie no registrado")
    })
    @GetMapping("/buscar-serie")
    public ResponseEntity<DroneResponseDTO> buscarPorNumeroSerie(
            @RequestParam String numeroSerie) {

        return ResponseEntity.ok(droneService.buscarPorNumeroSerie(numeroSerie));
    }

    @Operation(summary = "Filtrar drones por estado", description = "Obtiene una lista de drones según su estado operativo (ej. ACTIVO, MANTENIMIENTO, DADO_DE_BAJA).")
    @ApiResponse(responseCode = "200", description = "Búsqueda realizada exitosamente")
    @GetMapping("/estado")
    public ResponseEntity<List<DroneResponseDTO>> listarPorEstado(
            @RequestParam String estado) {

        return ResponseEntity.ok(droneService.listarPorEstado(estado));
    }

    @Operation(summary = "Buscar drones por Piloto", description = "Obtiene todas las aeronaves asignadas o registradas a nombre de un Piloto específico.")
    @ApiResponse(responseCode = "200", description = "Búsqueda realizada exitosamente")
    @GetMapping("/piloto/{pilotoId}")
    public ResponseEntity<List<DroneResponseDTO>> listarPorPiloto(
            @PathVariable Long pilotoId) {

        return ResponseEntity.ok(droneService.listarPorPilotoId(pilotoId));
    }

    @Operation(summary = "Filtrar drones por tipo de uso", description = "Obtiene una lista de drones según su propósito (ej. RECREATIVO, COMERCIAL, FISCALIZACION).")
    @ApiResponse(responseCode = "200", description = "Búsqueda realizada exitosamente")
    @GetMapping("/tipo-uso")
    public ResponseEntity<List<DroneResponseDTO>> buscarPorTipoUso(
            @RequestParam String tipoUso) {

        return ResponseEntity.ok(droneService.buscarPorTipoUso(tipoUso));
    }

    @Operation(summary = "Consultar estado de Pilotos (WebClient)", description = "Endpoint de integración que verifica la disponibilidad del microservicio de Pilotos.")
    @ApiResponse(responseCode = "200", description = "Comunicación exitosa con el microservicio de Pilotos")
    @GetMapping("/pilotos")
    public ResponseEntity<String> consultarPilotos() {
        return ResponseEntity.ok(droneService.consultarMicroservicioPilotos());
    }
}