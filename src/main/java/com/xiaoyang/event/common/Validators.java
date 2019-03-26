package com.xiaoyang.event.common;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import com.xiaoyang.event.exception.ParamsException;

public class Validators {

	/**
	 * Bean参数合法性验证器
	 * @param object 
	 */
	public static void validate(Object object) {
		ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
        Validator validator = vf.getValidator();
        Set<ConstraintViolation<Object>> set = validator.validate(object);
		for (ConstraintViolation<Object> constraintViolation : set) {
			throw new ParamsException(constraintViolation.getPropertyPath().toString() + constraintViolation.getMessage());
        }
	}
}
