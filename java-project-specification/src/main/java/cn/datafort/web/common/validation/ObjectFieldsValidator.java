package cn.datafort.web.common.validation;


import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ObjectFieldsValidator implements ConstraintValidator<cn.datafort.web.common.validation.ObjectFields, ObjectFieldsValidatorInterface> {
    
    @Override
    public boolean isValid(ObjectFieldsValidatorInterface objectFieldsValidatorInterface, ConstraintValidatorContext constraintValidatorContext) {
        objectFieldsValidatorInterface.multiFieldsValidator();
        return true;
    }
}
