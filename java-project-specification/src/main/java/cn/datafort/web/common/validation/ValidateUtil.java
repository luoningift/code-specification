package cn.datafort.web.common.validation;

import cn.datafort.exception.BusinessException;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.groups.Default;
import java.util.Set;

public class ValidateUtil {
    
    private static final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
    
    public static <T> void validate(T t) {
        
        validate(t, Default.class);
    }
    
    public static <T> void validate(T t, Class<?>... groups) {
    
        final Set<ConstraintViolation<T>> constraintViolations = validator.validate(t, groups);
        if (constraintViolations.size() > 0) {
            for (ConstraintViolation<T> constraintViolation : constraintViolations) {
                throw new BusinessException(5000, constraintViolation.getMessage());
            }
        }
    }
}
