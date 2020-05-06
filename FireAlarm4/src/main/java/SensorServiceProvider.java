import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

//RMI server
public class SensorServiceProvider extends UnicastRemoteObject implements SensorService {
	
	protected SensorServiceProvider() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public static void main(String[] args) {
		//connect with the rmi registry
		System.setProperty("java.security.policy", "file:allowall.policy");
		
		try {
			SensorServiceProvider sensor = new SensorServiceProvider();

			Registry registry = LocateRegistry.getRegistry();
			
			System.out.println("Loading Sensor service");
			registry.bind("SensorService", sensor);
			System.out.println("Service Started........");
		}catch(Exception e){
            e.printStackTrace();
        }
	}
	
	//get all sensors
	public List<Sensor> getSensors() {		
		try {
			
			URL url = new URL("http://localhost:8080/v1/sensors/");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Content-Type", "application/json");
			ObjectMapper objectMapper = new ObjectMapper();
			
			String output;

			BufferedReader br = new BufferedReader(new InputStreamReader(
					(conn.getInputStream())));
			StringBuilder sb = new StringBuilder();

			while ((output = br.readLine()) != null) {
				 sb.append(output+"\n");
			}
			
			output = sb.toString();
			
			List<Sensor> l = objectMapper.readValue(output,new TypeReference<List<Sensor>>(){});
			output = br.readLine();

			conn.disconnect();
			
			return l;

		  } catch (MalformedURLException e) {

			e.printStackTrace();

		  } catch (IOException e) {

			e.printStackTrace();

		 }
		
		return null;			
	}

	//add new sensor
	public void addSensor(int flr, int rm) throws RemoteException {
		// TODO Auto-generated method stub
		try {
			
			URL url = new URL("http://localhost:8080/v1/sensors/");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/json");
			
			String input = "{\"floor_no\":" + flr + ",\"room_no\":\"" + rm + "\"}";

			OutputStream os = conn.getOutputStream();
			os.write(input.getBytes());
			os.flush();
			String output;

			BufferedReader br = new BufferedReader(new InputStreamReader(
					(conn.getInputStream())));
			
			while ((output = br.readLine()) != null) {
			}

			conn.disconnect();

		  } catch (MalformedURLException e) {

			e.printStackTrace();

		  } catch (IOException e) {

			e.printStackTrace();

		 }
	}

	//get sensor by id
	public Sensor getSensorById(String id) throws RemoteException {
		// TODO Auto-generated method stub
		try {			
			
			URL url = new URL("http://localhost:8080/v1/sensors/" + id);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Content-Type", "application/json");
			
			String output;

			BufferedReader br = new BufferedReader(new InputStreamReader(
					(conn.getInputStream())));
			StringBuilder sb = new StringBuilder();
			
			while ((output = br.readLine()) != null) {
				 sb.append(output+"\n");
			}
			ObjectMapper objectMapper = new ObjectMapper();
			output = sb.toString();
			
			Sensor sensor = objectMapper.readValue(output, Sensor.class);

			conn.disconnect();
			
			return sensor;

		  } catch (MalformedURLException e) {

			e.printStackTrace();

		  } catch (IOException e) {

			e.printStackTrace();

		 }
		
		return null;
	};
	
	
	//update sensor details
	public void updateSensor(int floor, int room, boolean status, String sensorId, String locationId) throws RemoteException {
		try {
			
			URL url = new URL("http://localhost:8080/v1/sensors/" + sensorId + "/locations/" + locationId);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod("PUT");
			conn.setRequestProperty("Content-Type", "application/json");
			
			String input = "{\"active\":" + status +",\"location\":{\"floor_no\":" + floor + ",\"room_no\":" + room + "}}";
			
			OutputStream os = conn.getOutputStream();
			os.write(input.getBytes());
			os.flush();
			String output;

			BufferedReader br = new BufferedReader(new InputStreamReader(
					(conn.getInputStream())));
			
			while ((output = br.readLine()) != null) {
			}

			conn.disconnect();

		  } catch (MalformedURLException e) {

			e.printStackTrace();

		  } catch (IOException e) {

			e.printStackTrace();

		 }
	}

	//validate admin login
	public boolean adminCheck(String password) throws RemoteException {
		try {			
			
			URL url = new URL("http://localhost:8080/v1/users/Admin");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Content-Type", "application/json");
			
			String output;

			BufferedReader br = new BufferedReader(new InputStreamReader(
					(conn.getInputStream())));
			StringBuilder sb = new StringBuilder();
			
			while ((output = br.readLine()) != null) {
				 sb.append(output+"\n");
			}
			
			ObjectMapper objectMapper = new ObjectMapper();
			output = sb.toString();
			
			User user = objectMapper.readValue(output, User.class);

			conn.disconnect();
			
			if(password.equals(user.getPassword())) {
				return true;
			} else {
				return false;
			}

		  } catch (MalformedURLException e) {

			e.printStackTrace();

		  } catch (IOException e) {

			e.printStackTrace();

		 }
		
		return false;
	}

	//send email notification
	public void sendMail() throws RemoteException {
		// TODO Auto-generated method stub		
		try {			
			
			URL url = new URL("http://localhost:8080/v1/users/Admin");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Content-Type", "application/json");
			
			String output;

			BufferedReader br = new BufferedReader(new InputStreamReader(
					(conn.getInputStream())));
			StringBuilder sb = new StringBuilder();
			
			while ((output = br.readLine()) != null) {
				 sb.append(output+"\n");
			}
			
			ObjectMapper objectMapper = new ObjectMapper();
			output = sb.toString();
			
			User user = objectMapper.readValue(output, User.class);
			
			SendEmail email = new SendEmail(user.getReceiver_email(), user.getSender_email(), user.getSender_password());
			conn.disconnect();

		  } catch (MalformedURLException e) {

			e.printStackTrace();

		  } catch (IOException e) {

			e.printStackTrace();

		 }
		
	}

	//send sms notification
	public void sendSMS() throws RemoteException {
		// TODO Auto-generated method stub
		try {			
			
			URL url = new URL("http://localhost:8080/v1/users/Admin");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Content-Type", "application/json");
			
			String output;

			BufferedReader br = new BufferedReader(new InputStreamReader(
					(conn.getInputStream())));
			StringBuilder sb = new StringBuilder();
			
			while ((output = br.readLine()) != null) {
				 sb.append(output+"\n");
			}
			
			ObjectMapper objectMapper = new ObjectMapper();
			output = sb.toString();
			
			User user = objectMapper.readValue(output, User.class);
			SmsSender sms = new SmsSender(user.getReceiver_mobile(),user.getSender_mobile());
		
		conn.disconnect();

	  } catch (MalformedURLException e) {

		e.printStackTrace();

	  } catch (IOException e) {

		e.printStackTrace();

	 }
	}

}
