package br.com.pitang.back.car;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.pitang.back.exception.InvalidFieldsException;
import br.com.pitang.back.exception.MissingFieldsException;
import br.com.pitang.back.exception.UniqueLicensePlateException;

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

    public Car save(Car car) throws MissingFieldsException, UniqueLicensePlateException{
    	if (car.hasMissingFields()) throw new MissingFieldsException();
    	if (car.hasInvalidFields()) throw new InvalidFieldsException();
    	if (carRepository.existsByLicensePlate(car.getLicensePlate())) throw new UniqueLicensePlateException();
        
    	return carRepository.save(car);
    }

    public void deleteById(UUID id) {
        carRepository.deleteById(id);
    }
    
    public Car update(UUID id, Car updatedCar){
    	if (updatedCar.hasMissingFields()) throw new MissingFieldsException();
    	if (updatedCar.hasInvalidFields()) throw new InvalidFieldsException();
    	if (carRepository.existsByLicensePlate(updatedCar.getLicensePlate())) throw new UniqueLicensePlateException();
    	
        Car car = carRepository.findById(id).orElse(null);
        return car != null ? carRepository.save(car) : null;
    }

}
