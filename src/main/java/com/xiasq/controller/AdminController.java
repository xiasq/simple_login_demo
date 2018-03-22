package com.xiasq.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.xiasq.bean.Admin;
import com.xiasq.service.AdminService;
import com.xiasq.util.AuthCodeUtil;

/**
 * @author xiasq
 * @version AdminController, v0.1 2018/3/13 19:59
 */
@Controller
@RequestMapping("/admin")
public class AdminController {
	private Logger logger = LoggerFactory.getLogger(AdminController.class);

	private final String ADMINSESSION = "adminsession";
	private final static String STR_CODE = "strCode";
	private final static String RET_CODE = "retcode";
	private final static String MESSAGE = "message";

	@Autowired
	private AdminService adminService;

	/**
	 * 初始化登录页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/initLogin")
	public String initLogin() {
		logger.info("初始化登录页面...");
		return "login";
	}

	/**
	 * 登录
	 * 
	 * @param username
	 * @param password
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
	public ModelAndView doLogin(@RequestParam String username, @RequestParam String password,
			HttpServletRequest request) {
		logger.info("用户: {} 尝试登录... 使用密码 :{}", username, password);
		Admin admin = adminService.login(username, password);
		if (admin == null) {
			logger.error("账号或密码错误...");
			ModelAndView modelAndView = new ModelAndView("login");
			modelAndView.addObject(RET_CODE, "1");
			modelAndView.addObject(MESSAGE, "账号或密码错误");
			return modelAndView;
		}
		request.getSession().setAttribute(ADMINSESSION, admin);
		return new ModelAndView("redirect:/main/userList");
	}

	/**
	 * 跳转注册页
	 * 
	 * @return
	 */
	@RequestMapping(value = "/register")
	public String register() {
		logger.info("初始化注册页面...");
		return "register";
	}

	/**
	 * 注册
	 * 
	 * @return
	 */
	@RequestMapping(value = "/doRegister")
	public ModelAndView doRegister(@RequestParam String authCode, @ModelAttribute Admin admin, HttpSession session) {
		logger.info("注册...输入的验证码: {}", authCode);

		String strCode = (String) session.getAttribute(STR_CODE);
		logger.info("注册...session验证码: {}", strCode);
		if (authCode == null || "".equals(authCode)) {
			ModelAndView modelAndView = new ModelAndView("register");
			modelAndView.addObject(RET_CODE, "1");
			modelAndView.addObject(MESSAGE, "验证码不能为空");
			return modelAndView;
		}

		if (!authCode.equals(strCode)) {
			ModelAndView modelAndView = new ModelAndView("register");
			modelAndView.addObject(RET_CODE, "1");
			modelAndView.addObject(MESSAGE, "验证码不正确");
			return modelAndView;
		}

		Admin existsAdmin = adminService.findByName(admin.getName());
		if (existsAdmin != null) {
			logger.error("重复注册...");
			ModelAndView modelAndView = new ModelAndView("register");
			modelAndView.addObject(RET_CODE, "1");
			modelAndView.addObject(MESSAGE, "用户名已存在");
			return modelAndView;
		}
		ModelAndView modelAndView = new ModelAndView("redirect:/admin/initLogin");
		modelAndView.addObject(RET_CODE, "0");
		adminService.register(admin);
		return modelAndView;
	}

	/**
	 * 获取验证码
	 * 
	 * @param response
	 * @param session
	 * @throws IOException
	 */
	@RequestMapping(value = "/getCode")
	public void getAuthCode(HttpServletResponse response, HttpSession session) throws IOException {
		// 设置response头信息
		// 禁止缓存
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		// 生成验证码
		String strCode = AuthCodeUtil.generateAuthCode(response);
		logger.info("验证码strCode is :{}", strCode);
		// 将字符保存到session中用于前端的验证
		session.setAttribute(STR_CODE, strCode);
		response.getOutputStream().flush();
	}

}
