package cl.dgac.drones.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
@Schema(description = "Modelo de petición para el registro o actualización de un drone")
public class DroneRequestDTO {

    @Schema(description = "Marca fabricante del drone", example = "DJI")
    @NotBlank(message = "La marca es obligatoria")
    private String marca;

    @Schema(description = "Modelo específico de la aeronave", example = "Matrice 300 RTK")
    @NotBlank(message = "El modelo es obligatorio")
    private String modelo;

    @Schema(description = "Número de serie único de fábrica", example = "1581F4BQ20394")
    @NotBlank(message = "El número de serie es obligatorio")
    private String numeroSerie;

    @Schema(description = "Peso de la aeronave al despegue en kilogramos", example = "3.6")
    @NotNull(message = "El peso es obligatorio")
    @Positive(message = "El peso debe ser mayor a 0")
    private Double pesoKg;

    @Schema(description = "Propósito principal de la aeronave (ej. COMERCIAL, RECREATIVO, CIENTIFICO)", example = "COMERCIAL")
    @NotBlank(message = "El tipo de uso es obligatorio")
    private String tipoUso;

    @Schema(description = "Estado operativo actual (ej. ACTIVO, MANTENIMIENTO, DADO_DE_BAJA)", example = "ACTIVO")
    @NotBlank(message = "El estado es obligatorio")
    private String estado;

    @Schema(description = "Identificador del piloto asociado o dueño del equipo", example = "5")
    @NotNull(message = "El ID del piloto es obligatorio")
    private Long pilotoId;

    @Schema(description = "Identificador de la empresa operadora responsable", example = "2")
    @NotNull(message = "El ID de la empresa es obligatorio")
    private Long empresaId;
}