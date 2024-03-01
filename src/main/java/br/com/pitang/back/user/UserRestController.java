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

@RestController
@RequestMapping("api/users")
public class UserRestController {

	@Autowired
	private UserService userService;
    
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.findAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
    
    @PostMapping
    public ResponseEntity<User> addUser(@RequestBody User user) {
        User savedUser = userService.save(user);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable UUID id) {
        User user = userService.findById(id);
        if (user != null)
        	return new ResponseEntity<>(user, HttpStatus.OK);
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
    public ResponseEntity<User> updateUser(@PathVariable UUID id, @RequestBody User updatedUser) {
        User user = userService.update(id, updatedUser);
        if (user != null)
        	return new ResponseEntity<>(user, HttpStatus.OK);
        else
        	throw new ElementNotFoundException();
    }
}
