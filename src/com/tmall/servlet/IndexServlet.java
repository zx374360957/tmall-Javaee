package com.tmall.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.tmall.beans.Category;
import com.tmall.beans.Order;
import com.tmall.beans.OrderItem;
import com.tmall.beans.Product;
import com.tmall.beans.PropertyValue;
import com.tmall.beans.User;
import com.tmall.dao.CategoryDAO;
import com.tmall.dao.OrderDAO;
import com.tmall.dao.OrderItemDAO;
import com.tmall.dao.ProductDAO;
import com.tmall.dao.ProductImageDAO;
import com.tmall.dao.PropertyValueDAO;

@SuppressWarnings("serial")
public class IndexServlet extends HttpServlet {
	private CategoryDAO categoryDAO = new CategoryDAO();
	private ProductDAO productDAO = new ProductDAO();
	private PropertyValueDAO propertyValueDAO = new PropertyValueDAO();
	private ProductImageDAO productImageDAO = new ProductImageDAO();
	private OrderDAO orderDAO = new OrderDAO();
	private OrderItemDAO orderItemDAO = new OrderItemDAO();

	@Override
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String method = (String) request.getAttribute("method");

		HttpSession session = request.getSession();
		@SuppressWarnings("unchecked")
		List<OrderItem> shoppingCart = (List<OrderItem>) session.getAttribute("shoppingCart");
		if (shoppingCart == null) {
			shoppingCart = new LinkedList<OrderItem>();
			session.setAttribute("shoppingCart", shoppingCart);
			session.setAttribute("shoppingCartCount", shoppingCart.size());
		}

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
		case "search":
			search(request, response);
			break;
		case "login":
			request.getRequestDispatcher("fore/login.jsp").forward(request, response);
			break;
		case "register":
			request.getRequestDispatcher("fore/register.jsp").forward(request, response);
			break;
		case "logout":
			session.invalidate();
			response.sendRedirect("foreindex");
			break;
		case "shoppingCart":
			request.getRequestDispatcher("fore/shoppingCart.jsp").forward(request, response);
			break;
		case "toCreateOrder":
			toCreateOrder(request, response);
			break;
		case "createOrder":
			createOrder(request, response);
			break;
		case "toPay":
			toPay(request, response);
			break;
		case "pay":
			pay(request, response);
			break;
		case "listOrder":
			listOrder(request, response);
			break;
		case "toConfirm":
			toConfirm(request, response);
			break;
		case "confirm":
			confirm(request, response);
			break;
		case "review":
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

	public void search(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String name = request.getParameter("name");
		// name = new String(name.getBytes("ISO-8859-1"), "UTF-8");
		List<Product> ps = productDAO.search(name);

		request.setAttribute("theps", ps);
		request.getRequestDispatcher("fore/listSearch.jsp").forward(request, response);
	}

	@SuppressWarnings("unchecked")
	public void toCreateOrder(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		if (user == null) {
			response.sendRedirect("foreindex");
			return;
		}

		String pid = request.getParameter("pid");
		// Logger.getLogger("p").info(pid);
		List<OrderItem> orderItems = new ArrayList<OrderItem>();
		if (pid == null) { // 购物车产生的订单
			String[] pidStrings = request.getParameterValues("cartPid");
			List<OrderItem> shoppingCart = new ArrayList<OrderItem>();
			shoppingCart.addAll((List<OrderItem>) session.getAttribute("shoppingCart"));
			Iterator<OrderItem> it = shoppingCart.iterator();
			while (it.hasNext()) {
				OrderItem oi = it.next();
				for (String pidString : pidStrings) {
					if (oi.getProduct().getId() == Integer.parseInt(pidString)) {
						oi.setUser(user);
						orderItems.add(oi);
						it.remove();
					}
				}
			}
		} else { // 商品页产生的订单
			OrderItem oi = new OrderItem();
			Product p = productDAO.get(Integer.parseInt(pid));
			productImageDAO.fillImage(p);
			int number = Integer.parseInt(request.getParameter("selectedCount"));
			oi.setNumber(number);
			oi.setProduct(p);
			oi.setUser(user);
			orderItems.add(oi);
		}
		float sum = 0;
		for (OrderItem oi : orderItems) {
			sum += oi.getProduct().getPromotePrice() * oi.getNumber();
		}
		session.setAttribute("total", sum);
		session.setAttribute("theois", orderItems);

		request.getRequestDispatcher("fore/createOrder.jsp").forward(request, response);
	}

