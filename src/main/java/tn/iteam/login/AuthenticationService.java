package tn.iteam.login;

import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    public boolean authentication(String username, String password){
        boolean isValidUserName = username.equalsIgnoreCase("iteam");
        boolean isValidPassword = password.equalsIgnoreCase("123");

        return isValidUserName && isValidPassword;
    }
}
