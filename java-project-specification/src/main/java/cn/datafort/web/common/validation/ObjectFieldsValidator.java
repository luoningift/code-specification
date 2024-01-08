package cn.datafort.web.common.validation;


import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ObjectFieldsValidator implements ConstraintValidator<cn.datafort.web.common.validation.ObjectFields, ObjectFieldsCheckInterface> {
    
    @Override
    public boolean isValid(ObjectFieldsCheckInterface objectFieldsCheckInterface, ConstraintValidatorContext constraintValidatorContext) {
        return objectFieldsCheckInterface.multiFieldsValidator(constraintValidatorContext);
    }
}
