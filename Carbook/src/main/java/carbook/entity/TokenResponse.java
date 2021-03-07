package carbook.entity;


import com.fasterxml.jackson.annotation.JsonProperty;

public class TokenResponse {

	private int id;
	
	@JsonProperty("ten_khach_hang")
	private String name;
	
	@JsonProperty("email")
	private String email;
	
	@JsonProperty("discount")
	private int discount;
	
	@JsonProperty("Token")
	private final String jwttoken;
	
	@JsonProperty("Roles")
	private String [] roles;
	
	public TokenResponse(String maToken) {
		this.jwttoken =maToken;
	}

	public String getJwttoken() {
		return jwttoken;
	}

	public String [] getRoles() {
		return roles;
	}

	public void setRoles(String [] roles) {
		this.roles = roles;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getDiscount() {
		return discount;
	}

	public void setDiscount(int discount) {
		this.discount = discount;
	}	
	
	
	
}
