package com.binarycube.bp.cloud.util;

import java.util.Map;

import com.binarycube.bp.cloud.model.TripHelper;
import com.binarycube.bp.core.ITripProcessor;
import com.binarycube.bp.core.model.Trip;
/**
 * Writes the Trip information to GoogleCloud Datastore entry
 * @author chris
 *
 */

public class TripDatastoreProcessor implements ITripProcessor {

	public TripDatastoreProcessor( ) {
	}
	
	public void process(Trip t) {
		DatastoreHelper dsh = new DatastoreHelper("TRIP");
		Map<String,Object> data = TripHelper.toDatastore(t);
		String id = t.getTripID() == null ? "<ERROR - NO UUID SET FOR TRIP>" : t.getTripID().toString();
		dsh.create(id, data);
		//System.out.println("Created trip - " + id);
		 
	}

}
