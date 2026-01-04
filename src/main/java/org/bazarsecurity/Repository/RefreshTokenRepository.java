package org.bazarsecurity.Repository;

import org.bazarsecurity.Model.RefreshToken;
import org.bazarsecurity.Model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.Optional;
// Repositorio para gestionar las operaciones de RefreshToken
@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {

    void deleteAllByUsuario(Usuario usuario);

    Optional<RefreshToken> findRefreshTokenByRefreshTokenId(String refreshTokenId);

    void deleteRefreshTokenByUsuarioAndFechaExpiracionBefore(Usuario usuario, Instant fechaExpiracionBefore);

    Optional<RefreshToken> findRefreshTokenByUsuarioAndValido(Usuario usuario, boolean valido);




}
