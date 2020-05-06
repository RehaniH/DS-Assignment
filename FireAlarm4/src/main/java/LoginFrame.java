import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.Naming;
import java.rmi.Remote;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.*;


//Client view/Login
public class LoginFrame extends JFrame implements ActionListener {

	//declare Jframe components and variable
	Container container = getContentPane();
	JButton loginButton = new JButton("LOGIN");
	JLabel floor = new JLabel("Floor");
	JLabel room = new JLabel("Room");
	JLabel co2 = new JLabel("CO2");
	JLabel smoke = new JLabel("Smoke");
	JLabel status = new JLabel("Status");
	JPasswordField password = new JPasswordField();
	List<Sensor> reading;
	
   
    LoginFrame() {
        contents();
    }
    
    //set JFrame components
    public void contents() {
		setLayoutManager();
        setLocationAndSize();
        addComponentsToContainer();
        addActionEvent();
        
        System.setProperty("java.security.policy", "file:allowall.policy");		 

        //get all sensors
		try {
			String registration = "//localhost/SensorService";

			Remote remoteService = Naming.lookup(registration);
			SensorService sensor = (SensorService) remoteService;
			//first clear Jframe
			container.removeAll();
			addComponentsToContainer();
			reading = sensor.getSensors();
			//create rows for sensors
			int j = 90;
			for (Sensor i : reading) {
				JLabel floor = new JLabel();
				JLabel room = new JLabel();
				JLabel co2 = new JLabel();
				JLabel smoke = new JLabel();
				JLabel status = new JLabel();
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
				//check for fire alarm event
				if(i.getCo2_level() >= 5 || i.getSmoke_level() >= 5) {
					floor.setBackground(Color.RED);
					room.setBackground(Color.RED);
					co2.setBackground(Color.RED);
					smoke.setBackground(Color.RED);
					status.setBackground(Color.RED);
					//send sms and email
					//sensor.sendMail();
					//sensor.sendSMS();
				} else if(i.isActive() == true) {
					floor.setBackground(Color.GREEN);
					room.setBackground(Color.GREEN);
					co2.setBackground(Color.GREEN);
					smoke.setBackground(Color.GREEN);
					status.setBackground(Color.GREEN);
				}
				//set Jframe bounds and add
				floor.setBounds(10, j, 50, 30);
				room.setBounds(60, j, 50, 30);
				co2.setBounds(110, j, 50, 30);
				smoke.setBounds(160, j, 50, 30);
				status.setBounds(210, j, 50, 30);
				container.add(floor);
				container.add(room);
				container.add(co2);
				container.add(smoke);
				container.add(status);
				j+=40;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
    
    public static void main(String args[]) {
    	//create admin panel
    	LoginFrame frame = new LoginFrame();
	    frame.setTitle("Login Form");
	    frame.setVisible(true);
	    frame.setBounds(500, 100, 290, 600);
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.setResizable(true);
	}

    //set JFrame layout
    public void setLayoutManager() {
        container.setLayout(null);
    }

    //set JFrame component location and size
    public void setLocationAndSize() {
    	 loginButton.setBounds(160, 10, 100, 30);
         floor.setBounds(10, 50, 520, 30);
         room.setBounds(60, 50, 50, 30);
         co2.setBounds(110, 50, 50, 30);
         smoke.setBounds(160, 50, 50, 30);
         status.setBounds(210, 50, 50, 30);
         password.setBounds(10, 10, 130, 30);
    }

    //add JFrame components
    public void addComponentsToContainer() {
    	 container.add(loginButton);
	        container.add(floor);	 
	        container.add(room);
	        container.add(co2);	
	        container.add(smoke);
	        container.add(status);
	        container.add(password);
    }

    //add button events
    public void addActionEvent() {
        loginButton.addActionListener(this);
    }

    //button events
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {
        	char[] pwd;
            String pwdText;
            pwd = password.getPassword();
            pwdText = new String(pwd);
            
            System.setProperty("java.security.policy", "file:allowall.policy");		 

            //check login validation
    		try {
    			String registration = "//localhost/SensorService";

    			Remote remoteService = Naming.lookup(registration);
    			SensorService sensor = (SensorService) remoteService;
    			    			
            if (sensor.adminCheck(pwdText)) {
            	this.dispose();
            	ClientFrame frame = new ClientFrame();
        	    frame.setTitle("Admin Panel");
        	    frame.setVisible(true);
        	    frame.setBounds(500, 100, 400, 600);
        	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        	    frame.setResizable(true);
            } else {
                JOptionPane.showMessageDialog(this, "Invalid Username or Password");
            }
    		} catch (Exception ex) {
    			ex.printStackTrace();
    		}

        }
    }


}
