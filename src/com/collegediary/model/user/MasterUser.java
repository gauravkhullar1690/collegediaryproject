<<<<<<< HEAD
package com.collegediary.model.user;

public class MasterUser {
	private Long id;
	private String username;
	private String email;
	private String password;
	private Boolean active;
	private String token;
	private String remmberme;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Boolean getActive() {
		return active;
	}
	public void setActive(Boolean active) {
		this.active = active;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getRemmberme() {
		return remmberme;
	}
	public void setRemmberme(String remmberme) {
		this.remmberme = remmberme;
	}
}
=======
package com.collegediary.model.user;

public class MasterUser {
	private String username;
	private String email;
	private String password;
	private Boolean active;
	private UserType userType;
	private Boolean keepLoggedIn;
	private String authToken;

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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public UserType getUserType() {
		return userType;
	}

	public void setUserType(UserType userType) {
		this.userType = userType;
	}

	public Boolean getKeepLoggedIn() {
		return keepLoggedIn;
	}

	public void setKeepLoggedIn(Boolean keepLoggedIn) {
		this.keepLoggedIn = keepLoggedIn;
	}

	public String getAuthToken() {
		return authToken;
	}

	public void setAuthToken(String authToken) {
		this.authToken = authToken;
	}
}
>>>>>>> changes does in MasterUser to support adding keep me logged in functionality
