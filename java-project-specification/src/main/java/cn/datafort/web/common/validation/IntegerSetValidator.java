package cn.datafort.web.common.validation;


import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class IntegerSetValidator implements ConstraintValidator<IntegerSet, Integer> {
    
    private int[] collection;
    
    @Override
    public boolean isValid(Integer validateVal, ConstraintValidatorContext constraintValidatorContext) {
    
        for (int sample : collection) {
            if (sample == validateVal) {
                return true;
            }
        }
        return false;
    }
    
    @Override
    public void initialize(IntegerSet constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
        collection = constraintAnnotation.collection();
    }
}
