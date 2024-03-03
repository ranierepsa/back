package br.com.pitang.back.authentication;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import br.com.pitang.back.car.Car;
import br.com.pitang.back.user.User;

public class LoggedUserDTO {
	
	private String firstName;
	private String lastName;
	private String email;
	private LocalDate birthday;
	private String login;
	private String phone;
	private List<Car> cars;
	private LocalDateTime createdAt;
	private LocalDateTime lasLogin;
	
	public LoggedUserDTO() {
		
	}
	
	public LoggedUserDTO(User user) {
		this.firstName = user.getFirstName();
		this.lastName = user.getLastName();
		this.email = user.getEmail();
		this.birthday = user.getBirthday();
		this.login = user.getLogin();
		this.phone = user.getPhone();
		this.cars = user.getCars();
		this.createdAt = user.getCreatedAt();
		this.lasLogin = user.getLastLogin();
	}
	
	public LoggedUserDTO(String firstName, String lastName, String email, LocalDate birthday, String login,
			String phone, List<Car> cars, LocalDateTime createdAt, LocalDateTime lasLogin) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.birthday = birthday;
		this.login = login;
		this.phone = phone;
		this.cars = cars;
		this.createdAt = createdAt;
		this.lasLogin = lasLogin;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public LocalDate getBirthday() {
		return birthday;
	}

	public void setBirthday(LocalDate birthday) {
		this.birthday = birthday;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public List<Car> getCars() {
		return cars;
	}

	public void setCars(List<Car> cars) {
		this.cars = cars;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getLasLogin() {
		return lasLogin;
	}

	public void setLasLogin(LocalDateTime lasLogin) {
		this.lasLogin = lasLogin;
	}
}
