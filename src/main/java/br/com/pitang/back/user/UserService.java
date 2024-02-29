package br.com.pitang.back.user;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
		return userRepository.save(user);
	}
	
	public void deleteById(UUID id) {
		userRepository.deleteById(id);
	}
	
	public User update(UUID id, User user) {
		User foundUser = userRepository.findById(id).orElse(null);
        if (foundUser != null) {
            if (user.getFirstName() != null)
            	foundUser.setFirstName(user.getFirstName());
            if (user.getLastName() != null)
            	foundUser.setLastName(user.getLastName());
            if (user.getEmail() != null)
            	foundUser.setEmail(user.getEmail());
            if (user.getBirthday() != null)
            	foundUser.setBirthday(user.getBirthday());
            if (user.getLogin() != null)
            	foundUser.setLogin(user.getLogin());
            if (user.getPassword() != null)
            	foundUser.setPassword(user.getPassword());
            if (user.getPhone() != null)
            	foundUser.setPhone(user.getPhone());
            if (user.getCars() != null)
            	foundUser.setCars(user.getCars());
            
            return userRepository.save(foundUser);
        } else {
            return null;
        }
	}
}
