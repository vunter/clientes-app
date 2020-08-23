package net.ddns.salp;

import net.ddns.salp.service.UsuarioService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class SalpApplicationTests {

	@Autowired
	private UsuarioService usuarioService;

	@Test
	void contextLoads() {
		assertThat(usuarioService).isNotNull();
	}

}
