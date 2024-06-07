package com.poscodx.mysite.exception;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(Exception.class)
	public String handler(Exception e, Model model){
		//1. 로깅(loggin)
		StringWriter errors = new StringWriter(); 
		e.printStackTrace(new PrintWriter(errors));
		//얘가 이걸 통해서 메모리에 뿌려줌
		
		System.out.println(errors.toString());
		//2. 사과(종료)(
		model.addAttribute("error",errors.toString());
		return "errors/exception";
	}

}
