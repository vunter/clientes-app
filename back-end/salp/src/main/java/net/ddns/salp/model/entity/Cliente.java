package net.ddns.salp.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.boot.autoconfigure.web.format.DateTimeFormatters;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public @Data class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NotBlank(message = "{campo.nome.obrigatorio}")
    private String nome;

    @Column(nullable = false)
    @NotBlank(message = "{campo.lastname.obrigatorio}")
    private String lastName;

    @Column(nullable = false, length = 11)
    @NotNull(message = "{campo.cpf.obrigatorio}")
    @CPF(message = "{campo.cpf.invalido}")
    private String cpf;

    @Column(name = "DT_NASCIMENTO")
    @JsonFormat(pattern = "yyyy-MM-dd", locale = "pt_BR")
    private LocalDate dataNascimento;

    @Column(name = "DT_CADASTRO", updatable = false)
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss", locale = "pt_BR")
    private LocalDateTime dataCadastro;

    @PrePersist
    public void prePersist() {
        setDataCadastro(LocalDateTime.now());
    }
}
