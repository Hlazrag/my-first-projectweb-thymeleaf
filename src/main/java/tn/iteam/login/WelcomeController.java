package tn.iteam.login;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("name")
public class WelcomeController {

    private Logger logger = LoggerFactory.getLogger(getClass());
    private final AuthenticationService authenticationService;

    public WelcomeController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String goToLoginPage(ModelMap model) {
        String username = getLoggedInUsername();
        logger.info("The name from context is : {}", username );
        model.put("name", username);
        return "welcome";
    }

    public String getLoggedInUsername() {
        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        return authentication.getName();
    }
} 
