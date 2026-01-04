package org.bazarsecurity.Repository;

import org.bazarsecurity.Model.DetalleVenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
// Repositorio para gestionar las operaciones de DetalleVenta
@Repository
public interface DetalleVentaRepository extends JpaRepository<DetalleVenta,Long> {
}
