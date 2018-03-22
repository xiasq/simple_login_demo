package com.xiasq.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author xiasq
 * @version LoginInterceptor, v0.1 2018/3/13 20:36
 * 登录拦截器，没有验证通过跳转登录页面
 */
public class LoginInterceptor implements HandlerInterceptor {
	private Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);
	private final String ADMINSESSION = "adminsession";

	// 拦截前处理
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object obj) throws Exception {
		logger.info("登录拦截器开始。。。。");
		Object sessionObj = request.getSession().getAttribute(ADMINSESSION);
		if (sessionObj != null) {
			return true;
		}
		response.sendRedirect("/admin/initLogin");
		return false;
	}

	// 拦截后处理
	@Override
	public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o,
			ModelAndView modelAndView) throws Exception {

	}

	// 全部完成后处理
	@Override
	public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			Object o, Exception e) throws Exception {

	}
}
