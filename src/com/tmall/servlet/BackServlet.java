package com.tmall.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.tmall.dao.CategoryDAO;
import com.tmall.dao.OrderDAO;
import com.tmall.dao.OrderItemDAO;
import com.tmall.dao.ProductDAO;
import com.tmall.dao.ProductImageDAO;
import com.tmall.dao.PropertyDAO;
import com.tmall.dao.PropertyValueDAO;
import com.tmall.dao.ReviewDAO;
import com.tmall.dao.UserDAO;
import com.tmall.util.Page;

@SuppressWarnings("serial")
abstract public class BackServlet extends HttpServlet {

	abstract public String add(HttpServletResponse response, HttpServletRequest request, Page page);

	abstract public String delete(HttpServletResponse response, HttpServletRequest request, Page page);

	abstract public String edit(HttpServletResponse response, HttpServletRequest request, Page page);

	abstract public String update(HttpServletResponse response, HttpServletRequest request, Page page);

	abstract public String list(HttpServletResponse response, HttpServletRequest request, Page page);

	protected CategoryDAO categoryDAO = new CategoryDAO();
	protected OrderDAO orderDAO = new OrderDAO();
	protected OrderItemDAO orderItemDAO = new OrderItemDAO();
	protected ProductDAO productDAO = new ProductDAO();
	protected ProductImageDAO productImageDAO = new ProductImageDAO();
	protected PropertyDAO propertyDAO = new PropertyDAO();
	protected PropertyValueDAO propertyValueDAO = new PropertyValueDAO();
	protected ReviewDAO reviewDAo = new ReviewDAO();
	protected UserDAO userDAO = new UserDAO();

	@Override
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			int begin = 0;
			int singleCount = 5;
			try {
				begin = Integer.parseInt(request.getParameter("page.begin"));
			} catch (Exception e) {
			}
			try {
				singleCount = Integer.parseInt(request.getParameter("page.singleCount"));
			} catch (Exception e) {
			}

			Page page = new Page(begin, singleCount);

			String method = (String) request.getAttribute("method");
			Method m = this.getClass().getMethod(method, HttpServletResponse.class, HttpServletRequest.class,
					Page.class);
			String redirect = m.invoke(this, response, request, page).toString();

			if (redirect.startsWith("@")) {
				response.sendRedirect(redirect.substring(1));
			} else if (redirect.startsWith("%")) {
				response.getOutputStream().print(redirect.substring(1));
			} else {
				request.getRequestDispatcher(redirect).forward(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// public void doPost(HttpServletRequest request, HttpServletResponse response)
	// throws ServletException, IOException {
	// doGet(request, response);
	// }

	public InputStream parseUpload(HttpServletRequest request, Map<String, String> params) {
		InputStream is = null;
		try {
			DiskFileItemFactory fileFactory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(fileFactory);

			fileFactory.setSizeThreshold(1024 * 1024 * 10);

			List<FileItem> files = upload.parseRequest(request);
			Iterator<FileItem> it = files.iterator();
			while (it.hasNext()) {
				FileItem file = (FileItem) it.next();
				if (!file.isFormField()) {
					is = file.getInputStream();
				} else {
					String paramName = file.getFieldName();
					String paramValue = file.getString();
					paramValue = new String(paramValue.getBytes("ISO-8859-1"), "UTF-8");
					params.put(paramName, paramValue);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return is;
	}

}