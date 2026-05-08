package com.wm.aspect;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Objects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestBody;

import com.wm.validation.FormValidator;

@Aspect
@Component
public class FormValidatorAspect {
	
	@Before("execution(* com.wm.controller..*.*(..))")
	public void validate(JoinPoint joinPoint) throws Throwable {
		Object[] args = joinPoint.getArgs();
		MethodSignature signature = (MethodSignature)joinPoint.getSignature();
		Method method = signature.getMethod();
		Parameter[] parameters = method.getParameters();
		
		for( int i = 0; i < parameters.length; i++) {
			// 查找带@RequestBody注解的参数
			if(parameters[i].isAnnotationPresent(RequestBody.class)) {
				Object arg = args[i];
				if(Objects.isNull(arg)) continue;
				
				// 查找对应的 BindingResult 参数（通常紧跟在 @RequestBody 后面）
                Errors errors = null;
                if (i + 1 < args.length && args[i + 1] instanceof Errors) {
                	errors = (Errors) args[i + 1];
                }
                
                FormValidator<Object> validator = getValidatorForClass(arg.getClass());
                if (validator != null) {
                    validator.validate(arg, errors);
                }
                break;
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	private FormValidator<Object> getValidatorForClass(Class<?> clazz){
		 try {
             // 约定校验器类名：原类名 + "Validator"
             String validatorClassName = "com.wm.validation." + clazz.getSimpleName() + "Validator";
             Class<?> validatorClass = Class.forName(validatorClassName);
             if (FormValidator.class.isAssignableFrom(validatorClass)) {
                 return (FormValidator<Object>) validatorClass.getDeclaredConstructor().newInstance();
             }
         } catch (Exception e) {
             // 未找到对应校验器，忽略
         }
		 return null;
	}
}
