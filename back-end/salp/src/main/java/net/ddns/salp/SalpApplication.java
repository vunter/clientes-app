package net.ddns.salp;

import net.ddns.salp.model.entity.Cliente;
import net.ddns.salp.model.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class SalpApplication {

	@Bean
	public CommandLineRunner run(@Autowired ClienteRepository repository) {
		return args -> {
			List<Cliente> c = new ArrayList<Cliente>();
			c.add(Cliente.builder().cpf("06742100184").lastName("Eifert Catanante").nome("Leonardo").dataNascimento(LocalDate.of(1999, 01, 10)).build());
			c.add(Cliente.builder().cpf("06032112160").lastName("Eifert Catanante").nome("Henrique").dataNascimento(LocalDate.of(1996, 05, 11)).build());

			repository.saveAll(c);
		};
	}

	public static void main(String[] args) {
		SpringApplication.run(SalpApplication.class, args);
	}

}
