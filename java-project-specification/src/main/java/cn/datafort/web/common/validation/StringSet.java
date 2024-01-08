package cn.datafort.web.common.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = StringSetValidator.class)
public @interface StringSet {
    
    String message() default "枚举值不在允许的范围";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    String[] collection() default {};
}
