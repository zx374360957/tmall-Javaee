package com.tmall.servlet;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.tmall.beans.Category;
import com.tmall.beans.Property;
import com.tmall.util.Page;

@SuppressWarnings("serial")
public class PropertyServlet extends BackServlet {
	public String add(HttpServletResponse response, HttpServletRequest request, Page page) {
		Map<String, String> params = new HashMap<String, String>();
		int cid = -1;
		try {
			DiskFileItemFactory fileFactory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(fileFactory);

			fileFactory.setSizeThreshold(1024 * 1024 * 10);

			List<FileItem> files = upload.parseRequest(request);
			Iterator<FileItem> it = files.iterator();
			while (it.hasNext()) {
				FileItem file = (FileItem) it.next();
				if (file.isFormField()) {
					String paramName = file.getFieldName();
					String paramValue = file.getString();
					paramValue = new String(paramValue.getBytes("ISO-8859-1"), "UTF-8");
					params.put(paramName, paramValue);
				}
			}

			cid = Integer.parseInt(params.get("cid"));
			Category c = categoryDAO.get(cid);
			String name = params.get("name");

			Property pp = new Property();
			pp.setCategory(c);
			pp.setName(name);

			propertyDAO.add(pp);
			request.setAttribute("cid", cid);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return "@admin_Property_list?cid=" + cid;
	}

	public String delete(HttpServletResponse response, HttpServletRequest request, Page page) {
		int id = Integer.parseInt(request.getParameter("id"));
		int cid = Integer.parseInt(request.getParameter("cid"));
		propertyDAO.dalete(id);

		return "@admin_Property_list?cid=" + cid;
	}

	public String edit(HttpServletResponse response, HttpServletRequest request, Page page) {
		int id = Integer.parseInt(request.getParameter("id"));
		int cid = Integer.parseInt(request.getParameter("cid"));
		Category c = categoryDAO.get(cid);
		Property pp = new Property();
		pp.setId(id);
		pp.setCategory(c);

		request.setAttribute("pp", pp);

		return "admin/editProperty.jsp";
	}

	public String update(HttpServletResponse response, HttpServletRequest request, Page page) {
		String name = request.getParameter("name");
		int cid = Integer.parseInt(request.getParameter("cid"));
		int id = Integer.parseInt(request.getParameter("id"));
		Category c = categoryDAO.get(cid);

		Property pp = new Property();
		pp.setName(name);
		pp.setCategory(c);
		pp.setId(id);

		propertyDAO.update(pp);

		return "@admin_Property_list";
	}

	public String list(HttpServletResponse response, HttpServletRequest request, Page page) {
		int cid = Integer.parseInt(request.getParameter("cid"));
		List<Property> pps = propertyDAO.list(cid, page.getBegin(), page.getSingleCount());
		Category c = categoryDAO.get(cid);
		page.setTotalCount(propertyDAO.getTotal(cid));

		request.setAttribute("thepps", pps);
		request.setAttribute("page", page);
		request.setAttribute("c", c);

		return "admin/listProperty.jsp";
	}
}
