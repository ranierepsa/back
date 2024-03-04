package br.com.pitang.back.car;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.pitang.back.exception.InvalidFieldsException;
import br.com.pitang.back.exception.MissingFieldsException;
import br.com.pitang.back.exception.UniqueLicensePlateException;
import br.com.pitang.back.security.SecurityService;
import br.com.pitang.back.user.User;
import br.com.pitang.back.user.UserService;

@Service
public class CarService {
	
	@Autowired
	private CarRepository carRepository;
	
	@Autowired
	private UserService userService;
	
	public List<Car> findAll() {
		return getLoggedUser().getCars();
    }

    public Car findById(UUID id) {
    	List<Car> cars = getLoggedUser().getCars();
    	return cars.stream().filter(car -> {
    		return car.getId().equals(id);
    	}).findFirst().orElse(null);
    }

    public Car save(Car car) throws MissingFieldsException, UniqueLicensePlateException{
    	if (car.hasMissingFields()) throw new MissingFieldsException();
    	if (car.hasInvalidFields()) throw new InvalidFieldsException();
    	if (carRepository.existsByLicensePlate(car.getLicensePlate())) throw new UniqueLicensePlateException();
    	
    	Car newCar = carRepository.save(car);
    	getLoggedUser().getCars().add(newCar);
    	userService.update(getLoggedUser().getId(), getLoggedUser());
        
    	return newCar;
    }

    public void deleteById(UUID id) {
    	carRepository.deleteById(id);
    }
    
    public Car update(UUID id, Car updatedCar){
    	if (updatedCar.hasMissingFields()) throw new MissingFieldsException();
    	if (updatedCar.hasInvalidFields()) throw new InvalidFieldsException();
    	if (carRepository.existsByLicensePlate(updatedCar.getLicensePlate())) throw new UniqueLicensePlateException();
    	
        Car car = carRepository.findById(id).orElse(null);
        updatedCar.setId(id);
        return car != null ? carRepository.save(updatedCar) : null;
    }

    private User getLoggedUser() {
    	return SecurityService.getInstance().getLoggedUser();
    }
}
