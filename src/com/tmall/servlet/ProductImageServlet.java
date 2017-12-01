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
import com.tmall.beans.Product;
import com.tmall.beans.ProductImage;
import com.tmall.util.ImageUtil;
import com.tmall.util.Page;

@SuppressWarnings("serial")
public class ProductImageServlet extends BackServlet {

	public String add(HttpServletResponse response, HttpServletRequest request, Page page) {
		Map<String, String> params = new HashMap<String, String>();
		InputStream is = super.parseUpload(request, params);

		int pid = Integer.parseInt(params.get("pid"));
		Product p = productDAO.get(pid);
		ProductImage pi = new ProductImage();
		String type = params.get("type");
		pi.setType(type);
		pi.setProduct(p);

		productImageDAO.add(pi);

		File imgFloder = new File(request.getSession().getServletContext().getRealPath("/image/product/" + pid));
		if (!imgFloder.exists()) {
			imgFloder.mkdirs();
		}
		File img = new File(imgFloder, pi.getId() + ".jpg");
		try {
			if (is != null && is.available() != 0) {
				FileOutputStream fos = new FileOutputStream(img);
				byte[] buffer = new byte[1024 * 1024];
				int len = 0;

				while ((len = is.read(buffer)) != -1) {
					fos.write(buffer, 0, len);
				}
				fos.close();

				BufferedImage bimg = ImageUtil.change2jpg(img);
				ImageIO.write(bimg, "jpg", img);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "@admin_ProductImage_list?id=" + pid;
	}

	public String delete(HttpServletResponse response, HttpServletRequest request, Page page) {
		int id = Integer.parseInt(request.getParameter("id"));
		int pid = Integer.parseInt(request.getParameter("pid"));

		productImageDAO.delete(id);

		File imgFloder = new File(request.getSession().getServletContext().getRealPath("/image/product/" + pid));
		File img = new File(imgFloder, id + ".jpg");
		img.delete();

		return "@admin_ProductImage_list？id=" + pid;
	}

	public String edit(HttpServletResponse response, HttpServletRequest request, Page page) {

		return "";
	}

	public String update(HttpServletResponse response, HttpServletRequest request, Page page) {

		return "";
	}

	public String list(HttpServletResponse response, HttpServletRequest request, Page page) {
		int id = Integer.parseInt(request.getParameter("id"));
		Product p = productDAO.get(id);
		Category c = p.getCategory();
		List<ProductImage> si = productImageDAO.list(id, "single");
		List<ProductImage> di = productImageDAO.list(id, "details");
		p.setProductSingleImages(si);
		p.setProductDetailsImages(di);
		request.setAttribute("c", c);
		request.setAttribute("p", p);

		return "admin/listProductImage.jsp";
	}
}