	public void createOrder(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Date createDate = new Date();
		DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
		int uuid = new Random().nextInt(10000) + 1111;
		String orderCode = df.format(createDate) + uuid;
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		if (user == null) {
			response.sendRedirect("foreindex");
			return;
		}

		Map<String, String> params = new HashMap<String, String>();
		parseUpload(request, params);
		String address = params.get("addr");
		String post = params.get("post");
		String receiver = params.get("revName");
		String mobile = params.get("revTelNum");
		String userMessage = params.get("userMessage");

		Order order = new Order();
		order.setCreateDate(createDate);
		order.setOrderCode(orderCode);
		order.setUser(user);
		@SuppressWarnings("unchecked")
		List<OrderItem> orderItems = (List<OrderItem>) session.getAttribute("theois");
		order.setOrderItems(orderItems);
		order.setTotalNumber(orderItems.size());
		order.setStatus("待支付");
		order.setAddress(address);
		order.setPost(post);
		order.setReceiver(receiver);
		order.setMobile(mobile);
		order.setUserMessage(userMessage);

		float sum = 0;
		for (OrderItem oi : orderItems) {
			sum += oi.getProduct().getPromotePrice() * oi.getNumber();
		}
		order.setTotal(sum);
		orderDAO.add(order);
		orderItemDAO.addAll(order);
		session.setAttribute("shoppingCart", orderItems);
		session.setAttribute("shoppingCartCount", orderItems.size());

		request.setAttribute("o", order);

		request.getRequestDispatcher("fore/pay.jsp").forward(request, response);
	}

	public void toPay(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = (User) request.getSession().getAttribute("user");
		if (user == null) {
			response.sendRedirect("foreindex");
			return;
		}

		int oid = Integer.parseInt(request.getParameter("oid"));
		Order o = orderDAO.get(oid);
		request.setAttribute("o", o);
		request.getRequestDispatcher("fore/pay.jsp").forward(request, response);
	}

	@SuppressWarnings("deprecation")
	public void pay(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = (User) request.getSession().getAttribute("user");
		if (user == null) {
			response.sendRedirect("foreindex");
			return;
		}

		int oid = Integer.parseInt(request.getParameter("oid"));
		Order o = orderDAO.get(oid);
		o.setTotal(orderItemDAO.getTotalCost(oid));
		// Logger.getLogger("pay").info("" + o.getTotal());
		o.setPayDate(new Date());
		o.setStatus("待发货");
		orderDAO.update(o);

		request.setAttribute("o", o);

		Date estimatedTime = new Date(o.getCreateDate().getTime());
		estimatedTime.setDate(estimatedTime.getDate() + 7);
		request.setAttribute("time", estimatedTime);
		request.getRequestDispatcher("fore/paySussess.jsp").forward(request, response);
	}

	public void listOrder(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		User user = (User) request.getSession().getAttribute("user");
		if (user == null) {
			response.sendRedirect("foreindex");
			return;
		}

		List<Order> os = orderDAO.list(user.getId());
		// Logger logger = Logger.getLogger("o");
		// for (Order o : os) {
		// String s = (o.getOrderItems() == null) ? "null" :
		// o.getOrderItems().toString();
		// logger.info(s);
		// }
		request.setAttribute("theos", os);
		request.getRequestDispatcher("fore/listOrder.jsp").forward(request, response);
	}

	public void toConfirm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		User user = (User) request.getSession().getAttribute("user");
		if (user == null) {
			response.sendRedirect("foreindex");
			return;
		}

		int oid = Integer.parseInt(request.getParameter("oid"));
		Order o = orderDAO.get(oid);
		o.setOrderItems(orderItemDAO.list(oid));
		request.setAttribute("o", o);
		request.getRequestDispatcher("fore/confirm.jsp").forward(request, response);
	}

	public void confirm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = (User) request.getSession().getAttribute("user");
		if (user == null) {
			response.sendRedirect("foreindex");
			return;
		}

		int oid = Integer.parseInt(request.getParameter("oid"));
		Order o = orderDAO.get(oid);
		o.setStatus("待评价");
		o.setConfirmDate(new Date());
		orderDAO.update(o);
		request.setAttribute("o", o);
		request.getRequestDispatcher("fore/confirmSuccess.jsp").forward(request, response);
	}

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
