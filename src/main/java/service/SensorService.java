package service;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import org.json.JSONObject;

import model.Sensor;

//RMI server interface
public interface SensorService extends Remote {
	
	public List<Sensor> getSensors() throws RemoteException;
	
	public void addSensor(int flr, int rm) throws RemoteException;
	
	public Sensor getSensorById(String id) throws RemoteException;
	
	public void updateSensor(int floor, int room, boolean status, String sensorId, String locationId) throws RemoteException;
	
	public boolean adminCheck(String password ) throws RemoteException;
	
	public void sendMail() throws RemoteException;
	
	public void sendSMS() throws RemoteException;

}
