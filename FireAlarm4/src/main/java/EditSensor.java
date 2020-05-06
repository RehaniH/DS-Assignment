import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.Naming;
import java.rmi.Remote;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

//edit sensor detail frame
public class EditSensor extends JFrame implements ActionListener {
	//declare variables
	private String sensorId;
	private String locationId;	
	Container container = getContentPane();
	JLabel edit = new JLabel("Edit");
	JLabel room = new JLabel("Room No:");
	JLabel floor = new JLabel("Floor No:");
	JLabel status = new JLabel("Active:");
	JTextField roomNo = new JTextField();
	JTextField floorNo = new JTextField();
	JTextField active = new JTextField();
	JButton update = new JButton("UPDATE");
	
	//set JFrame layout
	public void setLayoutManager() {
        container.setLayout(null);
    }

	//set JFrame location and Size
	public void setLocationAndSize() {
		
		edit.setBounds(160, 10, 100, 30);		
		floor.setBounds(10, 50, 50, 30);
		floorNo.setBounds(70, 50, 50, 30);
		room.setBounds(130, 50, 60, 30);
		roomNo.setBounds(200, 50, 50, 30);
		status.setBounds(260, 50, 40, 30);
		active.setBounds(310, 50, 50, 30);
		update.setBounds(140, 90, 100, 30);
    }

	//add JFrame components
	 public void addComponentsToContainer() {
	        container.add(roomNo);
	        container.add(floorNo);
	        container.add(edit);
	        container.add(active);
	        container.add(update);
	        container.add(floor);
	        container.add(room);
	        container.add(status);
	 }

	 //add button eventlistener
	 public void addActionEvent() {
		 update.addActionListener(this);
	 }
	 
	 //button events
	 public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
		 	//update sensor
			if(e.getSource() == update) {
				 try {
						String registration = "//localhost/SensorService";
						Remote remoteService = Naming.lookup(registration);
						SensorService sensorService = (SensorService) remoteService;
						sensorService.updateSensor(Integer.parseInt(floorNo.getText()), Integer.parseInt( roomNo.getText()), Boolean.parseBoolean(active.getText()), sensorId, locationId);
						this.dispose();
						//go back to admin panel
			        	ClientFrame frame = new ClientFrame();
			    	    frame.setTitle("Login Form");
			    	    frame.setVisible(true);
			    	    frame.setBounds(500, 100, 400, 600);
			    	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			    	    frame.setResizable(true);
						
				 } catch (Exception ex) {
						ex.printStackTrace();
					}
			}
	}
	 
	 public EditSensor(String sensorId) {
		 	this.sensorId = sensorId;
			setLayoutManager();
	        setLocationAndSize();
	        addComponentsToContainer();
	        addActionEvent();
	        edit.setFont(new Font("", Font.PLAIN, 20));
	        //get sensor
	        try {
				String registration = "//localhost/SensorService";

				Remote remoteService = Naming.lookup(registration);
				SensorService sensorService = (SensorService) remoteService;
				Sensor sensor = sensorService.getSensorById(sensorId);
				
				floorNo.setText(String.valueOf(sensor.getLocation().getFloor_no()));
				roomNo.setText(String.valueOf(sensor.getLocation().getRoom_no()));
				active.setText(String.valueOf(sensor.isActive()));
				this.locationId = String.valueOf(sensor.getLocation().getId());
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

}
