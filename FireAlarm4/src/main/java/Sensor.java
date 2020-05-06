import java.io.Serializable;

//Sensor model object

public class Sensor implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String id;
	private int smoke_level;
	private int co2_level;
	private Location location;
	private boolean active;
	private Integer error;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getSmoke_level() {
		return smoke_level;
	}
	public void setSmoke_level(int smoke_level) {
		this.smoke_level = smoke_level;
	}
	public int getCo2_level() {
		return co2_level;
	}
	public void setCo2_level(int co2_level) {
		this.co2_level = co2_level;
	}
	public Location getLocation() {
		return location;
	}
	public void setLocation(Location location) {
		this.location = location;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public Integer getError() {
		return error;
	}
	public void setError(Integer error) {
		this.error = error;
	}

	
}
