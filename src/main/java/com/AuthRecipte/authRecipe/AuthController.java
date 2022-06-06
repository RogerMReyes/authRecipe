package com.AuthRecipte.authRecipe;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class AuthController {

    @Autowired
    SiteUserRepo siteUserRepo;

    @GetMapping("/login")
    public String getLoginPage(){return "login";}

    @PostMapping("/login")
    public RedirectView logInUser(String username, String _password){
        SiteUser user = siteUserRepo.findByUsername(username);

        if((user == null) || (!BCrypt.checkpw(_password, user.password))){
            return new RedirectView("/login");
        }
        return new RedirectView("/");
    }

    @GetMapping("/signUp")
    public String getSignInPage(){
        return "signIn";
    }

    @PostMapping("/signUp")
    public  RedirectView signUp(Model m, String username, String password){
        String hashedPass = BCrypt.hashpw(password, BCrypt.gensalt(15));
        SiteUser newUser = new SiteUser(username,hashedPass);
        siteUserRepo.save(newUser);
        return new RedirectView("login");
    }
}
