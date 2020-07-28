package net.ddns.salp.controller;

import net.ddns.salp.model.dto.ServicoPrestadoDTO;
import net.ddns.salp.model.dto.ServicoPrestadoFiltroDTO;
import net.ddns.salp.model.entity.ServicoPrestado;
import net.ddns.salp.service.ClienteService;
import net.ddns.salp.service.ServicoPrestadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/servicos")
public class ServicoPrestadoController {

    private final ServicoPrestadoService servicoPrestadoService;
    private final ClienteService clienteService;


    @Autowired
    public ServicoPrestadoController(ServicoPrestadoService servicoPrestadoService, ClienteService clienteService) {
        this.servicoPrestadoService = servicoPrestadoService;
        this.clienteService = clienteService;
    }

    @PostMapping("salvar")
    public ServicoPrestado salvar(@RequestBody @Valid ServicoPrestadoDTO dto) {
        ServicoPrestado servicoPrestado = dto.dtoToEntity(dto, clienteService.buscaCliente(dto.getIdCliente()));
        return servicoPrestadoService.salvar(servicoPrestado);
    }

    @PostMapping("buscar")
    public List<ServicoPrestado> listar(@RequestBody ServicoPrestadoFiltroDTO servicoPrestadoFiltroDTO) {

        return servicoPrestadoService.listar(servicoPrestadoFiltroDTO);
//        @RequestParam(value = "nome", required = false, defaultValue = "") String nome,
//        @RequestParam(value = "mes", required = false, defaultValue = "1") Integer mes,
//        @RequestParam(value = "mesFinal", required = false, defaultValue = "12")  Integer mesFinal
    }

}
