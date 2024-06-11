package com.poscodx.mysite.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import com.poscodx.mysite.vo.UserVo;

public class AuthInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(
		HttpServletRequest request,
		HttpServletResponse response,
		Object handler) throws Exception {
		
		// 1. handler 종류 확인
		if(!(handler instanceof HandlerMethod)) {
			// Default Servlet Handler가 처리하는 경우(정적자원, /assets/**, mapping이 안된 url
		}
		
		// 2. casting
		HandlerMethod handlerMethod = (HandlerMethod) handler;
		
		// 3. Handler Method @Auth 가져오기
		Auth auth = handlerMethod.getMethodAnnotation(Auth.class);
		
		// 4. Handler Method에 @Auth 없는 경우
		if(auth == null) {
			return true;
		}
		
		
		//5. @Auth가 붙어있기 때문에 (Authentication) 확인
		HttpSession session = request.getSession();
		UserVo authUser = (UserVo) session.getAttribute("authUser");
		
		
		// 6. 인증이 안된 경우 
		if(authUser==null) {
			response.sendRedirect(request.getContextPath()+"/user/login");
		}
		
		
		// 7. @Auth가 붙어 있고 인증도 된 경우
		return true;
	}

}
