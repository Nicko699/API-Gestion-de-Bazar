package org.bazarsecurity.Repository;

import org.bazarsecurity.Model.Venta;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
// Repositorio para gestionar las operaciones de Venta
@Repository
public interface VentaRepository extends JpaRepository<Venta,Long> {

    Optional<Venta> findVentaByUsuario_EmailAndId(String usuarioEmail, Long id);

    Page<Venta> findVentasByUsuario_Email(String usuarioEmail, Pageable pageable);

    List<Venta> findVentasByFechaVentaBetween(LocalDateTime fechaVentaAfter, LocalDateTime fechaVentaBefore);

    List<Venta>findTop5ByOrderByTotalVentaDesc();


}
