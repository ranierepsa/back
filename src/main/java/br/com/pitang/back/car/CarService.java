package br.com.pitang.back.car;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarService {
	
	@Autowired
	private CarRepository carRepository;
	
	public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    public Optional<Car> getCarById(UUID id) {
        return carRepository.findById(id);
    }

    public Car saveCar(Car car) {
        return carRepository.save(car);
    }

    public void deleteCar(UUID id) {
        carRepository.deleteById(id);
    }
    
    public Car updateCar(UUID id, Car updatedCar) {
        Optional<Car> optionalCar = carRepository.findById(id);
        if (optionalCar.isPresent()) {
            Car car = optionalCar.get();
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
