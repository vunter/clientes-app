package net.ddns.salp.service;

import net.ddns.salp.model.entity.Cliente;
import net.ddns.salp.model.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ClienteService {

    private final ClienteRepository repository;

    @Autowired
    public ClienteService(ClienteRepository repository) {
        this.repository = repository;
    }

    public Cliente salvar(Cliente cliente) {

        return repository.save(cliente);

    }

    public Cliente update(Long id, Cliente cliente) {
        return repository
                .findById(id)
                .map(c -> {
                    cliente.setId(c.getId());
                    return repository.save(cliente);
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não existe no banco de dados"));
    }

    public Cliente buscaCliente(Integer id) {

        return repository
                .findById(id.longValue())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não existe no banco de dados"));
    }

    public void deletarCliente(Long id) {
        repository
                .findById(id)
                .map(c -> {
                    repository.delete(c);
                    return c;
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não existe no banco de dados"));
    }

    public List<Cliente> listaClientes() {
        return repository
                .findAll();
    }

}
