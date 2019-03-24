package com.DouglasCarRental.Test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.DouglasCarRental.Entity.Reservation;

public class ReservationTest {

	Reservation reservation = new Reservation();
	
	@Test 
	public void testReservationMake() {
		
		reservation.setMake("Toyota");
		
		assertEquals(reservation.getMake(), "Toyota");
		
	}
	
	@Test 
	public void testReservationModel() {
		
		reservation.setModel("XYZ");
		
		assertEquals(reservation.getModel(), "XYZ");
		
	}
	
	@Test 
	public void testReservationYear() {
		
		reservation.setYear("1995");
		
		assertEquals(reservation.getYear(), "1995");
		
	}
	
	@Test 
	public void testReservationType() {
		
		reservation.setType("SUV");
		
		assertEquals(reservation.getType(), "SUV");
		
	}
	
	@Test 
	public void testReservationPrice() {
		
		reservation.setPrice(123);
		
		assertEquals(reservation.getPrice(), 123);
		
	}
	
	@Test 
	public void testReservationCusId() {
		
		reservation.setCusId(3);
		
		assertEquals(reservation.getCusId(), 3);
		
	}
	
	@Test 
	public void testReservationCusName() {
		
		reservation.setCusName("Joe");
		
		assertEquals(reservation.getCusName(), "Joe");
		
	}
	
	@Test 
	public void testReservationStartDate() {
		
		reservation.setStartDate("12/13/1234");
		
		assertEquals(reservation.getStartDate(), "12/13/1234");
		
	}
	
	@Test 
	public void testReservationEndDate() {
		
		reservation.setEndDate("12/13/1234");
		
		assertEquals(reservation.getEndDate(), "12/13/1234");
		
	}
	
	@Test 
	public void testReservationUnlimitedKms() {
		
		reservation.setUnlimitedKms(1240);
		
		assertEquals(reservation.getUnlimitedKms(), 1240);
		
	}
	
	@Test 
	public void testReservationExtraDriver() {
		
		reservation.setExtraDriver(3);
		
		assertEquals(reservation.getExtraDriver(), 3);
		
	}
	
	@Test 
	public void testReservationTotalPrice() {
		
		reservation.setTotalPrice(1345.30);
		
		assertEquals(reservation.getTotalPrice(), 1345.30);
		
	}
	
	

}
