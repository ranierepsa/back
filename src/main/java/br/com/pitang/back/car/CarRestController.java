package br.com.pitang.back.car;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.pitang.back.exception.ElementNotFoundException;

@RestController
@RequestMapping(value = "api/cars")
public class CarRestController {
	
	@Autowired
	private CarService carService;
    
    @GetMapping
    public ResponseEntity<List<Car>> getAllCars() {
        List<Car> cars = carService.findAll();
        return new ResponseEntity<>(cars, HttpStatus.OK);
    }
    
    @PostMapping
    public ResponseEntity<Car> addCar(@RequestBody Car car) {
        Car savedCar = carService.save(car);
        return new ResponseEntity<>(savedCar, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Car> getCarById(@PathVariable UUID id) {
		Car car = carService.findById(id);
		if (car != null)
        	return new ResponseEntity<>(car, HttpStatus.OK);
        else
        	throw new ElementNotFoundException();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCar(@PathVariable UUID id) {
    	Car car = carService.findById(id);
    	if (car != null) {
    		carService.deleteById(id);
    		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    	} else {
    		throw new ElementNotFoundException();
    	}
    }

    @PutMapping("/{id}")
    public ResponseEntity<Car> updateCar(@PathVariable UUID id, @RequestBody Car updatedCar) {
        Car car = carService.update(id, updatedCar);
        if (car != null)
        	return new ResponseEntity<>(car, HttpStatus.OK);
        else
        	throw new ElementNotFoundException();
    }
}
