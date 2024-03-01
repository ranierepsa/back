package br.com.pitang.back.user;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import br.com.pitang.back.car.Car;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "USER_TAB", 
       uniqueConstraints = @UniqueConstraint(
    		   columnNames = {"USER_EMAIL", "USER_LOGIN"}))
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@Column(name = "USER_ID")
	private UUID id;
	
	@NotNull
	@Column(name = "USER_FIRST_NAME")
	private String firstName;
	
	@NotNull
	@Column(name = "USER_LAST_NAME")
	private String lastName;
	
	@NotNull
	@Email
	@Column(name = "USER_EMAIL")
	private String email;
	
	@NotNull
	@Column(name = "USER_BIRTHDAY")
	private LocalDate birthday;
	
	@NotNull
	@Column(name = "USER_LOGIN")
	private String login;
	
	@NotNull
	@Column(name = "USER_PASSWORD")
	private String password;
	
	@NotNull
	@Size(min = 9, max = 9)
	@Column(name = "USER_PHONE")
	private String phone;
	
	@JoinColumn(name = "USER_ID")
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Car> cars;
	
	@Column(name = "USER_LAST_LOGIN")
	private LocalDateTime lastLogin;
	
	@CreationTimestamp
	@Column(name = "USER_CREATED_AT", updatable = false)
	private LocalDateTime createdAt;
	
	public User() {
		
	}

	public User(String firstName, String lastName, String email, LocalDate birthday, String login,
			String password, String phone, List<Car> cars) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.birthday = birthday;
		this.login = login;
		this.password = password;
		this.phone = phone;
		this.cars = cars;
	}
	
	public boolean hasMissingFields() {
		return firstName == null || firstName.isBlank()
				|| lastName == null || lastName.isBlank()
				|| email == null || email.isBlank()
				|| birthday == null
				|| login == null || login.isBlank()
				|| password == null || password.isBlank()
				|| phone == null || phone.isBlank();
	}
	
	public boolean hasInvalidFields() {
		return !isEmailValid();
	}
	
	private boolean isEmailValid() {
		String regex = "^[\\w!#$%&'*+/=?^`{|}~-]+(?:\\.[\\w!#$%&'*+/=?^`{|}~-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,}$";
	    return email != null && email.matches(regex);
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

}
