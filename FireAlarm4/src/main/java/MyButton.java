import javax.swing.JButton;

//custom JButton class to get sensor details

public class MyButton extends JButton {
	
	private String sensorId;
	private String locationId;
	
	public MyButton(){
		super();
	}

	public String getSensorId() {
		return sensorId;
	}

	public void setSensorId(String sensorId) {
		this.sensorId = sensorId;
	}

	public String getLocationId() {
		return locationId;
	}

	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}

	
}
