package cn.datafort.web.common.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.ANNOTATION_TYPE, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ObjectFieldsValidator.class)
public @interface ObjectFields {
    
    String message() default "参数校验失败";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
