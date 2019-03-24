package Utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.lang.reflect.Type;

//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;
//import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;

import com.DouglasCarRental.Entity.Vehicle;


public class ApiConnector {
	
	ArrayList<Vehicle> vehicle;
	
	public ApiConnector() {
		
	}
	
	public void startApi() {
		 try {
			 
			 
		 	String urlString = "http://localhost:8080/DouglasCarRentalWebService/VehicleServlet";
		 	final URL url = new URL(urlString);
		 	final URLConnection connection = url.openConnection();
		 	connection.setDoOutput(true);
		 	
		 	final BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		 	final char[] buffer = new char [Integer.parseInt(connection.getHeaderField("Content-Length"))];
		 	int bytesRead = 0;
		 	
		 	while(bytesRead < buffer.length) {
		 		bytesRead += br.read(buffer, bytesRead, buffer.length - bytesRead + 1);
		 	}
		 	final JSONArray jsonArray = new JSONArray(new String(buffer));
		 	vehicle = new ArrayList<Vehicle>(jsonArray.length());
		 	
		 	for(int i =0; i< jsonArray.length(); i++){
		 		
		 		vehicle.add((Vehicle) jsonArray.get(i));
		 	}
		 
            

	        } catch (Exception e) {
	            System.out.println("Exception in NetClientGet:- " + e);
	        }
	}
	
	public ArrayList<Vehicle> getVehicle(){
		return vehicle;
	}
	
	public static void main(String[] args) {
		
		ApiConnector ac = new ApiConnector();
		
		ac.startApi();
		
		System.out.println(ac.getVehicle());
	}

}
