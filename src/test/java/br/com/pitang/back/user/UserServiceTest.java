package br.com.pitang.back.user;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import br.com.pitang.back.car.Car;

@SpringBootTest
public class UserServiceTest {

	@Autowired
	private UserService userService;
	
	@MockBean
	private UserRepository userRepository;
	
	@Test
	public void testFindAll() {
		userService.findAll();
		
		Mockito.verify(userRepository, times(1)).findAll();
	}
	
	@Test
    public void testSave_NoCars() {
        User user = new User("Hello", "World", "hello@world.com", 
        		LocalDate.parse("1990-05-01"), "hello.world", "h3ll0", 
        		"988888888", null);

        Mockito.when(userRepository.save(user)).thenReturn(user);

        User savedUser = userService.save(user);

        assertNotNull(savedUser);
        assertEquals(user, savedUser);
    }
	
	@Test
    public void testSave_OneCar() {
		Car car = new Car(2023, "ABC-1234", "Tesla", "Black");
        User user = new User("Hello", "World", "hello@world.com", 
        		LocalDate.parse("1990-05-01"), "hello.world", "h3ll0", 
        		"988888888", List.of(car));

        Mockito.when(userRepository.save(user)).thenReturn(user);

        User savedUser = userService.save(user);

        assertNotNull(savedUser);
        assertEquals(user, savedUser);
        assertTrue(savedUser.getCars().size() == 1);
    }
	
	@Test
	public void testFindById_Existing() {
		UUID userId = UUID.randomUUID();
		Car car = new Car(2023, "ABC-1234", "Tesla", "Black");
        User user = new User("Hello", "World", "hello@world.com", 
        		LocalDate.parse("1990-05-01"), "hello.world", "h3ll0", 
        		"988888888", List.of(car));
		
		Mockito.when(userRepository.findById(any())).thenReturn(Optional.of(user));
		
		User savedUser = userService.findById(userId);
		
		assertNotNull(savedUser);
		assertEquals(user, savedUser);
		assertTrue(savedUser.getCars().size() == 1);
	}
	
	@Test
	public void testFindById_NonExisting() {
		UUID userId = UUID.randomUUID();
		
		Mockito.when(userRepository.findById(any())).thenReturn(Optional.empty());
		
		User userFound = userService.findById(userId);
		
		assertNull(userFound);
	}
	
	@Test
	public void testUpdate_AllFields() {
		UUID userId = UUID.randomUUID();
		Car existingCar = new Car(2023, "ABC-1234", "Tesla", "Black");
		Car updatedCar = new Car(2024, "ABC-1234", "Tesla", "Black");
		User existingUser = new User("Hello", "World", "hello@world.com", 
        		LocalDate.parse("1990-05-01"), "hello.world", "h3ll0", 
        		"988888888", List.of(existingCar));
		User updatedUser = new User("Rello", "Uorld", "rello@world.com", 
        		LocalDate.parse("1990-05-02"), "rello.world", "r3ll0", 
        		"988888881", List.of(updatedCar));
		
		Mockito.when(userRepository.findById(any())).thenReturn(Optional.of(existingUser));
		Mockito.when(userRepository.save(any())).thenReturn(updatedUser);

		User returnedUser = userService.update(userId, updatedUser);
		
		assertNotNull(returnedUser);
		assertEquals(updatedUser, returnedUser);
		assertEquals(returnedUser.getFirstName(), updatedUser.getFirstName());
		assertEquals(returnedUser.getLastName(), updatedUser.getLastName());
		assertEquals(returnedUser.getEmail(), updatedUser.getEmail());
		assertEquals(returnedUser.getBirthday(), updatedUser.getBirthday());
		assertEquals(returnedUser.getLogin(), updatedUser.getLogin());
		assertEquals(returnedUser.getPassword(), updatedUser.getPassword());
		assertEquals(returnedUser.getPhone(), updatedUser.getPhone());
		assertEquals(returnedUser.getCars(), updatedUser.getCars());
	}
	
	@Test
	public void testUpdate_FirstNameField() {
		UUID userId = UUID.randomUUID();
		User inputUser = new User();
		inputUser.setFirstName("Rello");
		Car existingCar = new Car(2023, "ABC-1234", "Tesla", "Black");
		Car updatedCar = new Car(2024, "ABC-1234", "Tesla", "Black");
		User existingUser = new User("Hello", "World", "hello@world.com", 
        		LocalDate.parse("1990-05-01"), "hello.world", "h3ll0", 
        		"988888888", List.of(existingCar));
		User updatedUser = new User("Rello", "Uorld", "rello@world.com", 
        		LocalDate.parse("1990-05-02"), "rello.world", "r3ll0", 
        		"988888881", List.of(updatedCar));
		
		Mockito.when(userRepository.findById(any())).thenReturn(Optional.of(existingUser));
		Mockito.when(userRepository.save(any())).thenReturn(updatedUser);

		User returnedUser = userService.update(userId, inputUser);
		
		assertNotNull(returnedUser);
		assertEquals(updatedUser, returnedUser);
		assertEquals(inputUser.getFirstName(), returnedUser.getFirstName());
	}
	
