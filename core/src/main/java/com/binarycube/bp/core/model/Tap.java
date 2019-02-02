package com.binarycube.bp.core.model;

import java.util.Date;
import java.util.UUID;

import com.binarycube.bp.core.util.DateUtil;

/**
 * Leverages the Builder pattern to construct a Tap object.
 * https://en.wikipedia.org/wiki/Builder_pattern
 * 
 * @author chris
 *
 */

public class Tap {
	private UUID tapID;

	private int id; // input file row/id
	private Date dt; // UTC
	private TapType tt;
	private String stopID, companyID, busID, pan;

	private Tap(Builder b) {
		this.id = b.id;
		this.dt = b.dt;
		this.tt = b.tt;
		this.stopID = b.stopID;
		this.companyID = b.companyID;
		this.busID = b.busID;
		this.pan = b.pan;
	}

	public static class Builder {
		private UUID tapID = UUID.randomUUID();
		private int id; // input file row/id
		private Date dt; // UTC
		private TapType tt;
		private String stopID, companyID, busID, pan;

		public Builder tapID(UUID tapID) {
			this.tapID = tapID;
			return this;
		}

		public Builder id(String id) {
			this.id = Integer.valueOf(id);
			return this;
		}

		public Builder dateTime(String dt) {
			this.dt = DateUtil.fromDateString(dt);
			return this;
		}

		public Builder tapType(String tt) {
			this.tt = TapType.valueOf(tt.trim());
			return this;
		}

		public Builder stopID(String stopID) {
			this.stopID = stopID;
			return this;
		}

		public Builder companyID(String companyID) {
			this.companyID = companyID;
			return this;
		}

		public Builder busID(String busID) {
			this.busID = busID;
			return this;
		}

		public Builder pan(String pan) {
			this.pan = pan;
			return this;
		}

		public Tap build() {
			return new Tap(this);
		}
	}

	public UUID getTapID() {
		return tapID;
	}

	public int getId() {
		return id;
	}

	public Date getDate() {
		return dt;
	}

	public TapType getTapType() {
		return tt;
	}

	public String getStopID() {
		return stopID;
	}

	public String getCompanyID() {
		return companyID;
	}

	public String getBusID() {
		return busID;
	}

	public String getPan() {
		return pan;
	}

	
	public String toString() {
		return this.toCSVString();
	}

	/*
	 * Return CSV string of Trip
	 */
	public String toCSVString() {
		StringBuilder buffer = new StringBuilder();
		buffer.append(id).append(",");
		buffer.append(DateUtil.toDateString(dt)).append(",");
		buffer.append(tt).append(",");
		buffer.append(stopID).append(",");
		buffer.append(companyID).append(",");
		buffer.append(busID).append(",");
		buffer.append(pan);

		return buffer.toString();

	}
	
}
