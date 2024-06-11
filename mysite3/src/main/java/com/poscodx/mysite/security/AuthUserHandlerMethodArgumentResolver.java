package com.poscodx.mysite.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebArgumentResolver;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.poscodx.mysite.vo.UserVo;

public class AuthUserHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {


	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		System.out.println("authUserHandler.supportsParameter 1");
		// 내가 지원하는 파라미터인지 확인하는 것
		AuthUser authUser = parameter.getParameterAnnotation(AuthUser.class);
		
		// @AuthUser가 안 붙어 있다면,
		if(authUser == null) {
			return false;
		}
		
		// type체크
		if(! parameter.getParameterType().equals(UserVo.class)) {
			return false;
		}
		
		return true;
	}
	
	// 내가 지원하는 파라미터야? 그럼 니가 resolving해
	@Override
	public Object resolveArgument(
			MethodParameter parameter, 
			ModelAndViewContainer mavContainer,
			NativeWebRequest webRequest, 
			WebDataBinderFactory binderFactory) throws Exception {
		System.out.println("authUserHandler.resolveArgument 2");
		
		
		// 파라미터를 해석 못하는 경우
		if(!supportsParameter(parameter)) {
			return WebArgumentResolver.UNRESOLVED;
		}
		
		
		HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();
		HttpSession session = request.getSession();
		
		
		
		return session.getAttribute("authUser");
	}
}
