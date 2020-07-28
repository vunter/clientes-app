package net.ddns.salp.controller;

import com.fasterxml.jackson.annotation.JsonFormat;
import net.ddns.salp.model.entity.Cliente;
import net.ddns.salp.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/cliente")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @PostMapping("salvar")
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente salvar(@RequestBody @Valid Cliente cliente) {

        return clienteService.salvar(cliente);
    }

    @PutMapping("update/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Cliente update(@PathVariable Long id, @RequestBody @Valid Cliente cliente) {

        return clienteService.update(id, cliente);
    }

    @GetMapping("{id}")
    public Cliente clientPorId(@PathVariable Integer id) {

        return clienteService.buscaCliente(id);
    }

    @GetMapping("/clientes")
    public List<Cliente> listaClientes() {
        return clienteService.listaClientes();
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarCliente(@PathVariable Long id) {
        clienteService.deletarCliente(id);
    }

}
