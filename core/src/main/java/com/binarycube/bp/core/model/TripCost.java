package com.binarycube.bp.core.model;

import java.math.BigDecimal;

public class TripCost {
	String from, to;
	BigDecimal cost;
	
	public TripCost(String f, String t, BigDecimal c) {
		from = f;
		to = t;
		cost = c;
	}

	public String getFrom() {
		return from;
	}

	public String getTo() {
		return to;
	}

	public BigDecimal getCost() {
		return cost;
	}
	
	
}
