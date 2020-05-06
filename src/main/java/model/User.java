package model;
import java.io.Serializable;


//User object model
public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String id;
	private String username;
	private String password;
	private String receiver_email;
	private String sender_password;
	private String sender_mobile;
	private String receiver_mobile;
	private Integer errorCode;
	private String sender_email;
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getReceiver_email() {
		return receiver_email;
	}

	public void setReceiver_email(String receiver_email) {
		this.receiver_email = receiver_email;
	}

	public String getSender_password() {
		return sender_password;
	}

	public void setSender_password(String sender_password) {
		this.sender_password = sender_password;
	}

	public String getSender_mobile() {
		return sender_mobile;
	}

	public void setSender_mobile(String sender_mobile) {
		this.sender_mobile = sender_mobile;
	}

	public String getReceiver_mobile() {
		return receiver_mobile;
	}

	public void setReceiver_mobile(String receiver_mobile) {
		this.receiver_mobile = receiver_mobile;
	}

	public Integer getError_code() {
		return errorCode;
	}

	public void setError_code(Integer errorCode) {
		this.errorCode = errorCode;
	}

	public String getSender_email() {
		return sender_email;
	}

	public void setSender_email(String sender_Email) {
		this.sender_email = sender_Email;
	}
}
