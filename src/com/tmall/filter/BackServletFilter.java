package com.tmall.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

public class BackServletFilter implements Filter {
	public void init(FilterConfig config) {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		// HttpServletResponse rep = (HttpServletResponse) response;

		String contextPath = req.getContextPath();
		String url = req.getRequestURI();
		url = StringUtils.remove(url, contextPath);

		if (url.startsWith("/admin_")) {
			String servletPath = StringUtils.substringBetween(url, "_", "_") + "Servlet";
			String method = StringUtils.substringAfterLast(url, "_");
			request.setAttribute("method", method);
			request.getRequestDispatcher("/" + servletPath).forward(request, response);
			return;
		}
		chain.doFilter(request, response);
	}

	public void destory() {

	}
}
