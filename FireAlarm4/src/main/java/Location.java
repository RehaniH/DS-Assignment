import java.io.Serializable;

//Location object model

public class Location implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	private int floor_no;
	private int room_no;
	
	public int getFloor_no() {
		return floor_no;
	}
	public void setFloor_no(int floor_no) {
		this.floor_no = floor_no;
	}
	public int getRoom_no() {
		return room_no;
	}
	public void setRoom_no(int room_no) {
		this.room_no = room_no;
	}
}
