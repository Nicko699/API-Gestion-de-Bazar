package org.bazarsecurity.Repository;

import org.bazarsecurity.Model.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
// Repositorio para gestionar las operaciones de Rol
@Repository
public interface RolRepository extends JpaRepository<Rol, Long> {




}
