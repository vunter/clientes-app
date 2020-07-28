package net.ddns.salp.model.repository;

import net.ddns.salp.model.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    public Optional<Usuario> findByUserIsLike(String user);

    public Optional<Usuario> findByUser(String user);

    public Optional<Usuario> findByEmail(String email);
}
