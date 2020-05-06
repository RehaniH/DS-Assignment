package service;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.Naming;
import java.rmi.Remote;
import java.util.List;

import javax.swing.*;

import model.Sensor;

import java.util.Timer;
import java.util.TimerTask;

public class ClientFrame extends JFrame implements ActionListener {
	
	/**
	 * A comment added
	 */
	private static final long serialVersionUID = 1L;
	
	//Initialise JFrame components
	Container container = getContentPane();
	JButton loginButton = new JButton("LOGOUT");
	JButton add = new JButton("ADD");
	JLabel floor = new JLabel("Floor");
	JLabel room = new JLabel("Room");
	JLabel co2 = new JLabel("CO2");
	JLabel smoke = new JLabel("Smoke");
	JLabel status = new JLabel("Status");
	JLabel rmno = new JLabel("Room No:");
	JLabel flno = new JLabel("Floor No:");
	JLabel adminPanel = new JLabel("Admin Panel");
	JTextField roomno = new JTextField(5);
    JTextField floorno = new JTextField(5);
	static ClientFrame frame = null;
	List<Sensor> reading;
	MyButton m = new MyButton();
	
	public ClientFrame() {
		contents();
	}
	
	//Set the JFrame components together
	public void contents() {
		
		setLayoutManager();
        setLocationAndSize();
        addComponentsToContainer();
        addActionEvent();
        adminPanel.setFont(new Font("", Font.PLAIN, 20));
        
        System.setProperty("java.security.policy", "file:allowall.policy");		 

		try {
			//get sensor information
			String registration = "//localhost/SensorService";

			Remote remoteService = Naming.lookup(registration);
			SensorService sensor = (SensorService) remoteService;
			//empty the JFrame first
			container.removeAll();
			addComponentsToContainer();
			reading = sensor.getSensors();
			//create rows for each sensor
			int j = 130;
			for (Sensor i : reading) {
				JLabel floor = new JLabel();
				JLabel room = new JLabel();
				JLabel co2 = new JLabel();
				JLabel smoke = new JLabel();
				JLabel status = new JLabel();
				MyButton edit = new MyButton();
				edit.setText("EDIT");
				floor.setOpaque(true);
				room.setOpaque(true);
				co2.setOpaque(true);
				smoke.setOpaque(true);
				status.setOpaque(true);
				floor.setText(String.valueOf(i.getLocation().getFloor_no()));	
				room.setText(String.valueOf(i.getLocation().getRoom_no()));
				co2.setText(String.valueOf(i.getCo2_level()));	
				smoke.setText(String.valueOf(i.getSmoke_level()));	
				status.setText(String.valueOf(i.isActive()));
				edit.setSensorId(i.getId());
				//update button click event
				edit.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						Object source = e.getSource();
						MyButton m = (MyButton)source;
						EditSensor es = new EditSensor(m.getSensorId());
						es.setTitle("Edit Form");
						es.setVisible(true);
						es.setBounds(500, 100, 390, 180);
						es.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE );
						es.setResizable(true);
						dispose();						
					}
				});
				//check for fire alarm event
				if(i.getCo2_level() >= 5 || i.getSmoke_level() >= 5) {
					floor.setBackground(Color.RED);
					room.setBackground(Color.RED);
					co2.setBackground(Color.RED);
					smoke.setBackground(Color.RED);
					status.setBackground(Color.RED);
					//send smms and email
					sensor.sendMail();
					sensor.sendSMS();
				} else if(i.isActive() == true) {
					floor.setBackground(Color.GREEN);
					room.setBackground(Color.GREEN);
					co2.setBackground(Color.GREEN);
					smoke.setBackground(Color.GREEN);
					status.setBackground(Color.GREEN);
				}
				//set JFrame components
				floor.setBounds(10, j, 50, 30);
				room.setBounds(60, j, 50, 30);
				co2.setBounds(110, j, 50, 30);
				smoke.setBounds(160, j, 50, 30);
				status.setBounds(210, j, 50, 30);
		        edit.setBounds(270, j, 100, 30);
				container.add(floor);
				container.add(room);
				container.add(edit);
				container.add(co2);
				container.add(smoke);
				container.add(status);
				j+=40;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//create new JFrame
		frame = new ClientFrame();
	    frame.setTitle("Admin Panel");
	    frame.setVisible(true);
	    frame.setBounds(500, 100, 400, 600);
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.setResizable(true);
    	//Refresh JFrame every 15 seconds
		new Timer().scheduleAtFixedRate(new TimerTask() {
			@Override
		    public void run() {
				if(frame != null) {
					frame.contents();
					frame.revalidate();
					frame.repaint();
				}
		    }
		}, 0, 15000);
	}

	//set JFrame layout
	public void setLayoutManager() {
        container.setLayout(null);
    }

	//set JFrame location and size
	public void setLocationAndSize() {
        loginButton.setBounds(270, 10, 100, 30);
        add.setBounds(270, 50, 100, 30);
        floor.setBounds(10, 90, 520, 30);
        room.setBounds(60, 90, 50, 30);
        co2.setBounds(110, 90, 50, 30);
        smoke.setBounds(160, 90, 50, 30);
        status.setBounds(210, 90, 50, 30);
        flno.setBounds(10, 50, 520, 30);
        floorno.setBounds(65, 50, 60, 30);
        rmno.setBounds(130, 45, 520, 40);
        roomno.setBounds(190, 50, 70, 30);
        adminPanel.setBounds(90, 5, 120, 40);
    }

	//add components to JFrame
	public void addComponentsToContainer() {
	        container.add(loginButton);
	        container.add(floor);	 
	        container.add(room);	
	        container.add(add);
	        container.add(co2);	
	        container.add(smoke);
	        container.add(status);
	        container.add(rmno);
	        container.add(flno);	
	        container.add(roomno);
	        container.add(floorno);
	        container.add(adminPanel);
	 }

	//add button events
	public void addActionEvent() {
		 if (loginButton.getActionListeners().length<1){
			 loginButton.addActionListener(this);
		 }
		 
		 if (add.getActionListeners().length<1){
			 add.addActionListener(this);
		 }
	 }

	//button events
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		//Client view/login
		if(e.getSource() == loginButton) {
			this.dispose();
			LoginFrame frame = new LoginFrame();
		    frame.setTitle("Login Form");
		    frame.setVisible(true);
		    frame.setBounds(500, 100, 290, 600);
		    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		    frame.setResizable(true);
		}
		//get sensor id and pass to next frame
		if(e.getSource() == add) {
		         System.setProperty("java.security.policy", "file:allowall.policy");				 

					try {
						String registration = "//localhost/SensorService";

						Remote remoteService = Naming.lookup(registration);
						SensorService sensor = (SensorService) remoteService;
						sensor.addSensor(Integer.parseInt(floorno.getText()), Integer.parseInt(roomno.getText()));
						floorno.setText("");
						roomno.setText("");
						contents();
						revalidate();
						repaint();
						}
					 catch (Exception ex) {
						ex.printStackTrace();
					}		
		}
	}
}