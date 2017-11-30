package com.tmall.servlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tmall.util.Page;

public class ProductImageServlet extends BackServlet {

	public String add(HttpServletResponse response, HttpServletRequest request, Page page) {

		return "@admin_Product_list？cid=";
	}

	public String delete(HttpServletResponse response, HttpServletRequest request, Page page) {

		return "@admin_Product_list？cid=";
	}

	public String edit(HttpServletResponse response, HttpServletRequest request, Page page) {

		return "admin/editProduct.jsp?cid=";
	}

	public String update(HttpServletResponse response, HttpServletRequest request, Page page) {

		return "@admin_Product_list?cid=";
	}

	public String list(HttpServletResponse response, HttpServletRequest request, Page page) {

		return "admin/listProduct.jsp";
	}
}
