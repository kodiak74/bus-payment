package com.binarycube.bp.cloud.actions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.binarycube.bp.cloud.util.DatastoreHelper;
import com.binarycube.bp.cloud.util.ResultList;
import com.binarycube.bp.core.model.Trip;

@SuppressWarnings("serial")
public class ResultsViewServlet extends HttpServlet {

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		List<Trip> results = new ArrayList<Trip>();
		String filterBy = null;
		String filterValue = null;
		
		String batch = req.getParameter("batch");
		if (batch != null) {
			filterBy = "batchID";
			filterValue = batch;
		}
				
		DatastoreHelper dshTrip = new DatastoreHelper("TRIP");
		ResultList<Trip> trips = dshTrip.list(Trip.class, null, "started", filterBy, filterValue);
		results = trips.result;
		
		req.getSession().getServletContext().setAttribute("results", results);
		req.getSession().getServletContext().setAttribute("batchID", batch);
		req.setAttribute("page", "results");
		req.getRequestDispatcher("/base.jsp").forward(req, resp);
	}
}