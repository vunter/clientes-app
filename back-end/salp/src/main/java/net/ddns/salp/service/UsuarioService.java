package net.ddns.salp.service;

import com.sun.mail.iap.Response;
import net.ddns.salp.model.entity.Usuario;
import net.ddns.salp.model.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityManager;

@Service
public class UsuarioService implements UserDetailsService {

    private final UsuarioRepository repository;

    private final EntityManager em;

    @Autowired
    public UsuarioService(EntityManager em, UsuarioRepository repository) {
        this.em = em;
        this.repository = repository;
    }

    public Usuario salvar(Usuario usuario) {

        return repository.save(usuario);

    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        Usuario user = repository.findByUser(s).orElseThrow(() -> new UsernameNotFoundException("Usuário e/ou senha incorretos!"));

        return User.builder()
                .username(user.getUser())
                .password(user.getPassword())
                .roles("USER")
                .build();
    }

    public Usuario findByUser(String s) {

        return repository
                .findByUser(s)
                .orElseThrow(() -> new ResponseStatusException( HttpStatus.NOT_FOUND ,"Usuário não encontrado!"));
    }

    public Boolean isUserPresent(String s) {
        return repository.findByUser(s).isPresent();
    }
    public Boolean isEmailPresente(String s) {
        return repository.findByEmail(s).isPresent();
    }
}
