
//
//import java.rmi.Naming;
//import java.rmi.Remote;
//import java.rmi.RemoteException;
//import java.rmi.server.UnicastRemoteObject;
//
//public class ClientServiceProvider extends UnicastRemoteObject {
//
//	protected ClientServiceProvider() throws RemoteException {
//		super();
//		// TODO Auto-generated constructor stub
//	}
//
//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//		 System.setProperty("java.security.policy", "file:allowall.policy");
//		 
//
//			try {
//				String registration = "//localhost/SensorService";
//
//				Remote remoteService = Naming.lookup(registration);
//				SensorService sensor = (SensorService) remoteService;
////				Location reading = sensor.addSensor();
////				System.out.println("Original temp : " + reading);
//				
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//	}
//
//}
