package com.binarycube.bp.core.util;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import com.binarycube.bp.core.model.Trip;
import com.binarycube.bp.core.model.TripCost;
import com.binarycube.bp.core.model.TripStatus;

public class TripCoster {
	
	static TripCost[] source = { new TripCost("Stop1","Stop2", BigDecimal.valueOf(3.25)),
								 new TripCost("Stop2","Stop3", BigDecimal.valueOf(5.50)),
								 new TripCost("Stop1","Stop3", BigDecimal.valueOf(7.30))
								};
	

	//Lookup table for costings
	private static Map<String, BigDecimal> costMap; 
	
	static {
		 Map<String, BigDecimal> tmp = new HashMap<String, BigDecimal>(); 
		 Map<String, BigDecimal> maxPerStop = new HashMap<String, BigDecimal>();
		//Build costings lookup map
		for(TripCost tc : source) {
			tmp.put(tc.getFrom()+"-"+tc.getTo(), tc.getCost());
			tmp.put(tc.getTo()+"-"+tc.getFrom(), tc.getCost());
			
			//Check & update maximum pricing for journey from "fromStop"
			if (maxPerStop.containsKey(tc.getFrom())) {
				BigDecimal current = maxPerStop.get(tc.getFrom());
				if (current.compareTo(tc.getCost()) < 0) maxPerStop.put(tc.getFrom(), tc.getCost());
			} else maxPerStop.put(tc.getFrom(), tc.getCost());
				
			//Check & update maximum pricing for journey from "toStop"
			if (maxPerStop.containsKey(tc.getTo())) {
				BigDecimal current = maxPerStop.get(tc.getTo());
				if (current.compareTo(tc.getCost()) < 0) maxPerStop.put(tc.getTo(), tc.getCost());
			} else maxPerStop.put(tc.getTo(), tc.getCost());
		}
		//Add Partials
		for (String key: maxPerStop.keySet()) {
			tmp.put(key+"-", maxPerStop.get(key));
		}
		
		costMap = tmp;
	}
	
	/**
	 * Calculate the cost of a trip 
	 * Handles the business logic for Completed, Incomplete, and Cancelled Trips
	 * 
	 * @param t
	 */
	public static void cost(Trip t) {
		//Cancelled
		if ((t.getFromStop() != null) && (t.getFromStop().equals(t.getToStop()))) {
			t.setChargeAmount(BigDecimal.valueOf(0));
			t.setStatus(TripStatus.CANCELLED.toString());
			return;
		}
		 
		//Incomplete
		if ((t.getFromStop() == null) || (t.getToStop() == null)) {
			t.setStatus(TripStatus.INCOMPLETE.toString());
		} else {
			t.setStatus(TripStatus.COMPLETED.toString());
		}
		
		//Completed & Incomplete Costing lookup
		String key = StringUtil.getString(t.getFromStop()) + "-" + StringUtil.getString(t.getToStop());
		t.setChargeAmount(costMap.get(key));
	}
	
	//Just for testing 
	public static BigDecimal getCosting(String key) {
		return costMap.get(key);
	}
	
	//Return generated costing map for visual validation
	public static String getCostingMap() {
		StringBuilder buffer = new StringBuilder();
		for (String key: costMap.keySet()) {
			buffer.append(key + "=" + costMap.get(key).toString() + "\n");
		}
		return buffer.toString();
	}
	
}
