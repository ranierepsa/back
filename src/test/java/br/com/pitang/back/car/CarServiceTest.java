package br.com.pitang.back.car;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;

import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
public class CarServiceTest {
	
	@Autowired
	private CarService carService;
	
	@MockBean
	private CarRepository carRepository;
	
	@Test
	public void testFindAll() {
		carService.findAll();
		
		Mockito.verify(carRepository, times(1)).findAll();
	}
	
	@Test
    public void testSave() {
        Car car = new Car(2023, "ABC-1234", "Tesla", "Black");

        Mockito.when(carRepository.save(any())).thenReturn(car);

        Car savedCar = carService.save(car);

        assertNotNull(savedCar);
        assertEquals(car, savedCar);
    }
	
	@Test
	public void testFindById_Existing() {
		UUID carId = UUID.randomUUID();
		Car car = new Car(2023, "ABC-1234", "Tesla", "Black");
		
		Mockito.when(carRepository.findById(any())).thenReturn(Optional.of(car));
		
		Car carFound = carService.findById(carId);
		
		assertNotNull(carFound);
		assertEquals(car, carFound);
	}
	
	@Test
	public void testFindById_NonExisting() {
		UUID carId = UUID.randomUUID();
		
		Mockito.when(carRepository.findById(any())).thenReturn(Optional.empty());
		
		Car carFound = carService.findById(carId);
		
		assertNull(carFound);
	}
	
	@Test
	public void testUpdate_AllFields() {
		UUID carId = UUID.randomUUID();
		Car existingCar = new Car(2023, "ABC-1234", "Tesla", "Black");
		Car updatedCar = new Car(2024, "CBA-4321", "Texla", "Brack");
		
		Mockito.when(carRepository.findById(any())).thenReturn(Optional.of(existingCar));
		Mockito.when(carRepository.save(any())).thenReturn(updatedCar);

		Car returnedCar = carService.update(carId, updatedCar);
		
		assertNotNull(returnedCar);
		assertEquals(updatedCar, returnedCar);
		assertEquals(returnedCar.getYear(), updatedCar.getYear());
		assertEquals(returnedCar.getLicensePlate(), updatedCar.getLicensePlate());
		assertEquals(returnedCar.getModel(), updatedCar.getModel());
		assertEquals(returnedCar.getColor(), updatedCar.getColor());
	}
	
	@Test
	public void testUpdate_YearField() {
		UUID carId = UUID.randomUUID();
		Car inputCar = new Car();
		inputCar.setYear(2024);
		Car existingCar = new Car(2023, "ABC-1234", "Tesla", "Black");
		Car updatedCar = new Car(2024, "ABC-1234", "Tesla", "Black");
		
		Mockito.when(carRepository.findById(any())).thenReturn(Optional.of(existingCar));
		Mockito.when(carRepository.save(any())).thenReturn(updatedCar);

		Car returnedCar = carService.update(carId, inputCar);
		
		assertNotNull(returnedCar);
		assertEquals(updatedCar, returnedCar);
	}
	
	@Test
	public void testUpdate_LicensePlateField() {
		UUID carId = UUID.randomUUID();
		Car inputCar = new Car();
		inputCar.setLicensePlate("CBA-4321");
		Car existingCar = new Car(2023, "ABC-1234", "Tesla", "Black");
		Car updatedCar = new Car(2023, "CBA-4321", "Tesla", "Black");
		
		Mockito.when(carRepository.findById(any())).thenReturn(Optional.of(existingCar));
		Mockito.when(carRepository.save(any())).thenReturn(updatedCar);

		Car returnedCar = carService.update(carId, inputCar);
		
		assertNotNull(returnedCar);
		assertEquals(updatedCar, returnedCar);
	}
	
	@Test
	public void testUpdate_ModelField() {
		UUID carId = UUID.randomUUID();
		Car inputCar = new Car();
		inputCar.setModel("Ford");
		Car existingCar = new Car(2023, "ABC-1234", "Tesla", "Black");
		Car updatedCar = new Car(2023, "ABC-1234", "Ford", "Black");
		
		Mockito.when(carRepository.findById(any())).thenReturn(Optional.of(existingCar));
		Mockito.when(carRepository.save(any())).thenReturn(updatedCar);

		Car returnedCar = carService.update(carId, inputCar);
		
		assertNotNull(returnedCar);
		assertEquals(updatedCar, returnedCar);
	}
	
	@Test
	public void testUpdate_ColorField() {
		UUID carId = UUID.randomUUID();
		Car inputCar = new Car();
		inputCar.setColor("White");
		Car existingCar = new Car(2023, "ABC-1234", "Tesla", "Black");
		Car updatedCar = new Car(2023, "ABC-1234", "Tesla", "White");
		
		Mockito.when(carRepository.findById(any())).thenReturn(Optional.of(existingCar));
		Mockito.when(carRepository.save(any())).thenReturn(updatedCar);

		Car returnedCar = carService.update(carId, inputCar);
		
		assertNotNull(returnedCar);
		assertEquals(updatedCar, returnedCar);
	}
	
	@Test
	public void testUpdate_NonExistingCar() {
		UUID carId = UUID.randomUUID();
		Car car = new Car(2023, "ABC-1234", "Tesla", "Black");
		
		Mockito.when(carRepository.findById(any())).thenReturn(Optional.empty());
		Mockito.when(carRepository.save(any())).thenReturn(null);

		Car returnedCar = carService.update(carId, car);
		
		assertNull(returnedCar);
	}
	
	@Test
	public void testDelete_ExistingCar() {
		UUID carId = UUID.randomUUID();
		
		Mockito.doNothing().when(carRepository).deleteById(carId);

	    carService.deleteById(carId);

	    Mockito.verify(carRepository, times(1)).deleteById(carId);
	}
	
	@Test
	public void testDelete_NonExistingCar() {
		UUID carId = UUID.randomUUID();
		
		carService.deleteById(carId);
		
		Mockito.verify(carRepository, times(1)).deleteById(carId);
	}
}
