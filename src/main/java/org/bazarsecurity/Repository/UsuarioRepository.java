package org.bazarsecurity.Repository;

import org.bazarsecurity.Model.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

// Repositorio para gestionar las operaciones de Usuario
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findUsuarioByEmail(String email);

    boolean existsUsuarioByEmail(String email);

    boolean existsUsuarioByEmailAndActive(String email, boolean active);

    Page<Usuario> findAllByActive(boolean active, Pageable pageable);




}
