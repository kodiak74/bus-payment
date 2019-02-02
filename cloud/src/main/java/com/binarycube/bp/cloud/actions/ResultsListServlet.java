package com.binarycube.bp.cloud.actions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class ResultsListServlet extends HttpServlet {

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

		List<Map> results = new ArrayList<Map>();
		req.getSession().getServletContext().setAttribute("results", results);

		req.setAttribute("page", "results");
		req.getRequestDispatcher("/base.jsp").forward(req, resp);
	}
}