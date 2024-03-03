package br.com.pitang.back.security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.com.pitang.back.exception.ElementNotFoundException;
import br.com.pitang.back.user.User;
import jakarta.servlet.http.HttpSession;

public class SecurityService {

    private static SecurityService instance;
    private HttpSession session;
    private BCryptPasswordEncoder encoder;

    private SecurityService() {
    	encoder = new BCryptPasswordEncoder();
    }

    public static SecurityService getInstance() {
        if (instance == null) {
            instance = new SecurityService();
        }
        return instance;
    }

    public void setSession(HttpSession session) {
        this.session = session;
    }
    
    public User getLoggedUser() {
        if (session != null && this.session.getAttribute("user") != null)
            return (User) this.session.getAttribute("user");

        throw new ElementNotFoundException();
    }
    
    public String encodePassword(String password) {
    	return encoder.encode(password);
    }
    
    public boolean verifyPasswords(String requestPassword, String storedPassword) {
    	return encoder.matches(requestPassword, storedPassword);
    }
}
