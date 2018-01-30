package com.tmall.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tmall.beans.Admin;
import com.tmall.dao.AdminDAO;

/**
 * Servlet implementation class AdminServlet
 */
public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private AdminDAO adminDAO = new AdminDAO();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String adminname = request.getParameter("adminname");
		String password = request.getParameter("password");
		Admin admin = adminDAO.get(adminname, password);

		if (admin != null) {
			HttpSession session = request.getSession();
			session.setAttribute("admin", admin);
			String uri = (String) session.getAttribute("uri");
			System.out.println(uri);
			response.sendRedirect(uri);
		} else {
			response.sendRedirect("admin/loginError.jsp");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
