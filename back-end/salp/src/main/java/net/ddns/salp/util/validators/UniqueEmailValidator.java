package net.ddns.salp.util.validators;

import net.ddns.salp.model.annotations.UniqueEmail;
import net.ddns.salp.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String>  {
    @Autowired
    private UsuarioService usuarioService;

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return !usuarioService.isEmailPresente(s);
    }
}
