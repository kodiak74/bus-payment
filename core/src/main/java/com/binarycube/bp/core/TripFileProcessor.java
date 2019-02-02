package com.binarycube.bp.core;

import java.io.PrintWriter;
import java.io.Writer;

import com.binarycube.bp.core.model.Trip;
/**
 * Generates the contents of the Trip output file from a processing run.
 * @author chris
 *
 */

public class TripFileProcessor implements ITripProcessor {
	
	private PrintWriter outputWriter;
	private String header = "Started, Finished, DurationSecs, FromStopId, ToStopId, ChargeAmount, CompanyId, BusID, PAN, Status";
	
	public TripFileProcessor(Writer w) {
		outputWriter= new PrintWriter(w);
		outputWriter.println(header);
	}
	

	public void process(Trip t) {
		outputWriter.println(t.toCSVString());
		
	}

}
