package com.tmall.servlet;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tmall.beans.Product;
import com.tmall.beans.Property;
import com.tmall.beans.PropertyValue;
import com.tmall.util.Page;

@SuppressWarnings("serial")
public class PropertyValueServlet extends BackServlet {

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
		Map<String, String> params = new HashMap<String, String>();
		super.parseUpload(request, params);

		// Logger logger = Logger.getLogger("update");
		// logger.info(params.toString());

		int cid = Integer.parseInt(params.get("cid"));
		int pid = Integer.parseInt(params.get("pid"));
		List<Property> pps = propertyDAO.list(cid);
		Product p = productDAO.get(pid);

		Iterator<Property> it = pps.iterator();
		while (it.hasNext()) {
			Property pp = it.next();
			PropertyValue ppv = new PropertyValue();
			ppv.setProduct(p);
			ppv.setProperty(pp);
			ppv.setValue(params.get("" + pp.getId()));
			int ppvid = propertyValueDAO.isExist(pid, pp.getId());
			if (ppvid == -1) {
				propertyValueDAO.add(ppv);
			} else {
				ppv.setId(ppvid);
				propertyValueDAO.update(ppv);
			}
		}

		return "@admin_PropertyValue_list?cid=" + cid + "&pid=" + pid;
	}

	public String list(HttpServletResponse response, HttpServletRequest request, Page page) {
		int cid = Integer.parseInt(request.getParameter("cid"));
		int pid = Integer.parseInt(request.getParameter("pid"));
		List<Property> pps = propertyDAO.list(cid);
		propertyValueDAO.init(pid, pps);
		List<PropertyValue> ppvs = propertyValueDAO.list(pid);

		request.setAttribute("theppvs", ppvs);
		request.setAttribute("p", productDAO.get(pid));

		return "admin/editPropertyValue.jsp";
	}
}
