package br.com.pitang.back.user;

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
import br.com.pitang.back.security.SecurityService;

@RestController
@RequestMapping("api/users")
public class UserRestController {

	@Autowired
	private UserService userService;
    
    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<User> users = userService.findAll();
        List<UserDTO> usersDTO = users.stream().map(user -> new UserDTO(user)).toList();
        return new ResponseEntity<>(usersDTO, HttpStatus.OK);
    }
    
    @PostMapping
    public ResponseEntity<UserDTO> addUser(@RequestBody User user) {
    	String encryptedPassword = SecurityService.getInstance().encodePassword(user.getPassword());
    	user.setPassword(encryptedPassword);
    	User savedUser = userService.save(user);
        return new ResponseEntity<>(new UserDTO(savedUser), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable UUID id) {
        User user = userService.findById(id);
        if (user != null)
        	return new ResponseEntity<>(new UserDTO(user), HttpStatus.OK);
        else
        	throw new ElementNotFoundException();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable UUID id) {
    	User user = userService.findById(id);
    	if (user != null) {
	    	userService.deleteById(id);
	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    	} else {
    		throw new ElementNotFoundException();
    	}
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable UUID id, @RequestBody User updatedUser) {
    	String encryptedPassword = SecurityService.getInstance().encodePassword(updatedUser.getPassword());
    	updatedUser.setPassword(encryptedPassword);
    	User user = userService.update(id, updatedUser);
        if (user != null)
        	return new ResponseEntity<>(new UserDTO(user), HttpStatus.OK);
        else
        	throw new ElementNotFoundException();
    }
}
