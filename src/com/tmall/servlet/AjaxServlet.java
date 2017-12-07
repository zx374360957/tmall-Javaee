package com.tmall.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tmall.beans.OrderItem;
import com.tmall.beans.Product;
import com.tmall.beans.User;
import com.tmall.dao.OrderDAO;
import com.tmall.dao.ProductDAO;
import com.tmall.dao.ProductImageDAO;
import com.tmall.dao.UserDAO;

@SuppressWarnings("serial")
public class AjaxServlet extends HttpServlet {
	UserDAO userDAO = new UserDAO();
	ProductDAO productDAO = new ProductDAO();
	ProductImageDAO productImageDAO = new ProductImageDAO();
	OrderDAO orderDAO = new OrderDAO();

	@Override
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String method = request.getParameter("method");
		switch (method) {
		case "login":
			login(request, response);
			break;
		case "addShoppingCart":
			addShoppingCart(request, response);
			break;
		case "deleteShoppingCart":
			deleteShoppingCart(request, response);
			break;
		case "deleteOrder":
			deleteOrder(request, response);
			break;
		default:
			break;
		}
	}

	public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("user");
		String password = request.getParameter("password");
		User user = userDAO.get(name, password);
		// Logger logger = Logger.getLogger("login");
		// logger.info(name);
		// logger.info(password);
		// logger.info((user == null) + "");
		try (PrintWriter out = response.getWriter()) {
			response.setContentType("text/html; charset=utf-8");
			response.setHeader("Cache-Control", "no-store");
			response.setHeader("Pragme", "no-cache");
			response.setDateHeader("Expires", 0);
			if (user == null) {
				out.print("fail");
			} else {
				request.getSession().setAttribute("user", user);
				out.print("success");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void addShoppingCart(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int pid = Integer.parseInt(request.getParameter("pid"));
		int number = Integer.parseInt(request.getParameter("count"));
		Product p = productDAO.get(pid);
		productImageDAO.fillImage(p);
		@SuppressWarnings("unchecked")
		List<OrderItem> carts = (List<OrderItem>) request.getSession().getAttribute("shoppingCart");
		for (OrderItem oi : carts) {
			if (oi.getProduct().getId() == pid)
				return;
		}
		OrderItem cart = new OrderItem();
		cart.setNumber(number);
		cart.setProduct(p);
		carts.add(cart);
		request.getSession().setAttribute("shoppingCartCount", carts.size());
	}

	public void deleteShoppingCart(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int pid = Integer.parseInt(request.getParameter("pid"));
		@SuppressWarnings("unchecked")
		List<OrderItem> carts = (List<OrderItem>) request.getSession().getAttribute("shoppingCart");
		Iterator<OrderItem> it = carts.iterator();
		while (it.hasNext()) {
			OrderItem cart = it.next();
			if (cart.getProduct().getId() == pid) {
				it.remove();
				break;
			}
		}
		request.getSession().setAttribute("shoppingCartCount", carts.size());
	}

	public void deleteOrder(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int oid = Integer.parseInt(request.getParameter("oid"));
		orderDAO.delete(oid);
	}
}
