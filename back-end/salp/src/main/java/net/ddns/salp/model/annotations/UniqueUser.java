package net.ddns.salp.model.annotations;

import net.ddns.salp.util.validators.UniqueUserValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = UniqueUserValidator.class)
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueUser {

    String message() default "Usuário já existe na base de dados!";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
