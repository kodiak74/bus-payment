package com.binarycube.bp.core;

import static com.google.common.truth.Truth.assertThat;

import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.binarycube.bp.core.model.Trip;
import com.binarycube.bp.core.util.DateUtil;
import com.binarycube.bp.core.util.StringUtil;
import com.binarycube.bp.core.util.TripCoster;

/**
 * Unit tests for Trip
 */
 
@RunWith(JUnit4.class)
public class TestTrip {

	@Before
	public void setUp() throws Exception {

	}

	@After
	public void tearDown() {

	}

	 
	@Test
	public void test_Duration() throws Exception {
		Date start = DateUtil.fromDateString("02-02-2019 12:20:00");
		Date finish = DateUtil.fromDateString("02-02-2019 12:22:00");
		Trip tp = new Trip.Builder().started(start).finished(finish).build();
		assertThat(tp.getDuration() == 120);
	}

 
	 
	 
	
}
