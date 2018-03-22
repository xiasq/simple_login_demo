package com.xiasq.controller;

import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * @author xiasq
 * @version DemoController, v0.1 2018/3/16 8:58
 */

@Controller
@RequestMapping("/demo")
public class DemoController {

	@RequestMapping("/test")
    @ResponseBody
	public String test(HttpServletRequest request, HttpServletResponse response) {
        WebUtils.addCookie(request,response,"xiasq","aaaaaaaaaaaa");
		return "http://127.0.0.1:8080/demo/test2";
	}

	@RequestMapping("/test2")
    @ResponseBody
	public String test2(HttpServletRequest request, HttpServletResponse response) {
        String value = WebUtils.getCookie(request, "xiasq");
        System.out.println("value :" + value);
		return "test2";
	}

}

class WebUtils {
	/** cookieDomain */
	private static final String cookieDomain = "";

	/** cookiePath */
	private static final String cookiePath = "/";

	public static void addCookie(HttpServletRequest request, HttpServletResponse response, String name, String value) {
		addCookie(request, response, name, value, null, cookiePath, cookieDomain, null);
	}

	public static void addCookie(HttpServletRequest request, HttpServletResponse response, String name, String value,
			Integer maxAge, String path, String domain, Boolean secure) {
		Assert.notNull(request);
		Assert.notNull(response);
		Assert.hasText(name);
		try {
			name = URLEncoder.encode(name, "UTF-8");
			value = URLEncoder.encode(URLEncoder.encode(value, "UTF-8"), "UTF-8");
			Cookie cookie = new Cookie(name, value);
			if (maxAge != null) {
				cookie.setMaxAge(maxAge);
			}
			if (path != null && !"".equals(path)) {
				cookie.setPath(path);
			}
			if (domain != null && !"".equals(domain)) {
				cookie.setDomain(domain);
			}
			if (secure != null) {
				cookie.setSecure(secure);
			}
			response.addCookie(cookie);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

    public static String getCookie(HttpServletRequest request, String name) {
        Assert.notNull(request);
        Assert.hasText(name);
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            try {
                name = URLEncoder.encode(name, "UTF-8");
                for (Cookie cookie : cookies) {
                    if (name.equals(cookie.getName())) {
                        return URLDecoder.decode(cookie.getValue(), "UTF-8");
                    }
                }
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
