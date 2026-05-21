package cl.dgac.drones.dto;

import lombok.Data;

@Data
public class DroneResponseDTO {

    private Long id;
    private String marca;
    private String modelo;
    private String numeroSerie;
    private Double pesoKg;
    private String tipoUso;
    private String estado;
    private Long pilotoId;
    private Long empresaId;
}