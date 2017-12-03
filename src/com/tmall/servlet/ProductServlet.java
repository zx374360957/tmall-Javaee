package com.tmall.servlet;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tmall.beans.Category;
import com.tmall.beans.Product;
import com.tmall.beans.ProductImage;
import com.tmall.util.Page;

@SuppressWarnings("serial")
public class ProductServlet extends BackServlet {

	public String add(HttpServletResponse response, HttpServletRequest request, Page page) {
		Map<String, String> params = new HashMap<>();
		super.parseUpload(request, params);

		Product p = new Product();
		String name = params.get("name");
		p.setName(name);

		String subtitle = params.get("subtitle");
		p.setSubTitle(subtitle);

		Float orignalPrice = Float.parseFloat(params.get("orignalPrice"));
		p.setOrignalPrice(orignalPrice);

		Float promotePrice = Float.parseFloat(params.get("promotePrice"));
		p.setPromotePrice(promotePrice);

		int stock = Integer.parseInt(params.get("stock"));
		p.setStock(stock);

		Date createDate = new Date();
		p.setCreateDate(createDate);

		int cid = Integer.parseInt(params.get("cid"));
		Category c = categoryDAO.get(cid);
		p.setCategory(c);

		productDAO.add(p);

		return "@admin_Product_list？cid=" + cid;
	}

	public String delete(HttpServletResponse response, HttpServletRequest request, Page page) {
		int cid = Integer.parseInt(request.getParameter("cid"));
		int id = Integer.parseInt(request.getParameter("id"));

		productDAO.delete(id);

		return "@admin_Product_list？cid=" + cid;
	}

	public String edit(HttpServletResponse response, HttpServletRequest request, Page page) {
		int cid = Integer.parseInt(request.getParameter("cid"));
		int id = Integer.parseInt(request.getParameter("id"));
		Product p = new Product();
		p.setId(id);

		request.setAttribute("p", p);

		return "admin/editProduct.jsp?cid=" + cid;
	}

	public String update(HttpServletResponse response, HttpServletRequest request, Page page) {
		Map<String, String> params = new HashMap<String, String>();
		super.parseUpload(request, params);
		Product p = new Product();

		int id = Integer.parseInt(params.get("id"));
		p.setId(id);

		String name = params.get("name");
		p.setName(name);

		String subtitle = params.get("subtitle");
		p.setSubTitle(subtitle);

		Float orignalPrice = Float.parseFloat(params.get("orignalPrice"));
		p.setOrignalPrice(orignalPrice);

		Float promotePrice = Float.parseFloat(params.get("promotePrice"));
		p.setPromotePrice(promotePrice);

		int stock = Integer.parseInt(params.get("stock"));
		p.setStock(stock);

		Date createDate = new Date();
		p.setCreateDate(createDate);

		int cid = Integer.parseInt(params.get("cid"));
		Category c = categoryDAO.get(cid);
		p.setCategory(c);

		productDAO.update(p);

		return "@admin_Product_list?cid=" + cid;
	}

	public String list(HttpServletResponse response, HttpServletRequest request, Page page) {
		int cid = Integer.parseInt(request.getParameter("cid"));
		List<Product> ps = productDAO.list(cid, page.getBegin(), page.getSingleCount());
		Category c = categoryDAO.get(cid);
		int total = productDAO.getTotal(cid);
		page.setTotalCount(total);

		Iterator<Product> it = ps.iterator();
		while (it.hasNext()) {
			Product p = (Product) it.next();
			ProductImage pi = productImageDAO.getOneImage(p.getId(), "single");
			p.setFirstProductImage(pi);
		}

		request.setAttribute("theps", ps);
		request.setAttribute("page", page);
		request.setAttribute("c", c);

		return "admin/listProduct.jsp";
	}

}
