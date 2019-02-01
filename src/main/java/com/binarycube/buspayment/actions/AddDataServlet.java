package com.binarycube.buspayment.actions;

import java.awt.print.Book;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.util.Streams;

import com.binarycube.buspayment.util.CloudStorageHelper;
import com.google.common.base.Strings;

/**
 * Servlet implementation class AddDataFile
 */

@WebServlet(name = "AddDataFile", value = "/bp/data/add")
public class AddDataServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddDataServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute("action", "Add"); // Part of the Header in form.jsp
		request.setAttribute("destination", "/bp/data/add"); // The urlPattern to invoke (this Servlet)
		request.setAttribute("page", "form"); // Tells base.jsp to include form.jsp
		request.getRequestDispatcher("/base.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Require mulitpart requests
		assert ServletFileUpload.isMultipartContent(request);

		CloudStorageHelper storageHelper = (CloudStorageHelper) getServletContext().getAttribute("storageHelper");

		String dataFileURL = null;
		Map<String, String> params = new HashMap<String, String>();
		try {
			FileItemIterator iter = new ServletFileUpload().getItemIterator(request);
			while (iter.hasNext()) {
				FileItemStream item = iter.next();
				if (item.isFormField()) {
					params.put(item.getFieldName(), Streams.asString(item.openStream()));
				} else if (!Strings.isNullOrEmpty(item.getName())) {
					dataFileURL = storageHelper.uploadFile(item,
							getServletContext().getInitParameter("datafile.bucket"));
					System.out.println("Uploaded datafile:" + dataFileURL);
				}
			}
		} catch (FileUploadException e) {
			throw new IOException(e);
		}

		response.sendRedirect("/list");

	}

}
