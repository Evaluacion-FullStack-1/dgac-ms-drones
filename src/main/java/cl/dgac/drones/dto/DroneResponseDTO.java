package cl.dgac.drones.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Modelo de respuesta con la información detallada del drone")
public class DroneResponseDTO {

    @Schema(description = "Identificador único del drone en la base de datos", example = "1")
    private Long id;

    @Schema(description = "Marca fabricante del drone", example = "DJI")
    private String marca;

    @Schema(description = "Modelo específico de la aeronave", example = "Matrice 300 RTK")
    private String modelo;

    @Schema(description = "Número de serie único de fábrica", example = "1581F4BQ20394")
    private String numeroSerie;

    @Schema(description = "Peso de la aeronave al despegue en kilogramos", example = "3.6")
    private Double pesoKg;

    @Schema(description = "Propósito principal de la aeronave", example = "COMERCIAL")
    private String tipoUso;

    @Schema(description = "Estado operativo actual", example = "ACTIVO")
    private String estado;

    @Schema(description = "Identificador del piloto asociado o dueño del equipo", example = "5")
    private Long pilotoId;

    @Schema(description = "Identificador de la empresa operadora responsable", example = "2")
    private Long empresaId;
}