package com.binarycube.bp.cloud.actions;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.binarycube.bp.cloud.util.CloudStorageHelper;

/**
 * Takes the source file to load from cloud Storage and passess to the 
 * main processing code. Returns a JSON response with the batch processing details.
 * 
 * @author chris
 *
 */
@SuppressWarnings("serial")
public class DeleteServlet extends HttpServlet {

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		
		 
		String fileName = req.getParameter("file");
		resp.setContentType("application/json");
		
		if (fileName != null) {
			CloudStorageHelper storageHelper = (CloudStorageHelper) getServletContext().getAttribute("storageHelper");
			if (storageHelper.deleteFile(fileName, getServletContext().getInitParameter("datafile.bucket"))) {
				resp.getWriter().println("{\"status\":\"ok\"}");
				return;
			} 
		}
		
		
		resp.getWriter().println("{status:'error',message:'There was a problem removing the file'}");
		
		
		 
	}
}