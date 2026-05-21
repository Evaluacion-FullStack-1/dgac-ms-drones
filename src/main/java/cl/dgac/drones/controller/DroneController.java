package cl.dgac.drones.controller;

import cl.dgac.drones.dto.DroneRequestDTO;
import cl.dgac.drones.dto.DroneResponseDTO;
import cl.dgac.drones.service.DroneService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/drones")
public class DroneController {

    private final DroneService droneService;

    public DroneController(DroneService droneService) {
        this.droneService = droneService;
    }

    @GetMapping
    public ResponseEntity<List<DroneResponseDTO>> listarDrones() {
        return ResponseEntity.ok(droneService.listarDrones());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DroneResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(droneService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<DroneResponseDTO> crearDrone(@Valid @RequestBody DroneRequestDTO dto) {
        DroneResponseDTO droneCreado = droneService.crearDrone(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(droneCreado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DroneResponseDTO> actualizarDrone(
            @PathVariable Long id,
            @Valid @RequestBody DroneRequestDTO dto) {

        return ResponseEntity.ok(droneService.actualizarDrone(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarDrone(@PathVariable Long id) {
        droneService.eliminarDrone(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/buscar-serie")
    public ResponseEntity<DroneResponseDTO> buscarPorNumeroSerie(
            @RequestParam String numeroSerie) {

        return ResponseEntity.ok(droneService.buscarPorNumeroSerie(numeroSerie));
    }

    @GetMapping("/estado")
    public ResponseEntity<List<DroneResponseDTO>> listarPorEstado(
            @RequestParam String estado) {

        return ResponseEntity.ok(droneService.listarPorEstado(estado));
    }

    @GetMapping("/piloto/{pilotoId}")
    public ResponseEntity<List<DroneResponseDTO>> listarPorPiloto(
            @PathVariable Long pilotoId) {

        return ResponseEntity.ok(droneService.listarPorPilotoId(pilotoId));
    }

    @GetMapping("/tipo-uso")
    public ResponseEntity<List<DroneResponseDTO>> buscarPorTipoUso(
            @RequestParam String tipoUso) {

        return ResponseEntity.ok(droneService.buscarPorTipoUso(tipoUso));
    }

    @GetMapping("/pilotos")
    public ResponseEntity<String> consultarPilotos() {
        return ResponseEntity.ok(droneService.consultarMicroservicioPilotos());
    }
}