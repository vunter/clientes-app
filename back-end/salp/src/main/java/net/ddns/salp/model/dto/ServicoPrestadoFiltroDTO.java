package net.ddns.salp.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public @Data
class ServicoPrestadoFiltroDTO {

    private String nomeCliente;
    private String cpfCliente;
    private String dtInicio;
    private String dtFim;
    private String descricao;
    private String valor;
}
