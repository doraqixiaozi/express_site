package com.jqh.crm.filter;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
@WebFilter(filterName="CharacterEncodingFilter",urlPatterns="/*")
public class CharacterEncoding implements Filter {

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) arg0;
		HttpServletResponse response = (HttpServletResponse) arg1;
		MyRequest myRequest = new MyRequest(request);
		myRequest.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		arg2.doFilter(request, response);
	}

}

class MyRequest extends HttpServletRequestWrapper {
	HttpServletRequest request;

	public MyRequest(HttpServletRequest request) {
		super(request);
		this.request = request;
	}

	@Override
	public String getParameter(String name) {
		String value = request.getParameter(name);
		if (value == null) {
			return null;
		}
		if (!request.getMethod().equalsIgnoreCase("get")) {
			return value;
		} else {
			try {
				return new String(value.getBytes("ISO8859-1"), "UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
				throw new RuntimeException("编码错误");
			}
		}
	}
}