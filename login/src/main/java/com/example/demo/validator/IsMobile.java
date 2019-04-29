package com.example.demo.validator;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.Payload;
import javax.validation.constraints.NotNull;
import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
//@Repeatable(NotNull.List.class)
@Documented
@Constraint(
        validatedBy = {IsMobileValidator.class}
)
public @interface IsMobile {
    boolean required() default true;
    String message() default "电话号码格式错误";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
