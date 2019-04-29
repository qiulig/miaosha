package com.example.demo.validator;

import com.example.demo.util.ValidatorUtil;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.ConstraintValidator;
public class IsMobileValidator implements ConstraintValidator<IsMobile,String> {
    private boolean required = false;
    @Override
    //初始化方法拿到注解，可以定义一个字符为空
    public void initialize(IsMobile constraintAnnotation) {
        constraintAnnotation.required();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        //值是必须的
        if(required){
            return ValidatorUtil.isMobile(value);
        }else{
            if(StringUtils.isEmpty(value)){
                return  true;
            }else{
                return ValidatorUtil.isMobile(value);
            }
        }
    }
}
