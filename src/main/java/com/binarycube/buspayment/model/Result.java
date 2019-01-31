package com.binarycube.buspayment.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

public class Result {

	UUID runID;
	
	Date started, finished;
	long duration;
	String fromStop, toStop;
	BigDecimal amount;
	String companyID, busID, PAN, status;
	
}
