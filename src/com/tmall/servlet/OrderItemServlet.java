package com.tmall.servlet;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tmall.beans.OrderItem;
import com.tmall.util.Page;

@SuppressWarnings("serial")
public class OrderItemServlet extends BackServlet {

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
		return "";
	}

	public String list(HttpServletResponse response, HttpServletRequest request, Page page) {
		int oid = Integer.parseInt(request.getParameter("oid"));
		List<OrderItem> ois = orderItemDAO.list(oid, page.getBegin(), page.getSingleCount());

		int total = orderItemDAO.getTotalNumber(oid);
		page.setTotalCount(total);
		page.setParam("&oid=" + oid);

		request.setAttribute("theos", ois);
		request.setAttribute("page", page);
		request.setAttribute("o", orderDAO.get(oid));

		return "admin/listOrderItem.jsp";
	}

}
