package cn.datafort.web.common.validation;


import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class StringSetValidator implements ConstraintValidator<cn.datafort.web.common.validation.StringSet, String> {
    
    private String[] collection;
    
    @Override
    public boolean isValid(String validateVal, ConstraintValidatorContext constraintValidatorContext) {
        for (String sample : collection) {
            if (sample.equals(validateVal)) {
                return true;
            }
        }
        return false;
    }
    
    @Override
    public void initialize(cn.datafort.web.common.validation.StringSet constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
        collection = constraintAnnotation.collection();
    }
}
