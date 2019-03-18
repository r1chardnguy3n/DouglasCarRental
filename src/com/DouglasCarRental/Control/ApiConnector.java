package com.DouglasCarRental.Control;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.lang.reflect.Type;

//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;
//import com.google.gson.reflect.TypeToken;


public class ApiConnector {
	
	public void startApi() {
		 try {

	            URL url = new URL("http://localhost:61809/api/Vehicle");
	            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	            conn.setRequestMethod("GET");
	            conn.setRequestProperty("Accept", "application/json");
	            if (conn.getResponseCode() != 200) {
	                throw new RuntimeException("Failed : HTTP Error code : "
	                        + conn.getResponseCode());
	            }
	            
	            InputStreamReader in = new InputStreamReader(conn.getInputStream());
	            BufferedReader br = new BufferedReader(in);
	            String output;
	            
	                     
	            /*
	            StringBuffer response = new StringBuffer();
	            ObjectMapper objMap = new ObjectMapper();
	            
	        
	            ArrayList<VehicleData> vdList = new ArrayList<VehicleData>();
	            VehicleData vd = new VehicleData();
	            */
	            while ((output = br.readLine()) != null) {
	            	/*
	            	response.append(output);
	            	vd = objMap.readValue(response.toString(), VehicleData.class);
	            	vdList.add(new VehicleData(vd.getMake(),vd.getModel(),vd.getType(),vd.getYear()));
	            	*/
	           
	            }
	            conn.disconnect();  
	            /*
	            for(int i = 0; i <= vdList.size(); i++) {
	            	System.out.println("make = " + vdList.toString());
	            }
	            */

	        } catch (Exception e) {
	            System.out.println("Exception in NetClientGet:- " + e);
	        }
		
	}

}
