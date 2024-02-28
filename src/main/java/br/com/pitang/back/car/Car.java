package br.com.pitang.back.car;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "CAR")
public class Car {
	
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@Column(name = "CAR_ID")
	private UUID id;
	
	@Column(name = "CAR_YEAR")
	private Integer year;
	
	@Column(name = "CAR_LICENCE_PLATE")
	private String licensePlate;
	
	@Column(name = "CAR_MODEL")
	private String model;
	
	@Column(name = "CAR_COLOR")
	private String color;

	public Car() {

	}
	
	public Car(Integer year, String licensePlate, String model, String color) {
		this.year = year;
		this.licensePlate = licensePlate;
		this.model = model;
		this.color = color;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public String getLicensePlate() {
		return licensePlate;
	}

	public void setLicensePlate(String licensePlate) {
		this.licensePlate = licensePlate;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}
}
