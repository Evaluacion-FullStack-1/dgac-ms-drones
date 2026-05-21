package cl.dgac.drones.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "drones")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Drone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String marca;

    @Column(nullable = false)
    private String modelo;

    @Column(nullable = false, unique = true)
    private String numeroSerie;

    @Column(nullable = false)
    private Double pesoKg;

    @Column(nullable = false)
    private String tipoUso;

    @Column(nullable = false)
    private String estado;

    @Column(nullable = false)
    private Long pilotoId;

    @Column(nullable = false)
    private Long empresaId;
}