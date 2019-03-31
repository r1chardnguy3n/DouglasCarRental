package com.DouglasCarRental.Test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.DouglasCarRental.Entity.User;

public class UserTest {

	User user = new User();
	
	@Test 
	public void testUserID() {
	
		user.setId(5);
		
		assertEquals(user.getId(), 5);
		
	}
	
	@Test 
	public void testUserName() {
	
		user.setName("Mark");
		
		assertEquals(user.getName(), "Mark");
		
	}
	
	@Test 
	public void testUserLastName() {
	
		user.setLastname("Lee");
		
		assertEquals(user.getLastname(), "Lee");
		
	}
	
	@Test 
	public void testUserBirthday() {
	
		user.setBirthday("5-24-1234");
		
		assertEquals(user.getBirthday(), "5-24-1234");
		
	}
	
	@Test 
	public void testUserEmail() {
	
		user.setEmail("richard@gmail.com");
		
		assertEquals(user.getEmail(), "richadfdrd@gmail.com");
		
	}
	
	@Test 
	public void testUserPassword() {
	
		user.setPassword("12345");
		
		assertEquals(user.getPassword(), "12345");
		
	}

	
	
}
