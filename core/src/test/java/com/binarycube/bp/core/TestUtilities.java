package com.binarycube.bp.core;

import static com.google.common.truth.Truth.assertThat;

import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.binarycube.bp.core.util.DateUtil;
import com.binarycube.bp.core.util.StringUtil;
import com.binarycube.bp.core.util.TripCoster;

/**
 * Unit tests for Utility components
 */
 
@RunWith(JUnit4.class)
public class TestUtilities {

	@Before
	public void setUp() throws Exception {

	}

	@After
	public void tearDown() {

	}

	
	 
	@Test
	public void test_Date_Utility() throws Exception {
		String source ="02-02-2019 12:20:00";
		Date dt = DateUtil.fromDateString(source);
		String parsed = DateUtil.toDateString(dt);
		assertThat(source.equals(parsed));
	}

	@Test
	public void test_String_Utility() throws Exception {
		String source = null;
		String parsed = StringUtil.getString(source);
		assertThat(parsed != null);
	}
	 
	 
	
}