	@Test
	public void testUpdate_LastNameField() {
		UUID userId = UUID.randomUUID();
		User inputUser = new User();
		inputUser.setLastName("Uorld");
		Car existingCar = new Car(2023, "ABC-1234", "Tesla", "Black");
		Car updatedCar = new Car(2024, "ABC-1234", "Tesla", "Black");
		User existingUser = new User("Hello", "World", "hello@world.com", 
        		LocalDate.parse("1990-05-01"), "hello.world", "h3ll0", 
        		"988888888", List.of(existingCar));
		User updatedUser = new User("Rello", "Uorld", "rello@world.com", 
        		LocalDate.parse("1990-05-02"), "rello.world", "r3ll0", 
        		"988888881", List.of(updatedCar));
		
		Mockito.when(userRepository.findById(any())).thenReturn(Optional.of(existingUser));
		Mockito.when(userRepository.save(any())).thenReturn(updatedUser);

		User returnedUser = userService.update(userId, inputUser);
		
		assertNotNull(returnedUser);
		assertEquals(updatedUser, returnedUser);
		assertEquals(inputUser.getLastName(), returnedUser.getLastName());
	}
	
	@Test
	public void testUpdate_EmailField() {
		UUID userId = UUID.randomUUID();
		User inputUser = new User();
		inputUser.setEmail("rello@world.com");
		Car existingCar = new Car(2023, "ABC-1234", "Tesla", "Black");
		Car updatedCar = new Car(2024, "ABC-1234", "Tesla", "Black");
		User existingUser = new User("Hello", "World", "hello@world.com", 
        		LocalDate.parse("1990-05-01"), "hello.world", "h3ll0", 
        		"988888888", List.of(existingCar));
		User updatedUser = new User("Rello", "Uorld", "rello@world.com", 
        		LocalDate.parse("1990-05-02"), "rello.world", "r3ll0", 
        		"988888881", List.of(updatedCar));
		
		Mockito.when(userRepository.findById(any())).thenReturn(Optional.of(existingUser));
		Mockito.when(userRepository.save(any())).thenReturn(updatedUser);

		User returnedUser = userService.update(userId, inputUser);
		
		assertNotNull(returnedUser);
		assertEquals(updatedUser, returnedUser);
		assertEquals(inputUser.getEmail(), returnedUser.getEmail());
	}
	
	@Test
	public void testUpdate_BirthdayField() {
		UUID userId = UUID.randomUUID();
		User inputUser = new User();
		inputUser.setBirthday(LocalDate.parse("1990-05-02"));
		Car existingCar = new Car(2023, "ABC-1234", "Tesla", "Black");
		Car updatedCar = new Car(2024, "ABC-1234", "Tesla", "Black");
		User existingUser = new User("Hello", "World", "hello@world.com", 
        		LocalDate.parse("1990-05-01"), "hello.world", "h3ll0", 
        		"988888888", List.of(existingCar));
		User updatedUser = new User("Rello", "Uorld", "rello@world.com", 
        		LocalDate.parse("1990-05-02"), "rello.world", "r3ll0", 
        		"988888881", List.of(updatedCar));
		
		Mockito.when(userRepository.findById(any())).thenReturn(Optional.of(existingUser));
		Mockito.when(userRepository.save(any())).thenReturn(updatedUser);

		User returnedUser = userService.update(userId, inputUser);
		
		assertNotNull(returnedUser);
		assertEquals(updatedUser, returnedUser);
		assertEquals(inputUser.getBirthday(), returnedUser.getBirthday());
	}
	
	@Test
	public void testUpdate_LoginField() {
		UUID userId = UUID.randomUUID();
		User inputUser = new User();
		inputUser.setLogin("rello.world");
		Car existingCar = new Car(2023, "ABC-1234", "Tesla", "Black");
		Car updatedCar = new Car(2024, "ABC-1234", "Tesla", "Black");
		User existingUser = new User("Hello", "World", "hello@world.com", 
        		LocalDate.parse("1990-05-01"), "hello.world", "h3ll0", 
        		"988888888", List.of(existingCar));
		User updatedUser = new User("Rello", "Uorld", "rello@world.com", 
        		LocalDate.parse("1990-05-02"), "rello.world", "r3ll0", 
        		"988888881", List.of(updatedCar));
		
		Mockito.when(userRepository.findById(any())).thenReturn(Optional.of(existingUser));
		Mockito.when(userRepository.save(any())).thenReturn(updatedUser);

		User returnedUser = userService.update(userId, inputUser);
		
		assertNotNull(returnedUser);
		assertEquals(updatedUser, returnedUser);
		assertEquals(inputUser.getLogin(), returnedUser.getLogin());
	}
	
