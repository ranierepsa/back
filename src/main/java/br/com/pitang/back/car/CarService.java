package br.com.pitang.back.car;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarService {
	
	@Autowired
	private CarRepository carRepository;
	
	public List<Car> findAll() {
        return carRepository.findAll();
    }

    public Car findById(UUID id) {
        return carRepository.findById(id).orElse(null);
    }

    public Car save(Car car) {
        return carRepository.save(car);
    }

    public void deleteById(UUID id) {
        carRepository.deleteById(id);
    }
    
    public Car update(UUID id, Car updatedCar) {
        Car car = carRepository.findById(id).orElse(null);
        if (car != null) {
            if (updatedCar.getYear() != null)
            	car.setYear(updatedCar.getYear());
            if (updatedCar.getLicensePlate() != null)
            	car.setLicensePlate(updatedCar.getLicensePlate());
            if (updatedCar.getModel() != null)
            	car.setModel(updatedCar.getModel());
            if (updatedCar.getColor() != null)
            	car.setColor(updatedCar.getColor());

            return carRepository.save(car);
        } else {
            return null;
        }
    }

}
