package com.tmall.servlet;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tmall.beans.Category;
import com.tmall.util.ImageUtil;
import com.tmall.util.Page;

@SuppressWarnings("serial")
public class CategoryServlet extends BackServlet {

	public String add(HttpServletResponse response, HttpServletRequest request, Page page) {
		Map<String, String> params = new HashMap<>();
		InputStream is = super.parseUpload(request, params);

		Category c = new Category();
		c.setName(params.get("name"));
		categoryDAO.add(c);

		File imgFloder = new File(request.getSession().getServletContext().getRealPath("/image/category"));
		if (!imgFloder.exists()) {
			imgFloder.mkdirs();
		}
		File img = new File(imgFloder, c.getId() + ".jpg");
		try {
			if (is != null && is.available() != 0) {
				FileOutputStream fos = new FileOutputStream(img);
				byte[] buffer = new byte[1024 * 1024];
				int len = 0;

				while ((len = is.read(buffer)) != -1) {
					fos.write(buffer, 0, len);
				}
				fos.flush();
				fos.close();

				BufferedImage bimg = ImageUtil.change2jpg(img);
				ImageIO.write(bimg, "jpg", img);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "@admin_Category_list";
	}

	public String delete(HttpServletResponse response, HttpServletRequest request, Page page) {
		int id = Integer.parseInt(request.getParameter("id"));

		categoryDAO.dalete(id);

		File imgFloder = new File(request.getSession().getServletContext().getRealPath("/image/category"));
		File img = new File(imgFloder, id + ".jpg");
		if (img.exists()) {
			img.delete();
		}

		return "@admin_Category_list";
	}

	public String edit(HttpServletResponse response, HttpServletRequest request, Page page) {
		int id = Integer.parseInt(request.getParameter("id"));
		Category c = new Category();
		c.setId(id);

		request.setAttribute("c", c);

		return "admin/editCategory.jsp";
	}

	public String update(HttpServletResponse response, HttpServletRequest request, Page page) {
		Map<String, String> params = new HashMap<String, String>();
		InputStream is = super.parseUpload(request, params);

		Category c = new Category();
		int id = Integer.parseInt(params.get("id"));
		c.setName(params.get("name"));
		c.setId(id);
		categoryDAO.update(c);

		File imgFloder = new File(request.getSession().getServletContext().getRealPath("/image/category"));
		if (!imgFloder.exists()) {
			imgFloder.mkdirs();
		}
		File img = new File(imgFloder, id + ".jpg");
		try {
			FileOutputStream fos = new FileOutputStream(img);
			byte[] buffer = new byte[1024 * 1024];
			int len = 0;
			while ((len = is.read(buffer)) != -1) {
				fos.write(buffer, 0, len);
			}
			fos.flush();
			fos.close();
			BufferedImage i = ImageUtil.change2jpg(img);
			ImageIO.write(i, "jpg", img);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return "@admin_Category_list";
	}

	public String list(HttpServletResponse response, HttpServletRequest request, Page page) {
		List<Category> categorys = categoryDAO.list(page.getBegin(), page.getSingleCount());
		int total = categoryDAO.getTotal();
		page.setTotalCount(total);

		request.setAttribute("thecs", categorys);
		request.setAttribute("page", page);

		return "admin/listCategory.jsp";
	}
}