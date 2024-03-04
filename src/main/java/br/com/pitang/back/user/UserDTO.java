package br.com.pitang.back.user;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import br.com.pitang.back.car.Car;

public class UserDTO {
	
	private UUID id;
	private String firstName;
	private String lastName;
	private String email;
	private LocalDate birthday;
	private String login;
	private String phone;
	private List<Car> cars;
	
	public UserDTO() {
		
	}
	
	public UserDTO(User user) {
		this.id = user.getId();
		this.firstName = user.getFirstName();
		this.lastName = user.getLastName();
		this.email = user.getEmail();
		this.birthday = user.getBirthday();
		this.login = user.getLogin();
		this.phone = user.getPhone();
		this.cars = user.getCars();
	}
	
	public UserDTO(UUID id, String firstName, String lastName, String email, LocalDate birthday, String login,
			String phone, List<Car> cars) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.birthday = birthday;
		this.login = login;
		this.phone = phone;
		this.cars = cars;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
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
}
