package cl.dgac.drones.repository;

import cl.dgac.drones.model.Drone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface DroneRepository extends JpaRepository<Drone, Long> {

    Optional<Drone> findByNumeroSerie(String numeroSerie);

    List<Drone> findByEstado(String estado);

    List<Drone> findByPilotoId(Long pilotoId);

    @Query("SELECT d FROM Drone d WHERE LOWER(d.tipoUso) LIKE LOWER(CONCAT('%', :tipoUso, '%'))")
    List<Drone> buscarPorTipoUso(String tipoUso);
}