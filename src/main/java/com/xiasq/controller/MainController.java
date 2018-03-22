package com.xiasq.controller;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.xiasq.bean.Page;
import com.xiasq.bean.User;
import com.xiasq.service.UserService;

/**
 * @author xiasq
 * @version MainController, v0.1 2018/3/13 19:59
 */
@Controller
@RequestMapping("/main")
public class MainController {
	private Logger logger = LoggerFactory.getLogger(MainController.class);
	private final String ADMINSESSION = "adminsession";
	@Autowired
	private UserService userService;

	/**
	 * 用户列表
	 * 
	 * @return
	 */
	@RequestMapping(value = "/userList")
	public ModelAndView userList(@RequestParam(value = "currentPage", defaultValue = "1") int currentPage,
			@RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
		ModelAndView modelAndView = new ModelAndView("userList");
		logger.info("用户列表...");
		int count = userService.count();
		List<User> users = userService.list((currentPage - 1) * pageSize, pageSize);

		// 创建page对象
		Page page = new Page(count, currentPage, pageSize);

		modelAndView.addObject("users", users);
		modelAndView.addObject("page", page);
		return modelAndView;
	}

	@RequestMapping(value = "/queryDetail")
	public ModelAndView queryDetail(@RequestParam int id, HttpServletRequest request) {
		logger.info("查询明细,用户: {}", id);
		ModelAndView modelAndView = new ModelAndView("detail");
		User user = userService.findByUserId(id);
		modelAndView.addObject("user", user);
		String fileUrl = user.getIdcardImgPath();
		if (user != null && fileUrl != null && !"".equals(fileUrl)) {
			logger.info("fileUrl : {}", fileUrl);
			String realPath = request.getSession().getServletContext().getRealPath("/");
			File file = new File(realPath+fileUrl);
			if (file.exists()) {
				logger.info("图片存在...");
				modelAndView.addObject("fileUrl", user.getIdcardImgPath());
			}
		}
		return modelAndView;
	}

	/**
	 * 添加用户画面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/add")
	public ModelAndView add() {
		return new ModelAndView("addoredit");
	}

	/**
	 * 修改用户画面
	 *
	 * @return
	 */
	@RequestMapping(value = "/edit")
	public ModelAndView edit(@RequestParam int id) {
		logger.info("修改界面初始化,用户: {}", id);
		ModelAndView modelAndView = new ModelAndView("addoredit");
		User user = userService.findByUserId(id);
		modelAndView.addObject("user", user);
		return modelAndView;
	}

	/**
	 * 保存用户
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/save")
	public ModelAndView save(@ModelAttribute User user) {
		logger.info("保存用户: {}", user.toString());

		ModelAndView modelAndView = new ModelAndView("redirect:userList");
		if (user.getId() != null && user.getId() > 0) {
			userService.update(user);
		} else {
			User existUser = userService.findByUsername(user.getUsername());
			if (existUser != null) {
				modelAndView = new ModelAndView("addoredit");
				modelAndView.addObject("retcode", "1");
				modelAndView.addObject("message", "用户名已存在");
				return modelAndView;
			}
			User existUser1 = userService.findByMobile(user.getMobile());
			if (existUser1 != null) {
				modelAndView = new ModelAndView("addoredit");
				modelAndView.addObject("retcode", "1");
				modelAndView.addObject("message", "手机号已存在");
				return modelAndView;
			}

			User existUser2 = userService.findByIdcard(user.getIdcard());
			if (existUser2 != null) {
				modelAndView = new ModelAndView("addoredit");
				modelAndView.addObject("retcode", "1");
				modelAndView.addObject("message", "证件号已存在");
				return modelAndView;
			}
			userService.insert(user);
		}
		return modelAndView;
	}

	/**
	 * 删除用户
	 *
	 * @return
	 */
	@RequestMapping(value = "/delete")
	public ModelAndView delete(@RequestParam int id) {
		logger.info("删除用户: {}", id);
		ModelAndView modelAndView = new ModelAndView("redirect:userList");
		userService.deleteByUserId(id);
		return modelAndView;
	}

	/**
	 * 退出登录
	 *
	 * @return
	 */
	@RequestMapping(value = "/quit")
	public ModelAndView quit(HttpServletRequest request) {
		request.getSession().removeAttribute(ADMINSESSION);
		return new ModelAndView("redirect:/main/userList");
	}

	/**
	 * 退出登录
	 *
	 * @return
	 */
	@RequestMapping(value = "/upload")
	public ModelAndView upload(@RequestParam int id) {
		ModelAndView modelAndView = new ModelAndView("upload");
		modelAndView.addObject("userId", id);
		return modelAndView;
	}

	/**
	 * 上传文件
	 * 
	 * @param file
	 * @param userId
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/doUpload")
	public ModelAndView doUpload(@RequestParam(value = "file", required = false) MultipartFile file, @RequestParam int userId,
			HttpServletRequest request) throws IOException {
		ModelAndView modelAndView = new ModelAndView("upload");
		if (file.isEmpty()) {
			logger.error("文件未上传");
			modelAndView.addObject("userId", userId);
			modelAndView.addObject("retcode", "1");
			modelAndView.addObject("message", "请选择文件重新上传");
			return modelAndView;
		} else {
			String contentType = file.getContentType();
			logger.info(contentType);// 输出image/jpeg
			if (contentType.startsWith("image")) {
				// 获取Web项目的全路径
				String realPath = request.getSession().getServletContext().getRealPath("");
				logger.info(realPath);
				String newFileName = new Date().getTime() + "" + new Random().nextInt() + ".jpg";
				FileUtils.copyInputStreamToFile(file.getInputStream(), new File(realPath, newFileName));

				// 将图片路径插入数据库
				userService.updateImagePath(userId, newFileName);
			} else {
				logger.error("上传失败，文件格式有误");
				modelAndView.addObject("userId", userId);
				modelAndView.addObject("retcode", "1");
				modelAndView.addObject("message", "上传失败，图片格式有误");
				return modelAndView;
			}
		}
		modelAndView = new ModelAndView("redirect:/main/queryDetail");
		modelAndView.addObject("id", userId);
		return modelAndView;
	}

}
