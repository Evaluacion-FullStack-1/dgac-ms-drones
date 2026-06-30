package cl.dgac.drones.service;

import cl.dgac.drones.dto.DroneRequestDTO;
import cl.dgac.drones.dto.DroneResponseDTO;
import cl.dgac.drones.exception.ResourceNotFoundException;
import cl.dgac.drones.mapper.DroneMapper;
import cl.dgac.drones.model.Drone;
import cl.dgac.drones.repository.DroneRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DroneService {

    private final DroneRepository droneRepository;
    private final DroneMapper droneMapper;
    
    // Inyectamos el WebClient pre-configurado para balanceo de carga
    private final WebClient webClientPilotos;

    public DroneService(DroneRepository droneRepository,
                        DroneMapper droneMapper,
                        WebClient webClientPilotos) {
        this.droneRepository = droneRepository;
        this.droneMapper = droneMapper;
        this.webClientPilotos = webClientPilotos;
    }

    public List<DroneResponseDTO> listarDrones() {
        return droneRepository.findAll()
                .stream()
                .map(droneMapper::toDTO)
                .collect(Collectors.toList());
    }

    public DroneResponseDTO buscarPorId(Long id) {
        Drone drone = droneRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Drone no encontrado con ID: " + id));

        return droneMapper.toDTO(drone);
    }

    public DroneResponseDTO crearDrone(DroneRequestDTO dto) {
        Drone drone = droneMapper.toEntity(dto);
        Drone droneGuardado = droneRepository.save(drone);

        return droneMapper.toDTO(droneGuardado);
    }

    public DroneResponseDTO actualizarDrone(Long id, DroneRequestDTO dto) {
        Drone drone = droneRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Drone no encontrado con ID: " + id));

        droneMapper.updateEntity(drone, dto);
        Drone droneActualizado = droneRepository.save(drone);

        return droneMapper.toDTO(droneActualizado);
    }

    public void eliminarDrone(Long id) {
        Drone drone = droneRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Drone no encontrado con ID: " + id));

        droneRepository.delete(drone);
    }

    public DroneResponseDTO buscarPorNumeroSerie(String numeroSerie) {
        Drone drone = droneRepository.findByNumeroSerie(numeroSerie)
                .orElseThrow(() -> new ResourceNotFoundException("Drone no encontrado con número de serie: " + numeroSerie));

        return droneMapper.toDTO(drone);
    }

    public List<DroneResponseDTO> listarPorEstado(String estado) {
        return droneRepository.findByEstado(estado)
                .stream()
                .map(droneMapper::toDTO)
                .collect(Collectors.toList());
    }

    public List<DroneResponseDTO> listarPorPilotoId(Long pilotoId) {
        return droneRepository.findByPilotoId(pilotoId)
                .stream()
                .map(droneMapper::toDTO)
                .collect(Collectors.toList());
    }

    public List<DroneResponseDTO> buscarPorTipoUso(String tipoUso) {
        return droneRepository.buscarPorTipoUso(tipoUso)
                .stream()
                .map(droneMapper::toDTO)
                .collect(Collectors.toList());
    }

    public String consultarMicroservicioPilotos() {
        // Usamos la ruta relativa y el cliente inyectado; Eureka resuelve la IP y puerto
        return webClientPilotos
                .get()
                .uri("/api/pilotos")
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }
}