	@Test
	public void testUpdate_PasswordField() {
		UUID userId = UUID.randomUUID();
		User inputUser = new User();
		inputUser.setPassword("r3ll0");
		Car existingCar = new Car(2023, "ABC-1234", "Tesla", "Black");
		Car updatedCar = new Car(2024, "ABC-1234", "Tesla", "Black");
		User existingUser = new User("Hello", "World", "hello@world.com", 
        		LocalDate.parse("1990-05-01"), "hello.world", "h3ll0", 
        		"988888888", List.of(existingCar));
		User updatedUser = new User("Rello", "Uorld", "rello@world.com", 
        		LocalDate.parse("1990-05-02"), "rello.world", "r3ll0", 
        		"988888881", List.of(updatedCar));
		
		Mockito.when(userRepository.findById(any())).thenReturn(Optional.of(existingUser));
		Mockito.when(userRepository.save(any())).thenReturn(updatedUser);

		User returnedUser = userService.update(userId, inputUser);
		
		assertNotNull(returnedUser);
		assertEquals(updatedUser, returnedUser);
		assertEquals(inputUser.getPassword(), returnedUser.getPassword());
	}
	
	@Test
	public void testUpdate_PhoneField() {
		UUID userId = UUID.randomUUID();
		User inputUser = new User();
		inputUser.setPhone("988888881");
		Car existingCar = new Car(2023, "ABC-1234", "Tesla", "Black");
		Car updatedCar = new Car(2024, "ABC-1234", "Tesla", "Black");
		User existingUser = new User("Hello", "World", "hello@world.com", 
        		LocalDate.parse("1990-05-01"), "hello.world", "h3ll0", 
        		"988888888", List.of(existingCar));
		User updatedUser = new User("Rello", "Uorld", "rello@world.com", 
        		LocalDate.parse("1990-05-02"), "rello.world", "r3ll0", 
        		"988888881", List.of(updatedCar));
		
		Mockito.when(userRepository.findById(any())).thenReturn(Optional.of(existingUser));
		Mockito.when(userRepository.save(any())).thenReturn(updatedUser);

		User returnedUser = userService.update(userId, inputUser);
		
		assertNotNull(returnedUser);
		assertEquals(updatedUser, returnedUser);
		assertEquals(inputUser.getPhone(), returnedUser.getPhone());
	}
	
	@Test
	public void testUpdate_CarsField() {
		UUID userId = UUID.randomUUID();
		Car existingCar = new Car(2023, "ABC-1234", "Tesla", "Black");
		Car updatedCar = new Car(2024, "ABC-1234", "Tesla", "Black");
		User existingUser = new User("Hello", "World", "hello@world.com", 
        		LocalDate.parse("1990-05-01"), "hello.world", "h3ll0", 
        		"988888888", List.of(existingCar));
		User updatedUser = new User("Rello", "Uorld", "rello@world.com", 
        		LocalDate.parse("1990-05-02"), "rello.world", "r3ll0", 
        		"988888881", List.of(updatedCar));
		User inputUser = new User();
		inputUser.setCars(List.of(updatedCar));
		
		Mockito.when(userRepository.findById(any())).thenReturn(Optional.of(existingUser));
		Mockito.when(userRepository.save(any())).thenReturn(updatedUser);

		User returnedUser = userService.update(userId, inputUser);
		
		assertNotNull(returnedUser);
		assertEquals(updatedUser, returnedUser);
		assertEquals(inputUser.getCars(), returnedUser.getCars());
	}
	
	@Test
	public void testUpdate_NonExisting() {
		UUID userId = UUID.randomUUID();
		Car car = new Car(2024, "ABC-1234", "Tesla", "Black");
		User existingUser = new User("Hello", "World", "hello@world.com", 
        		LocalDate.parse("1990-05-01"), "hello.world", "h3ll0", 
        		"988888888", List.of(car));
		
		Mockito.when(userRepository.findById(any())).thenReturn(Optional.empty());
		Mockito.when(userRepository.save(any())).thenReturn(null);

		User returnedUser = userService.update(userId, existingUser);
		
		assertNull(returnedUser);
	}
	
	@Test
	public void testDelete_Existing() {
		UUID userId = UUID.randomUUID();
		
		Mockito.doNothing().when(userRepository).deleteById(userId);

	    userService.deleteById(userId);

	    Mockito.verify(userRepository, times(1)).deleteById(userId);
	}
	
	@Test
	public void testDelete_NonExisting() {
		UUID userId = UUID.randomUUID();
		
		userService.deleteById(userId);
		
		Mockito.verify(userRepository, times(1)).deleteById(userId);
	}
}
