package net.ddns.salp.model.entity;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import net.ddns.salp.model.annotations.UniqueEmail;
import net.ddns.salp.model.annotations.UniqueUser;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public @Data class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    @UniqueUser
    @NotEmpty(message = "{campo.user.obrigatorio}")
    private String user;

    @Column
    @NotEmpty(message = "{campo.password.obrigatorio}")
    private String password;

    @Column(unique = true)
    @Email(message = "E-mail inválido!")
    @UniqueEmail
    @NotEmpty(message = "{campo.email.obrigatorio}")
    private String email;



}
