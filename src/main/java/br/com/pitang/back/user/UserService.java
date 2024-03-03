package br.com.pitang.back.user;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.pitang.back.exception.InvalidFieldsException;
import br.com.pitang.back.exception.MissingFieldsException;
import br.com.pitang.back.exception.UniqueUserEmailException;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	public List<User> findAll() {
		return userRepository.findAll();
	}
	
	public User findById(UUID id) {
		Optional<User> optUser = userRepository.findById(id);
		return optUser.orElse(null);
	}
	
	public User save(User user) {
		if (user.hasMissingFields()) throw new MissingFieldsException();
    	if (user.hasInvalidFields()) throw new InvalidFieldsException();
    	if (userRepository.existsByEmail(user.getEmail())) throw new UniqueUserEmailException();
    	
		return userRepository.save(user);
	}
	
	public void deleteById(UUID id) {
		userRepository.deleteById(id);
	}
	
	public User update(UUID id, User user) {
		if (user.hasMissingFields()) throw new MissingFieldsException();
    	if (user.hasInvalidFields()) throw new InvalidFieldsException();
    	
		User foundUser = userRepository.findById(id).orElse(null);
		
		if (foundUser == null) return null;
		
		if (doesEmailBelongsToSomeoneElse(user, foundUser)) 
			throw new UniqueUserEmailException();		
		
		user.setId(id);
		user.setLastLogin(foundUser.getLastLogin());
		user.setCreatedAt(foundUser.getCreatedAt());
		
		return foundUser != null ? userRepository.save(user) : null;
	}

	private boolean doesEmailBelongsToSomeoneElse(User user, User foundUser) {
		return foundUser != null && user != null
				&& userRepository.existsByEmail(user.getEmail())
				&& !user.getEmail().equals(foundUser.getEmail());
	}

	public User findByLogin(String login) {
		Optional<User> optUser = userRepository.findByLogin(login);
		return optUser.orElse(null);
	}
}
