package com.binarycube.bp.core;

import java.io.IOException;
import java.io.Reader;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import com.binarycube.bp.core.model.ProcessingBatch;
import com.binarycube.bp.core.model.Tap;
import com.binarycube.bp.core.model.TapType;
import com.binarycube.bp.core.model.Trip;
import com.binarycube.bp.core.util.TripCoster;

public class BusPaymentProcesser {

	Reader csvSource;
	ITripProcessor tripProcessor;
	 
	
	ProcessingBatch pb;

	public BusPaymentProcesser(String srcLabel, Reader src, ITripProcessor proc) {
		csvSource = src;
		tripProcessor = proc;
		pb = new ProcessingBatch.Builder().datafile(srcLabel).runTS(new Date()).build();
	}

	/**
	 * Process the source file and generate Trip data
	 * 
	 * @return 0 success, anything else is an error.
	 */
	public ProcessingBatch run() {

		// Hold the last tap data from a PAN# to be matched...
		Map<String, Tap> tapRegister = new HashMap<String, Tap>();
		int count = 0;

		Iterable<CSVRecord> records = null;
		try {
			records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(csvSource);
		} catch (IOException e) {
			pb.setMessage(e.getMessage());
			pb.setStatus(-1);
			return pb;
		}
		// Assume column order matching - ID, DateTimeUTC, TapType, StopId, CompanyId,
		// BusID, PAN
		for (CSVRecord record : records) {
			Tap.Builder tapBuilder = new Tap.Builder();
			tapBuilder.id(record.get(0).trim());
			tapBuilder.dateTime(record.get(1).trim());
			tapBuilder.tapType(record.get(2).trim());
			tapBuilder.stopID(record.get(3).trim());
			tapBuilder.companyID(record.get(4).trim());
			tapBuilder.busID(record.get(5).trim());
			tapBuilder.pan(record.get(6).trim());
			Tap currentTap = tapBuilder.build();
			Tap lastTap = tapRegister.get(currentTap.getPan());
			if (lastTap == null) {
				tapRegister.put(currentTap.getPan(), currentTap);
			} else {

				// Check for incomplete previous tap
				if ((lastTap.getTapType() == TapType.ON) && (currentTap.getTapType() == TapType.OFF)) {
					processCompleteTrip(lastTap, currentTap);
					count++;
				} else if ((lastTap.getTapType() == TapType.ON) && (currentTap.getTapType() == TapType.ON)) {
					processIncompleteTrip(lastTap);
					count++;
					tapRegister.put(currentTap.getPan(), currentTap);
				} else {
					// Something weird is going on - get outta here
					pb.setMessage("Previous tap was: (" + lastTap.toString() + ") \n Current tap is: ("
							+ currentTap.toString() + ")");
					pb.setStatus(-1);
					return pb;
				}
				// Clear the register
				tapRegister.remove(lastTap.getPan());
			}
		}
		// Cleanup any remaining incomplete trips...
		for (Tap tap : tapRegister.values()) {
			processIncompleteTrip(tap);
			count++;
		}

		pb.setRecordCount(count);
		pb.setMessage("Proceesing successful");
		return pb;
	}

	private void processCompleteTrip(Tap tapOn, Tap tapOff) {
		// Build trip
		Trip.Builder tripBuilder = new Trip.Builder();
		tripBuilder.busID(tapOn.getBusID());
		tripBuilder.started(tapOn.getDate());
		tripBuilder.finished(tapOff.getDate());
		tripBuilder.fromStop(tapOn.getStopID());
		tripBuilder.toStop(tapOff.getStopID());
		tripBuilder.pan(tapOn.getPan());
		tripBuilder.companyID(tapOn.getCompanyID());
		tripBuilder.batchID(pb.getUid());
		Trip trip = tripBuilder.build();
		TripCoster.cost(trip);
		tripProcessor.process(trip);
	}

	private void processIncompleteTrip(Tap tap) {
		// Build trip
		Trip.Builder tripBuilder = new Trip.Builder();
		tripBuilder.busID(tap.getBusID());
		if (tap.getTapType() == TapType.ON) {
			tripBuilder.started(tap.getDate());
			tripBuilder.fromStop(tap.getStopID());
		} else {
			tripBuilder.finished(tap.getDate());
			tripBuilder.toStop(tap.getStopID());
		}
		tripBuilder.pan(tap.getPan());
		tripBuilder.companyID(tap.getCompanyID());
		tripBuilder.batchID(pb.getUid());
		Trip trip = tripBuilder.build();
		TripCoster.cost(trip);
 		tripProcessor.process(trip);
	}

	 

}
