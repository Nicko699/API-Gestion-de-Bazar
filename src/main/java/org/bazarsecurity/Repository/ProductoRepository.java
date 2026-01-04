package org.bazarsecurity.Repository;

import org.bazarsecurity.Model.Producto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.List;
// Repositorio para gestionar las operaciones de Producto
@Repository
public interface ProductoRepository extends JpaRepository<Producto,Long> {

    Page<Producto> findProductosByActivo(boolean activo, Pageable pageable);

    Page<Producto> findProductosByCantidadDisponibleLessThan(int cantidadDisponibleIsLessThan, Pageable pageable);




}
