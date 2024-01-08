package cn.datafort.web.common.validation;

import javax.validation.ConstraintValidatorContext;

public interface ObjectFieldsCheckInterface {
    
    default boolean multiFieldsValidator (ConstraintValidatorContext context) {
        
        return true;
    }
    
    default void validatorErrorMsg(ConstraintValidatorContext context, String msg, String field) {
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(msg).addPropertyNode(field).addConstraintViolation();
    }
}
