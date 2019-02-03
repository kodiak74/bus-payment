package com.binarycube.bp.cloud.model;

import java.util.HashMap;
import java.util.Map;

import com.binarycube.bp.core.model.Trip;

public class TripHelper {
	
	public static Map<String,Object> toDatastore(Trip trip) {
		java.util.Map<String,Object> data = new HashMap<String,Object>();
		
		data.put("batchID", trip.getBatchID() == null ? "": trip.getBatchID().toString());
		
		data.put("started", trip.getStarted());
		data.put("finished",trip.getFinished());
		data.put("duration",trip.getDuration());
		data.put("fromStop",trip.getFromStop());
		data.put("toStop",trip.getToStop());
		data.put("chargeAmount",trip.getChargeAmount().toString());
		data.put("companyID",trip.getCompanyID());
		data.put("busID",trip.getBusID());
		data.put("pan",trip.getPan());
		data.put("status",trip.getStatus());
		
		return data;
	}
}
