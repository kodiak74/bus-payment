package com.binarycube.bp.core.model;

import java.util.Date;
import java.util.UUID;

import com.google.gson.Gson;

/**
 * Information about the processing run
 * 
 * @author chris
 *
 */

public class ProcessingBatch {
	private UUID uid = UUID.randomUUID();
	private String datafile;
	private Date runTS;
	private String message;
	private int recordCount;
	private int status;

	private ProcessingBatch(Builder b) {
		if (b.uid != null) this.uid = b.uid;
		this.datafile = b.datafile;
		this.runTS = b.runTS;
		 
	}

	public static class Builder {
		private UUID uid;
		private String datafile;
		private Date runTS;
	 
		 

		public Builder uid(UUID uid) {
			this.uid = uid;
			return this;
		}

		public Builder datafile(String datafile) {
			this.datafile = datafile;
			return this;
		}

		public Builder runTS(Date runTS) {
			this.runTS = runTS;
			return this;
		}

		 
		public ProcessingBatch build() {
			return new ProcessingBatch(this);
		}
	}

	public UUID getUid() {
		return uid;
	}

	public String getDatafile() {
		return datafile;
	}

	public Date getRunTS() {
		return runTS;
	}

	 

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getRecordCount() {
		return recordCount;
	}

	public void setRecordCount(int recordCount) {
		this.recordCount = recordCount;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String toString() {
		Gson gson = new Gson();
		return gson.toJson(this);
	}
	
}
