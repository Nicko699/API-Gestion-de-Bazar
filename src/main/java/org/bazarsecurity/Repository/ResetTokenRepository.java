package org.bazarsecurity.Repository;

import org.bazarsecurity.Model.ResetToken;
import org.bazarsecurity.Model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
// Repositorio para gestionar las operaciones de ResetToken
@Repository
public interface ResetTokenRepository extends JpaRepository<ResetToken, Long> {
    @Modifying
    @Query(value = "DELETE FROM reset_token WHERE usuario_id = :usuarioId", nativeQuery = true)
    void deleteResetTokenByUsuarioId(@Param("usuarioId") Long usuarioId);


    Optional<ResetToken> findResetTokenByResetTokenId(String resetTokenId);


}
