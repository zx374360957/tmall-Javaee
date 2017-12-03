package com.tmall.servlet;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tmall.beans.User;
import com.tmall.util.Page;

@SuppressWarnings("serial")
public class UserServlet extends BackServlet {

	public String add(HttpServletResponse response, HttpServletRequest request, Page page) {
		return "";
	}

	public String delete(HttpServletResponse response, HttpServletRequest request, Page page) {
		return "";
	}

	public String edit(HttpServletResponse response, HttpServletRequest request, Page page) {
		return "";
	}

	public String update(HttpServletResponse response, HttpServletRequest request, Page page) {
		int id = Integer.parseInt(request.getParameter("id"));
		userDAO.delete(id);
		return "@admin_User_list";
	}

	public String list(HttpServletResponse response, HttpServletRequest request, Page page) {
		List<User> us = userDAO.list(page.getBegin(), page.getSingleCount());
		int total = userDAO.getTotal();
		page.setTotalCount(total);

		request.setAttribute("theus", us);
		request.setAttribute("page", page);

		return "admin/listUser.jsp";
	}

}
