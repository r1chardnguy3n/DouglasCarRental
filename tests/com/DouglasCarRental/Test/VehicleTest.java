package com.DouglasCarRental.Test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.DouglasCarRental.Entity.Vehicle;

public class VehicleTest {
	
	Vehicle vehicle = new Vehicle();
	
	
	@Test 
	public void testVehicleID() {
		
		vehicle.setId("25");
		
		assertEquals(vehicle.getId(), "25");
		
	}

	
	@Test 
	public void testVehicleMake() {
		
		vehicle.setMake("Toyota");
		
		assertEquals(vehicle.getMake(), "Toyota");
		
	}
	
	@Test 
	public void testVehicleModel() {
		
		vehicle.setModel("XYZ");
		
		assertEquals(vehicle.getModel(), "XYZz");
		
	}
	
	@Test 
	public void testVehicleYear() {
		
		vehicle.setYear("1995");
		
		assertEquals(vehicle.getYear(), "1996");
		
	}
	
	@Test 
	public void testVehicleType() {
		
		vehicle.setType("SUV");
		
		assertEquals(vehicle.getType(), "SUV");
		
	}
	
	@Test 
	public void testVehiclePrice() {
		
		vehicle.setPrice("134");
		
		assertEquals(vehicle.getPrice(), "123");
		
	}

}
