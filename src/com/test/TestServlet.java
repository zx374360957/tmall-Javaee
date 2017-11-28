package com.test;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
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

@SuppressWarnings("serial")
public class TestServlet extends HttpServlet {
	@Override
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String, String> params = new HashMap<String, String>();
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
			PrintWriter out = response.getWriter();
			out.println(params);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
