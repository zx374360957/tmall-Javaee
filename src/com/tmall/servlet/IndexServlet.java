package com.tmall.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tmall.beans.Category;
import com.tmall.beans.Product;
import com.tmall.beans.PropertyValue;
import com.tmall.dao.CategoryDAO;
import com.tmall.dao.ProductDAO;
import com.tmall.dao.ProductImageDAO;
import com.tmall.dao.PropertyValueDAO;

@SuppressWarnings("serial")
public class IndexServlet extends HttpServlet {
	private CategoryDAO categoryDAO = new CategoryDAO();
	private ProductDAO productDAO = new ProductDAO();
	private PropertyValueDAO propertyValueDAO = new PropertyValueDAO();
	private ProductImageDAO productImageDAO = new ProductImageDAO();

	@Override
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String method = (String) request.getAttribute("method");

		switch (method) {
		case "index":
			index(request, response);
			break;
		case "category":
			category(request, response);
			break;
		case "product":
			product(request, response);
			break;
		default:
			break;
		}
	}

	public void index(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Category> cs = categoryDAO.list(0, 17);
		productDAO.fill(cs);
		productDAO.fillByRow(cs);

		request.setAttribute("thecs", cs);
		request.getRequestDispatcher("fore/index.jsp").forward(request, response);
	}

	public void category(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int cid = Integer.parseInt(request.getParameter("cid"));
		List<Product> ps = productDAO.list(cid);

		request.setAttribute("theps", ps);
		request.setAttribute("c", categoryDAO.get(cid));
		request.getRequestDispatcher("fore/listCategory.jsp").forward(request, response);
	}

	public void product(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int pid = Integer.parseInt(request.getParameter("pid"));
		Product p = productDAO.get(pid);
		productImageDAO.fillImage(p);
		List<PropertyValue> ppvs = propertyValueDAO.list(p.getId());

		request.setAttribute("p", p);
		request.setAttribute("theppvs", ppvs);

		request.getRequestDispatcher("fore/listProduct.jsp").forward(request, response);
	}
}
