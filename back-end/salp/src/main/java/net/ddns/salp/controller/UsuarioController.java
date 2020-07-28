package net.ddns.salp.controller;

import lombok.RequiredArgsConstructor;
import net.ddns.salp.model.entity.Usuario;
import net.ddns.salp.service.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Usuario salvar (@RequestBody @Valid Usuario usuario) {
        return usuarioService.salvar(usuario);
    }

}
