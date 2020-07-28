package net.ddns.salp.model.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.ddns.salp.model.entity.Cliente;
import net.ddns.salp.model.entity.ServicoPrestado;
import net.ddns.salp.util.BigDecimalConverter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@NoArgsConstructor
@AllArgsConstructor
public @Data
class ServicoPrestadoDTO {


    @NotEmpty(message = "{campo.descricao.obrigatorio}")
    private String descricao;

    @NotEmpty(message = "{campo.valor.obrigatorio}")
    private String valor;

    @NotEmpty(message = "{campo.data.obrigatorio}")
    private String data;

    @NotNull(message = "{campo.cliente.obrigatorio}")
    private Integer idCliente;

    public ServicoPrestado dtoToEntity(ServicoPrestadoDTO servicoPrestadoDTO, Cliente cliente) {
        ServicoPrestado servicoPrestado = new ServicoPrestado();
        servicoPrestado.setData(LocalDate.parse(servicoPrestadoDTO.getData(), DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        servicoPrestado.setDescricao(servicoPrestadoDTO.getDescricao());
        servicoPrestado.setCliente(cliente);
        servicoPrestado.setValor(BigDecimalConverter.converter(servicoPrestadoDTO.getValor()));
        return servicoPrestado;
    }

}
