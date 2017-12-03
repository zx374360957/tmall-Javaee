package com.tmall.servlet;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tmall.beans.Order;
import com.tmall.util.Page;

@SuppressWarnings("serial")
public class OrderServlet extends BackServlet {

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
		List<Order> os = orderDAO.list(page.getBegin(), page.getSingleCount());
		int total = userDAO.getTotal();
		page.setTotalCount(total);

		request.setAttribute("theos", os);
		request.setAttribute("page", page);

		return "admin/listOrder.jsp";
	}

}
