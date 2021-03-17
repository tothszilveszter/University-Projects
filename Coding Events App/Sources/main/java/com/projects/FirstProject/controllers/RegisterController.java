package com.projects.FirstProject.controllers;

import com.projects.FirstProject.data.UserRepository;
import com.projects.FirstProject.models.User;
import com.projects.FirstProject.models.UserType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.SecureRandom;
import java.util.Random;

@Controller
@RequestMapping("register")
public class RegisterController {
    @Autowired
    private UserRepository userRepository;
    private static final Random RANDOM = new SecureRandom();


    @GetMapping("create")
    public String displayRegisterForm(Model model) {
        model.addAttribute("title", "Register");
        model.addAttribute(new User());
        return "register/create";
    }

    @PostMapping("create")
    public String processRegisterForm(@ModelAttribute User user, Model model) {

        model.addAttribute("title", "Register");
        Iterable<User> userList = userRepository.findAll();
        model.addAttribute("title", "Create Event");
        if (user.getUserEmail().equals("") || user.getPassword().equals("")){

            model.addAttribute("errorMsg", "Bad register data!");
            return "register/create";
        }
        else{
            for (User x : userList){
                if (x.getUserEmail().equals(user.getUserEmail())){
                    model.addAttribute("errorMsg", "Email already registered!");
                    return "register/create";
                }
            }
        }
        user.setPassword(String.valueOf(user.hashCode()) + getSalt());
        user.setUserType(UserType.EMPLOYEE);
        userRepository.save(user);
        return "/indexAfterLogin";
    }

    private static byte[] getSalt() {
        byte[] salt = new byte[16];
        RANDOM.nextBytes(salt);
        return salt;
    }

}
