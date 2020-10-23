package net.ddns.salp;

import lombok.RequiredArgsConstructor;
import net.ddns.salp.model.entity.Cliente;
import net.ddns.salp.model.entity.Usuario;
import net.ddns.salp.model.repository.ClienteRepository;
import net.ddns.salp.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@RequiredArgsConstructor
public class SalpApplication {

	private final PasswordEncoder passwordEncoder;

	@Bean
	public CommandLineRunner run(@Autowired ClienteRepository repository, @Autowired UsuarioService usuarioRepository) {
		return args -> {
			List<Cliente> c = new ArrayList<>();
			c.add(Cliente.builder().cpf("06742100184").lastName("Eifert Catanante").nome("Leonardo").dataNascimento(LocalDate.of(1999, 01, 10)).build());
			c.add(Cliente.builder().cpf("06032112160").lastName("Eifert Catanante").nome("Henrique").dataNascimento(LocalDate.of(1996, 05, 11)).build());
			
			repository.saveAll(c);

			usuarioRepository.salvar(Usuario.builder().user("admin").password(passwordEncoder.encode("admin")).email("leoeifert@hotmail.com").build());
		};
	}

	public static void main(String[] args) {
		SpringApplication.run(SalpApplication.class, args);
	}

}
