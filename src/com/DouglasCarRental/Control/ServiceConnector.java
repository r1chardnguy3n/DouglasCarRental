package com.DouglasCarRental.Control;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import com.DouglasCarRental.Entity.Vehicle;





public class ServiceConnector {
	
	String jsonArrayString;
	public static ArrayList<Vehicle> vehicleList;
	public static ArrayList<Vehicle> vehicleList2;
	
	public void serviceStart() {
		

		
		try {
			
			//Connecting to service
			URL url = new URL("http://localhost:8080/WebServiceDou/vehicle.jsp");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");
            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP Error code : "
                        + conn.getResponseCode());		              
            }

            
            //Reading page
            InputStreamReader in = new InputStreamReader(conn.getInputStream());
            BufferedReader br = new BufferedReader(in);
            String output;
            
            //loop through json and store into string
            while((output = br.readLine()) != null) {
            	
            	jsonArrayString = output;
            	
            }
            
            //Converting JsonArray into ArrayList Object of Vehicle
            JSONObject jsonObject = new JSONObject(jsonArrayString);
            JSONArray jsonArray = jsonObject.getJSONArray("vehicle");
            vehicleList = new ArrayList<Vehicle>();
            for(int i = 0; i < jsonArray.length(); i++) {
            	JSONObject jObject = jsonArray.getJSONObject(i);
            	
            
            	
            	vehicleList.add(new Vehicle("1",jObject.getString("make"),
            			jObject.getString("model"),
            			jObject.getString("year"),
            			jObject.getString("type"),
            			jObject.getString("price")));
            			
            }
            

          
            

            conn.disconnect();
            
		}catch(Exception d) {
			
		}
		
	}//End of method
	
	public static ArrayList<Vehicle> getVehicleList(){
		return vehicleList;
	}
	
	public static void main(String[] args) {
		
		//Heres a test on how to use the Vehicle Object
		
		//Create new Object of ServiceConncetor
		ServiceConnector sc = new ServiceConnector();
		
		//Start the Connections
		sc.serviceStart();
		
		//Test Vehicle Object
		
		vehicleList2 = getVehicleList();
		
		for (Vehicle b : vehicleList2) {
   		 System.out.println(b);
   		 }
		int x =1;
		//Getting objects at index
		//System.out.println(sc.getVehicleList().get(1));
		
		//if you want to get make of a certain index
		//System.out.println(sc.getVehicleList().get(5).getMake());
		
		//if you want to get type at index 2
		//System.out.println(sc.getVehicleList().get(2).getType());
	}
	
	
}

