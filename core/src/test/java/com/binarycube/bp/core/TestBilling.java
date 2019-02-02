package com.binarycube.bp.core;

import static com.google.common.truth.Truth.assertThat;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.binarycube.bp.core.model.Trip;
import com.binarycube.bp.core.util.TripCoster;

/**
 * Unit tests for Billing
 */
 
@RunWith(JUnit4.class)
public class TestBilling {

	@Before
	public void setUp() throws Exception {

	}

	@After
	public void tearDown() {

	}

	
	/**
	 * Validate the creation of a Trip object and the calculation of costings
	 * @throws Exception
	 */
	@Test
	public void test_trip_12_costings() throws Exception {
		Trip t12 = new Trip.Builder().fromStop("Stop1").toStop("Stop2").build();
		TripCoster.cost(t12);
		assertThat(t12.getChargeAmount().equals(3.25));
	}

	@Test
	public void test_trip_23_costings() throws Exception {
		Trip t23 = new Trip.Builder().fromStop("Stop2").toStop("Stop3").build();
		TripCoster.cost(t23);
		assertThat(t23.getChargeAmount().equals(5.50));
	} 

	@Test
	public void test_trip_13_costings() throws Exception {
		Trip t13 = new Trip.Builder().fromStop("Stop1").toStop("Stop3").build();
		TripCoster.cost(t13);
		assertThat(t13.getChargeAmount().equals(7.30));
	} 
	
	
	@Test
	public void test_cancelled_costings() throws Exception {
		Trip tx = new Trip.Builder().fromStop("Stop1").toStop("Stop1").build();
		TripCoster.cost(tx);
		assertThat(tx.getChargeAmount().equals(0));
	} 
	
	@Test
	public void test_partial_stop1() throws Exception {
		Trip tp = new Trip.Builder().fromStop("Stop1").build();
		TripCoster.cost(tp);
		assertThat(tp.getChargeAmount().equals(7.30));
	} 
	
	@Test
	public void test_partial_stop2() throws Exception {
		Trip tp = new Trip.Builder().fromStop("Stop2").build();
		TripCoster.cost(tp);
		assertThat(tp.getChargeAmount().equals(5.50));
	} 
	
	@Test
	public void test_partial_stop3() throws Exception {
		Trip tp = new Trip.Builder().fromStop("Stop3").build();
		TripCoster.cost(tp);
		assertThat(tp.getChargeAmount().equals(7.30));
	} 
	
	
	@Test
	public void test_Dump_Cost_Map() throws Exception {
		System.out.println(TripCoster.getCostingMap());
		
	}
	
}
