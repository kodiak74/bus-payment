package com.binarycube.buspayment.actions;
 

 

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.binarycube.buspayment.util.CloudStorageHelper;
import com.google.common.base.Strings;

// [START example]
@SuppressWarnings("serial")
public class ListDataServlet extends HttpServlet {

  @Override
  public void init() throws ServletException {
     

    // [START storageHelper]
    CloudStorageHelper storageHelper = new CloudStorageHelper();
    // [END storageHelper]

     
    // [START save_storage]
    this.getServletContext().setAttribute("storageHelper", storageHelper);
    this.getServletContext().setAttribute(
        "isCloudStorageConfigured",  // Hide upload when Cloud Storage is not configured.
        !Strings.isNullOrEmpty(getServletContext().getInitParameter("datafile.bucket")));
    // [END save_storage]
  }

  @Override
  public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException,
      ServletException {
	  
	  CloudStorageHelper storageHelper =   (CloudStorageHelper) getServletContext().getAttribute("storageHelper");
	 List<Map> files = storageHelper.getFiles(getServletContext().getInitParameter("datafile.bucket")); 
	 
	 req.getSession().getServletContext().setAttribute("datafiles", files);
	 
	 System.out.println("Files:" + files.size());
   
    req.setAttribute("page", "list");
    req.getRequestDispatcher("/base.jsp").forward(req, resp);
  }
}