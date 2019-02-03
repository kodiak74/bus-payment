package com.binarycube.bp.cloud.actions;

import java.io.IOException;
import java.io.Reader;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.binarycube.bp.cloud.util.CloudStorageHelper;
import com.binarycube.bp.cloud.util.TripDatastoreProcessor;
import com.binarycube.bp.core.BusPaymentProcesser;
import com.binarycube.bp.core.ITripProcessor;
import com.binarycube.bp.core.model.ProcessingBatch;

/**
 * Takes the source file to load from cloud Storage and passess to the 
 * main processing code. Returns a JSON response with the batch processing details.
 * 
 * @author chris
 *
 */
@SuppressWarnings("serial")
public class ProcessServlet extends HttpServlet {

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		
		CloudStorageHelper storageHelper = (CloudStorageHelper) getServletContext().getAttribute("storageHelper");
		String fileName = req.getParameter("file");
		
		
		
		//And pass reader and writer to core processor
		try {
		 
			Reader dataReader = storageHelper.getFileReader(fileName,getServletContext().getInitParameter("datafile.bucket"));
			
			ITripProcessor tripProcessor = new TripDatastoreProcessor();
			BusPaymentProcesser bpp = new BusPaymentProcesser(fileName, dataReader, tripProcessor);
			ProcessingBatch pb = bpp.run();
			
			System.out.println("Processed batch: " + pb.getUid());
			
			
			resp.setContentType("application/json");
			resp.getWriter().println(pb.toString());
			//Cleanup
			dataReader.close();
		    
		} catch (IOException e) {
		    e.printStackTrace();
		    resp.getWriter().println("There was an error processing the datafile: " + e.getMessage());
		}    
		
		
		
		 
	}
}