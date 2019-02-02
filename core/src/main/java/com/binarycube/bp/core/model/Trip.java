package com.binarycube.bp.core.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

import com.binarycube.bp.core.util.DateUtil;
import com.binarycube.bp.core.util.StringUtil;

/**
 * Leverages the Builder pattern to construct a Trip object.
 * https://en.wikipedia.org/wiki/Builder_pattern
 * 
 * @author chris
 *
 */

public class Trip {
	private UUID tripID, tapOn, tapOff;
	private Date started, finished;
	private long duration;
	private String fromStop, toStop;
	private BigDecimal chargeAmount;
	private String companyID, busID, pan, status;

	private Trip(Builder b) {
		this.tripID = b.tripID;
		this.tapOn = b.tapOn;
		this.tapOff = b.tapOff;
		this.started = b.started;
		this.finished = b.finished;
		this.duration = b.duration;
		this.fromStop = b.fromStop;
		this.toStop = b.toStop;
		this.chargeAmount = b.chargeAmount;
		this.companyID = b.companyID;
		this.busID = b.busID;
		this.pan = b.pan;
		this.status = b.status;
	}

	public static class Builder {

		private UUID tripID = UUID.randomUUID();
		private UUID tapOn, tapOff;
		private Date started, finished;
		private long duration;
		private String fromStop, toStop;
		private BigDecimal chargeAmount;
		private String companyID, busID, pan, status;

		public Builder tripID(UUID tripID) {
			this.tripID = tripID;
			return this;
		}

		public Builder tapOn(UUID tapOn) {
			this.tapOn = tapOn;
			return this;
		}

		public Builder tapOff(UUID tapOff) {
			this.tapOff = tapOff;
			return this;
		}

		public Builder started(Date started) {
			this.started = started;
			return this;
		}

		public Builder finished(Date finished) {
			this.finished = finished;
			return this;
		}

		public Builder duration(long duration) {
			this.duration = duration;
			return this;
		}

		public Builder fromStop(String fromStop) {
			this.fromStop = fromStop;
			return this;
		}

		public Builder toStop(String toStop) {
			this.toStop = toStop;
			return this;
		}

		public Builder chargeAmount(BigDecimal chargeAmount) {
			this.chargeAmount = chargeAmount;
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

		public Builder status(String status) {
			this.status = status;
			return this;
		}

		public Trip build() {
			// Compute the duration if we can (in seconds)
			if ((this.started != null) && (this.finished != null)) {
				this.duration = this.finished.getTime() - this.started.getTime();
				if (this.duration != 0) this.duration = this.duration/1000;
			}

			return new Trip(this);
		}

	}

	public UUID getTripID() {
		return tripID;
	}

	public void setTripID(UUID tripID) {
		this.tripID = tripID;
	}

	public UUID getTapOn() {
		return tapOn;
	}

	public void setTapOn(UUID tapOn) {
		this.tapOn = tapOn;
	}

	public UUID getTapOff() {
		return tapOff;
	}

	public void setTapOff(UUID tapOff) {
		this.tapOff = tapOff;
	}

	public Date getStarted() {
		return started;
	}

	public void setStarted(Date started) {
		this.started = started;
	}

	public Date getFinished() {
		return finished;
	}

	public void setFinished(Date finished) {
		this.finished = finished;
	}

	public long getDuration() {
		return duration;
	}

	public void setDuration(long duration) {
		this.duration = duration;
	}

	public String getFromStop() {
		return fromStop;
	}

	public void setFromStop(String fromStop) {
		this.fromStop = fromStop;
	}

	public String getToStop() {
		return toStop;
	}

	public void setToStop(String toStop) {
		this.toStop = toStop;
	}

	public BigDecimal getChargeAmount() {
		return chargeAmount;
	}

	public void setChargeAmount(BigDecimal chargeAmount) {
		this.chargeAmount = chargeAmount;
	}

	public String getCompanyID() {
		return companyID;
	}

	public void setCompanyID(String companyID) {
		this.companyID = companyID;
	}

	public String getBusID() {
		return busID;
	}

	public void setBusID(String busID) {
		this.busID = busID;
	}

	public String getPan() {
		return pan;
	}

	public void setPan(String pan) {
		this.pan = pan;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String toString() {
		return this.toCSVString();
	}

	/*
	 * Return CSV string of Trip
	 */
	public String toCSVString() {
		StringBuilder buffer = new StringBuilder();
		buffer.append(DateUtil.toDateString(started)).append(",");
		buffer.append(DateUtil.toDateString(finished)).append(",");
		buffer.append(duration).append(",");
		buffer.append(StringUtil.getString(fromStop)).append(",");
		buffer.append(StringUtil.getString(toStop)).append(",");
		buffer.append(chargeAmount).append(",");
		buffer.append(StringUtil.getString(companyID)).append(",");
		buffer.append(StringUtil.getString(busID)).append(",");
		buffer.append(StringUtil.getString(pan)).append(",");
		buffer.append(status);

		return buffer.toString();

	}

}
