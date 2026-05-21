package cl.dgac.drones.mapper;

import cl.dgac.drones.dto.DroneRequestDTO;
import cl.dgac.drones.dto.DroneResponseDTO;
import cl.dgac.drones.model.Drone;
import org.springframework.stereotype.Component;

@Component
public class DroneMapper {

    public Drone toEntity(DroneRequestDTO dto) {
        Drone drone = new Drone();

        drone.setMarca(dto.getMarca());
        drone.setModelo(dto.getModelo());
        drone.setNumeroSerie(dto.getNumeroSerie());
        drone.setPesoKg(dto.getPesoKg());
        drone.setTipoUso(dto.getTipoUso());
        drone.setEstado(dto.getEstado());
        drone.setPilotoId(dto.getPilotoId());
        drone.setEmpresaId(dto.getEmpresaId());

        return drone;
    }

    public DroneResponseDTO toDTO(Drone drone) {
        DroneResponseDTO dto = new DroneResponseDTO();

        dto.setId(drone.getId());
        dto.setMarca(drone.getMarca());
        dto.setModelo(drone.getModelo());
        dto.setNumeroSerie(drone.getNumeroSerie());
        dto.setPesoKg(drone.getPesoKg());
        dto.setTipoUso(drone.getTipoUso());
        dto.setEstado(drone.getEstado());
        dto.setPilotoId(drone.getPilotoId());
        dto.setEmpresaId(drone.getEmpresaId());

        return dto;
    }

    public void updateEntity(Drone drone, DroneRequestDTO dto) {
        drone.setMarca(dto.getMarca());
        drone.setModelo(dto.getModelo());
        drone.setNumeroSerie(dto.getNumeroSerie());
        drone.setPesoKg(dto.getPesoKg());
        drone.setTipoUso(dto.getTipoUso());
        drone.setEstado(dto.getEstado());
        drone.setPilotoId(dto.getPilotoId());
        drone.setEmpresaId(dto.getEmpresaId());
    }
}