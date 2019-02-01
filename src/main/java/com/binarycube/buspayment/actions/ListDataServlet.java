package com.binarycube.buspayment.actions;

/**
 * Retrieve list of files stored in the CloudStorage Bucket
 */

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.binarycube.buspayment.util.CloudStorageHelper;

@SuppressWarnings("serial")
public class ListDataServlet extends HttpServlet {

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

		CloudStorageHelper storageHelper = (CloudStorageHelper) getServletContext().getAttribute("storageHelper");
		List<Map<String, Object>> files = storageHelper.getFiles(getServletContext().getInitParameter("datafile.bucket"));
		//Push result into context for use in JSP
		req.getSession().getServletContext().setAttribute("datafiles", files);
		req.setAttribute("page", "list");
		req.getRequestDispatcher("/base.jsp").forward(req, resp);
	}
}