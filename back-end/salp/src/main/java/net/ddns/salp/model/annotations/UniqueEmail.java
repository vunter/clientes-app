package net.ddns.salp.model.annotations;

import net.ddns.salp.util.validators.UniqueEmailValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = UniqueEmailValidator.class)
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueEmail {

    String message() default "Email já existe na base de dados!";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
